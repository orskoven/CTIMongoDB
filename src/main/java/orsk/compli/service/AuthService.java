package orsk.compli.service;

import org.hibernate.event.service.spi.EventListenerRegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orsk.compli.dtos.*;
import orsk.compli.entities.mongo.MongoPasswordResetToken;
import orsk.compli.entities.mongo.MongoRefreshToken;
import orsk.compli.entities.mongo.MongoUser;
import orsk.compli.entities.mongo.MongoVerificationToken;
import orsk.compli.repository.mongo.PasswordResetTokenMongoRepository;
import orsk.compli.repository.mongo.RoleMongoRepository;
import orsk.compli.repository.mongo.UserMongoRepository;
import orsk.compli.repository.mongo.VerificationTokenMongoRepository;
import orsk.compli.security.JwtTokenProvider;
import orsk.compli.service.mongo.RefreshTokenMongoService;

import javax.management.relation.RoleNotFoundException;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Service("MongoAuthService")
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMongoRepository userRepository;

    @Autowired
    private RoleMongoRepository roleRepository;

    @Autowired
    private VerificationTokenMongoRepository verificationTokenRepository;

    @Autowired
    private PasswordResetTokenMongoRepository passwordResetTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private RefreshTokenMongoService refreshTokenService;



    @Transactional
    public void registerUser(RegistrationRequest registrationRequest) {
        if (userRepository.findByUsername(registrationRequest.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username is already taken");
        }
        if (userRepository.findByEmail(registrationRequest.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already registered");
        }

        try {
            MongoUser user = new MongoUser();
            user.setUsername(registrationRequest.getUsername());
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            user.setEmail(registrationRequest.getEmail());
            user.setConsentToDataUsage(registrationRequest.getConsentToDataUsage());
            user.setEnabled(false);

            user.setRoles(Set.of(roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RoleNotFoundException("ROLE_USER not found"))));

            userRepository.save(user);

            String token = generateVerificationToken(user);
            // emailService.sendVerificationEmail(user.getEmail(), token);

        } catch (Exception e) {
            throw new EventListenerRegistrationException("Registration failed: " + e.getMessage(), e);
        }
    }

    @Transactional
    public JwtAuthenticationResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        String jwt = tokenProvider.generateToken(authentication);

        MongoRefreshToken refreshToken = refreshTokenService.createRefreshToken(authentication.getName());

        return new JwtAuthenticationResponse(jwt, refreshToken.getToken());
    }

    @Transactional
    public JwtAuthenticationResponse refreshToken(TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(MongoRefreshToken::getUser)
                .map(user -> {
                    String token = tokenProvider.generateTokenFromUsername(user.getUsername());
                    return new JwtAuthenticationResponse(token, requestRefreshToken);
                })
                .orElseThrow(() -> new RuntimeException("Refresh token not found or expired"));
    }

    @Transactional
    public void logoutUser(LogoutRequest logoutRequest) {
        refreshTokenService.deleteByToken(logoutRequest.getRefreshToken());
    }

    @Transactional
    public void initiatePasswordReset(PasswordResetRequest passwordResetRequest) {
        MongoUser user = userRepository.findByEmail(passwordResetRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Email not found"));

        String token = UUID.randomUUID().toString();
        MongoPasswordResetToken mongoPasswordResetToken = new MongoPasswordResetToken();
        mongoPasswordResetToken.setToken(token);
        mongoPasswordResetToken.setUser(user);
        mongoPasswordResetToken.setExpiryDate(Instant.now().plusSeconds(3600)); // 1 hour expiry

        passwordResetTokenRepository.save(mongoPasswordResetToken);

      }

    @Transactional
    public void changePassword(PasswordChangeRequest passwordChangeRequest) {
        validatePassword(String.valueOf(passwordChangeRequest));
        MongoPasswordResetToken mongoPasswordResetToken = passwordResetTokenRepository.findByToken(passwordChangeRequest.getToken())
                .orElseThrow(() -> new RuntimeException("Invalid password reset token"));

        if (mongoPasswordResetToken.getExpiryDate().isBefore(Instant.now())) {
            throw new RuntimeException("Password reset token expired");
        }

        MongoUser user = mongoPasswordResetToken.getUser();
        user.setPassword(passwordEncoder.encode(passwordChangeRequest.getNewPassword()));

        userRepository.save(user);

        passwordResetTokenRepository.delete(mongoPasswordResetToken);
    }

    private void validatePassword(String password) {
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new IllegalArgumentException("Password must contain at least one uppercase letter");
        }
        if (!password.matches(".*[0-9].*")) {
            throw new IllegalArgumentException("Password must contain at least one number");
        }
    }

    @Transactional
    public void verifyEmail(String token) {
        MongoVerificationToken verificationToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid verification token"));

        MongoUser user = verificationToken.getUser();
        user.setEnabled(true);

        userRepository.save(user);

        verificationTokenRepository.delete(verificationToken);
    }

    private String generateVerificationToken(MongoUser user) {
        String token = UUID.randomUUID().toString();

        MongoVerificationToken verificationToken = new MongoVerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(Instant.now().plusSeconds(86400)); // 24 hours expiry

        verificationTokenRepository.save(verificationToken);

        return token;
    }
}

package orsk.compli.service.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import orsk.compli.entities.mongo.MongoRefreshToken;
import orsk.compli.entities.mongo.MongoUser;
import orsk.compli.repository.mongo.RefreshTokenMongoRepository;
import orsk.compli.repository.mongo.UserMongoRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@Component("mongoRefreshTokenService")
public class RefreshTokenMongoService {

    @Autowired
    private RefreshTokenMongoRepository refreshTokenRepository;

    @Autowired
    private UserMongoRepository userRepository;

    public Optional<MongoRefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public MongoRefreshToken createRefreshToken(String username) {
        MongoRefreshToken refreshToken = new MongoRefreshToken();

        MongoUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        refreshToken.setUser(user);
        refreshToken.setExpiryDate(Instant.now().plusSeconds(604800)); // 7 days
        refreshToken.setToken(UUID.randomUUID().toString());

        return refreshTokenRepository.save(refreshToken);
    }

    public MongoRefreshToken verifyExpiration(MongoRefreshToken token) {
        if (token.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh token expired");
        }

        return token;
    }

    @Transactional
    public int deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    @Transactional
    public void deleteByToken(String token) {
        refreshTokenRepository.findByToken(token).ifPresent(refreshTokenRepository::delete);
    }
}

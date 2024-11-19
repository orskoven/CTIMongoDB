package orsk.compli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import orsk.compli.entities.mongo.MongoUser;
import orsk.compli.repository.mongo.UserMongoRepository;
;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("MongoCustomUserDetailsService")
public class CustomUserDetailsMongoService implements UserDetailsService {

    @Autowired
    private UserMongoRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        MongoUser user = userRepository.findByUsername(usernameOrEmail)
                .or(() -> userRepository.findByEmail(usernameOrEmail))
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + usernameOrEmail));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getEnabled(),
                true,
                true,
                true,
                user.getRoles().stream()
                        .filter(role -> role != null) // Skip null roles
                        .flatMap(role -> {
                            if (role.getPrivileges() != null) {
                                return role.getPrivileges().stream();
                            } else {
                                return Stream.empty(); // Handle null privileges
                            }
                        })
                        .map(privilege -> new SimpleGrantedAuthority(privilege.getName()))
                        .collect(Collectors.toList()));
    }
}

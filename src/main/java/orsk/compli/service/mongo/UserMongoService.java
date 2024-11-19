package orsk.compli.service.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import orsk.compli.entities.mongo.MongoRole;
import orsk.compli.entities.mongo.MongoUser;
import orsk.compli.repository.mongo.RoleMongoRepository;
import orsk.compli.repository.mongo.UserMongoRepository;

import java.util.Collections;

@Service
@Component("mongoUserService")
public class UserMongoService {

    @Autowired
    private UserMongoRepository userRepository;

    @Autowired
    private RoleMongoRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public MongoUser registerNewUser(MongoUser user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(false); // User needs to be enabled after registration or email verification
        MongoRole userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("ROLE_USER not found"));
        user.setRoles(Collections.singleton(userRole));
        return userRepository.save(user);
    }
}

package orsk.compli.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import orsk.compli.entities.mongo.MongoUser;

import java.util.Optional;

@Repository("mongoUserRepositoryRepository")
public interface UserMongoRepository extends MongoRepository<MongoUser, Long> {
    Optional<MongoUser> findByUsername(String username);

    Optional<MongoUser> findByEmail(String email);
}

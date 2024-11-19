package orsk.compli.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import orsk.compli.entities.mongo.MongoPasswordResetToken;

import java.util.Optional;

@Repository("mongoPasswordResetTokenRepositoryRepository")
public interface PasswordResetTokenMongoRepository extends MongoRepository<MongoPasswordResetToken, Long> {
    Optional<MongoPasswordResetToken> findByToken(String token);
}

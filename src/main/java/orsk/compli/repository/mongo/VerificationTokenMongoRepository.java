package orsk.compli.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import orsk.compli.entities.mongo.MongoUser;
import orsk.compli.entities.mongo.MongoVerificationToken;

import java.util.Optional;

@Repository("mongoVerificationTokenRepositoryRepository")
public interface VerificationTokenMongoRepository extends MongoRepository<MongoVerificationToken, Long> {
    Optional<MongoVerificationToken> findByToken(String token);

    Optional<MongoVerificationToken> findByUser(MongoUser user);
}

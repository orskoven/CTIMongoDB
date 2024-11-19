package orsk.compli.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import orsk.compli.entities.mongo.MongoRefreshToken;
import orsk.compli.entities.mongo.MongoUser;

import java.util.Optional;

@Repository("mongoRefreshTokenRepositoryRepository")
public interface RefreshTokenMongoRepository extends MongoRepository<MongoRefreshToken, Long> {
    Optional<MongoRefreshToken> findByToken(String token);

    int deleteByUser(MongoUser user);
}

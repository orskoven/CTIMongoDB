package orsk.compli.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import orsk.compli.entities.mongo.MongoRole;

import java.util.Optional;

@Repository("mongoRoleRepositoryRepository")
public interface RoleMongoRepository extends MongoRepository<MongoRole, Long> {
    @Query("{ 'name': ?0 }")
    Optional<MongoRole> findByName(String name);
}

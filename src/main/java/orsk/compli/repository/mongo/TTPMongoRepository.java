package orsk.compli.repository.mongo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import orsk.compli.entities.mongo.MongoTTP;

@Repository("mongoTTPRepositoryRepository")
public interface TTPMongoRepository extends MongoRepository<MongoTTP, ObjectId> {
}


package orsk.compli.repository.mongo;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import orsk.compli.entities.mongo.MongoIndicator;

@Repository("mongoIndicatorRepositoryRepository")
public interface IndicatorMongoRepository extends MongoRepository<MongoIndicator, ObjectId> {
}


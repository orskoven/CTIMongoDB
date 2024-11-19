package orsk.compli.service.mongo;

import org.bson.types.ObjectId;
import orsk.compli.entities.mongo.MongoAttackPattern;

public interface AttackPatternMongoService extends CrudMongoService<MongoAttackPattern, ObjectId> {
    // Add any additional methods specific to MongoAttackPattern if needed
}


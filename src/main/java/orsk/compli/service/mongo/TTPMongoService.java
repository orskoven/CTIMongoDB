package orsk.compli.service.mongo;

import org.bson.types.ObjectId;
import orsk.compli.entities.mongo.MongoTTP;

public interface TTPMongoService extends CrudMongoService<MongoTTP, ObjectId> {
    // Add any additional methods specific to TTP if needed
}


package orsk.compli.service.mongo;

import org.bson.types.ObjectId;
import orsk.compli.entities.mongo.MongoGlobalThreat;

public interface GlobalThreatMongoService extends CrudMongoService<MongoGlobalThreat, ObjectId> {
    // Add any additional methods specific to GlobalThreat if needed
}


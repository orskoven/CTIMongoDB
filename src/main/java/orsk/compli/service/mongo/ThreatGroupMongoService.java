package orsk.compli.service.mongo;


import org.bson.types.ObjectId;
import orsk.compli.entities.mongo.MongoThreatGroup;

public interface ThreatGroupMongoService extends CrudMongoService<MongoThreatGroup, ObjectId> {
    // Add any additional methods specific to ThreatGroup if needed
}


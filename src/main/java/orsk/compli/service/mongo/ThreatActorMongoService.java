package orsk.compli.service.mongo;

import org.bson.types.ObjectId;
import orsk.compli.entities.mongo.MongoThreatActor;

public interface ThreatActorMongoService extends CrudMongoService<MongoThreatActor, ObjectId> {
    // Add any additional methods specific to MongoThreatActor if needed
}


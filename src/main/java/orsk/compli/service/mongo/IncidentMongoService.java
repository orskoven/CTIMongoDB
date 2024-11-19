package orsk.compli.service.mongo;

import org.bson.types.ObjectId;
import orsk.compli.entities.mongo.MongoIncident;

public interface IncidentMongoService extends CrudMongoService<MongoIncident, ObjectId> {
    // Add any additional methods specific to Incident if needed
}


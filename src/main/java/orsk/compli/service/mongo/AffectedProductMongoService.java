package orsk.compli.service.mongo;

import org.bson.types.ObjectId;
import orsk.compli.entities.mongo.MongoAffectedProduct;

public interface AffectedProductMongoService extends CrudMongoService<MongoAffectedProduct, ObjectId> {
    // Add any additional methods specific to AffectedProduct if needed
}


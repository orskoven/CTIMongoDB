package orsk.compli.service.mongo;

import org.bson.types.ObjectId;
import orsk.compli.entities.mongo.MongoIndicator;

public interface IndicatorMongoService extends CrudMongoService<MongoIndicator, ObjectId> {
    // Add any additional methods specific to Indicator if needed
}


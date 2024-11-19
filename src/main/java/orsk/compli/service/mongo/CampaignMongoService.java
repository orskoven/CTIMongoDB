package orsk.compli.service.mongo;

import org.bson.types.ObjectId;
import orsk.compli.entities.mongo.MongoCampaign;

public interface CampaignMongoService extends CrudMongoService<MongoCampaign, ObjectId> {
    // Add any additional methods specific to Campaign if needed
}


package orsk.compli.service.mongo;

import org.bson.types.ObjectId;
import orsk.compli.entities.mongo.MongoCountry;

public interface CountryMongoService extends CrudMongoService<MongoCountry, ObjectId> {
    // Add any additional methods specific to Country if needed
}


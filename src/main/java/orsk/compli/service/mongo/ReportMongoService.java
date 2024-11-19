package orsk.compli.service.mongo;

import org.bson.types.ObjectId;
import orsk.compli.entities.mongo.MongoReport;

public interface ReportMongoService extends CrudMongoService<MongoReport, ObjectId> {
    // Add any additional methods specific to Report if needed
}


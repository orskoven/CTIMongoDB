package orsk.compli.controller.mongo;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orsk.compli.entities.mongo.MongoIncident;
import orsk.compli.service.mongo.CrudMongoService;
import orsk.compli.service.mongo.IncidentMongoService;

@RestController("mongoIncidentController")
@RequestMapping("/api/mongo/incidents")
public class IncidentControllerMongoMongo extends AbstractCrudControllerMongo<MongoIncident, ObjectId> {

    private static final Logger logger = LoggerFactory.getLogger(IncidentControllerMongoMongo.class);
    private final IncidentMongoService service;

    public IncidentControllerMongoMongo(@Qualifier("mongoIncidentServiceImpl") IncidentMongoService service) {
        this.service = service;
    }

    @Override
    protected CrudMongoService<MongoIncident, ObjectId> getService() {
        return this.service;
    }
}


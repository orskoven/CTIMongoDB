package orsk.compli.controller.mongo;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orsk.compli.entities.mongo.MongoGlobalThreat;
import orsk.compli.service.mongo.CrudMongoService;
import orsk.compli.service.mongo.GlobalThreatMongoService;

@RestController("mongoGlobalThreatController")
@RequestMapping("/api/mongo/globalthreats")
public class GlobalThreatControllerMongoMongo extends AbstractCrudControllerMongo<MongoGlobalThreat, ObjectId> {

    private static final Logger logger = LoggerFactory.getLogger(GlobalThreatControllerMongoMongo.class);
    private final GlobalThreatMongoService service;

    public GlobalThreatControllerMongoMongo(@Qualifier("mongoGlobalThreatServiceImpl") GlobalThreatMongoService service) {
        this.service = service;
    }

    @Override
    protected CrudMongoService<MongoGlobalThreat, ObjectId> getService() {
        return this.service;
    }
}


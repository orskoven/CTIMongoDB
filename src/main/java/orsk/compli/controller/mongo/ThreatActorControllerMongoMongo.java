package orsk.compli.controller.mongo;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orsk.compli.entities.mongo.MongoThreatActor;
import orsk.compli.service.mongo.CrudMongoService;
import orsk.compli.service.mongo.ThreatActorMongoService;

@RestController("mongoThreatActorController")
@RequestMapping("/api/mongo/threatactors")
public class ThreatActorControllerMongoMongo extends AbstractCrudControllerMongo<MongoThreatActor, ObjectId> {

    private static final Logger logger = LoggerFactory.getLogger(ThreatActorControllerMongoMongo.class);
    private final ThreatActorMongoService service;

    public ThreatActorControllerMongoMongo(@Qualifier("mongoThreatActorServiceImpl") ThreatActorMongoService service) {
        this.service = service;
    }

    @Override
    protected CrudMongoService<MongoThreatActor, ObjectId> getService() {
        return this.service;
    }
}


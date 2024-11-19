package orsk.compli.controller.mongo;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orsk.compli.entities.mongo.MongoThreatGroup;
import orsk.compli.service.mongo.CrudMongoService;
import orsk.compli.service.mongo.ThreatGroupMongoService;

@RestController("mongoThreatGroupController")
@RequestMapping("/api/mongo/threatgroups")
public class ThreatGroupControllerMongoMongo extends AbstractCrudControllerMongo<MongoThreatGroup, ObjectId> {

    private static final Logger logger = LoggerFactory.getLogger(ThreatGroupControllerMongoMongo.class);
    private final ThreatGroupMongoService service;

    public ThreatGroupControllerMongoMongo(@Qualifier("mongoThreatGroupServiceImpl") ThreatGroupMongoService service) {
        this.service = service;
    }

    @Override
    protected CrudMongoService<MongoThreatGroup, ObjectId> getService() {
        return this.service;
    }
}


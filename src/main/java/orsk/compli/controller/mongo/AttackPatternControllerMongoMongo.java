package orsk.compli.controller.mongo;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orsk.compli.entities.mongo.MongoAttackPattern;
import orsk.compli.service.mongo.AttackPatternMongoService;
import orsk.compli.service.mongo.CrudMongoService;

@RestController("mongoAttackPatternController")
@RequestMapping("/api/mongo/attackpatterns")
public class AttackPatternControllerMongoMongo extends AbstractCrudControllerMongo<MongoAttackPattern, ObjectId> {

    private static final Logger logger = LoggerFactory.getLogger(AttackPatternControllerMongoMongo.class);
    private final AttackPatternMongoService service;

    public AttackPatternControllerMongoMongo(@Qualifier("mongoAttackPatternServiceImpl") AttackPatternMongoService service) {
        this.service = service;
    }

    @Override
    protected CrudMongoService<MongoAttackPattern, ObjectId> getService() {
        return this.service;
    }
}


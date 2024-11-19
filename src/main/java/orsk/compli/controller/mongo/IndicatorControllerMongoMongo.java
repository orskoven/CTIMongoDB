package orsk.compli.controller.mongo;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orsk.compli.entities.mongo.MongoIndicator;
import orsk.compli.service.mongo.CrudMongoService;
import orsk.compli.service.mongo.IndicatorMongoService;

@RestController("mongoIndicatorController")
@RequestMapping("/api/mongo/indicators")
public class IndicatorControllerMongoMongo extends AbstractCrudControllerMongo<MongoIndicator, ObjectId> {

    private static final Logger logger = LoggerFactory.getLogger(IndicatorControllerMongoMongo.class);
    private final IndicatorMongoService service;

    public IndicatorControllerMongoMongo(@Qualifier("mongoIndicatorServiceImpl") IndicatorMongoService service) {
        this.service = service;
    }

    @Override
    protected CrudMongoService<MongoIndicator, ObjectId> getService() {
        return this.service;
    }
}


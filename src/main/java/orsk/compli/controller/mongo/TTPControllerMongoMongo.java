package orsk.compli.controller.mongo;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orsk.compli.entities.mongo.MongoTTP;
import orsk.compli.service.mongo.CrudMongoService;
import orsk.compli.service.mongo.TTPMongoService;

@RestController("mongoTTPController")
@RequestMapping("/api/mongo/ttps")
public class TTPControllerMongoMongo extends AbstractCrudControllerMongo<MongoTTP, ObjectId> {

    private static final Logger logger = LoggerFactory.getLogger(TTPControllerMongoMongo.class);
    private final TTPMongoService service;

    public TTPControllerMongoMongo(@Qualifier("mongoTTPServiceImpl") TTPMongoService service) {
        this.service = service;
    }

    @Override
    protected CrudMongoService<MongoTTP, ObjectId> getService() {
        return this.service;
    }
}


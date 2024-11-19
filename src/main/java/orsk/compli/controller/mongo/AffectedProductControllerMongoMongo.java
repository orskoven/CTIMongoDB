package orsk.compli.controller.mongo;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orsk.compli.entities.mongo.MongoAffectedProduct;
import orsk.compli.service.mongo.AffectedProductMongoService;
import orsk.compli.service.mongo.CrudMongoService;

@RestController("mongoAffectedProductController")
@RequestMapping("/api/mongo/affectedproducts")
public class AffectedProductControllerMongoMongo extends AbstractCrudControllerMongo<MongoAffectedProduct, ObjectId> {

    private static final Logger logger = LoggerFactory.getLogger(AffectedProductControllerMongoMongo.class);
    private final AffectedProductMongoService service;

    public AffectedProductControllerMongoMongo(@Qualifier("mongoAffectedProductServiceImpl") AffectedProductMongoService service) {
        this.service = service;
    }

    @Override
    protected CrudMongoService<MongoAffectedProduct, ObjectId> getService() {
        return this.service;
    }
}


package orsk.compli.controller.mongo;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orsk.compli.entities.mongo.MongoCountry;
import orsk.compli.service.mongo.CountryMongoService;
import orsk.compli.service.mongo.CrudMongoService;

@RestController("mongoCountryController")
@RequestMapping("/api/mongo/countries")
public class CountryControllerMongoMongo extends AbstractCrudControllerMongo<MongoCountry, ObjectId> {

    private static final Logger logger = LoggerFactory.getLogger(CountryControllerMongoMongo.class);
    private final CountryMongoService service;

    public CountryControllerMongoMongo(@Qualifier("mongoCountryServiceImpl") CountryMongoService service) {
        this.service = service;
    }

    @Override
    protected CrudMongoService<MongoCountry, ObjectId> getService() {
        return this.service;
    }
}


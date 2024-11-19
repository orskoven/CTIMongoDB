package orsk.compli.controller.mongo;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orsk.compli.entities.mongo.MongoCampaign;
import orsk.compli.service.mongo.CampaignMongoService;
import orsk.compli.service.mongo.CampaignMongoServiceImpl;
import orsk.compli.service.mongo.CrudMongoService;

@RestController("mongoCampaignController")
@RequestMapping("/api/mongo/campaigns")
public class CampaignControllerMongoMongo extends AbstractCrudControllerMongo<MongoCampaign, ObjectId> {

    private static final Logger logger = LoggerFactory.getLogger(CampaignControllerMongoMongo.class);
    private final CampaignMongoServiceImpl service;

    public CampaignControllerMongoMongo(@Qualifier("mongoCampaignServiceImpl") CampaignMongoService service) {
        this.service = (CampaignMongoServiceImpl) service;
    }

    @Override
    protected CrudMongoService<MongoCampaign, ObjectId> getService() {
        return this.service;
    }
}


package orsk.compli.controller.mongo;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import orsk.compli.entities.mongo.MongoReport;
import orsk.compli.service.mongo.CrudMongoService;
import orsk.compli.service.mongo.ReportMongoService;

@RestController("mongoReportController")
@RequestMapping("/api/mongo/reports")
public class ReportControllerMongoMongo extends AbstractCrudControllerMongo<MongoReport, ObjectId> {

    private static final Logger logger = LoggerFactory.getLogger(ReportControllerMongoMongo.class);
    private final ReportMongoService service;

    public ReportControllerMongoMongo(@Qualifier("mongoReportServiceImpl") ReportMongoService service) {
        this.service = service;
    }

    @Override
    protected CrudMongoService<MongoReport, ObjectId> getService() {
        return this.service;
    }
}


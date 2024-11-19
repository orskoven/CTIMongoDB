package orsk.compli.service.mongo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import orsk.compli.entities.mongo.MongoReport;
import orsk.compli.repository.mongo.ReportMongoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Component("mongoReportServiceImpl")
public class ReportMongoServiceImpl implements ReportMongoService {

    private final ReportMongoRepository repository;

    @Autowired
    public ReportMongoServiceImpl(ReportMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public MongoReport create(MongoReport entity) {
        entity.setCreatedAt(java.time.LocalDateTime.now());
        entity.setUpdatedAt(java.time.LocalDateTime.now());
        return repository.save(entity);
    }

    @Override
    public List<MongoReport> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<MongoReport> getById(ObjectId id) {
        return repository.findById(id);
    }

    @Override
    public MongoReport update(ObjectId id, MongoReport entity) {
        entity.setId(id);
        entity.setUpdatedAt(java.time.LocalDateTime.now());
        return repository.save(entity);
    }

    @Override
    public boolean delete(ObjectId id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}


package orsk.compli.service.mongo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import orsk.compli.entities.mongo.MongoGlobalThreat;
import orsk.compli.repository.mongo.GlobalThreatMongoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Component("mongoGlobalThreatServiceImpl")
public class GlobalThreatMongoServiceImpl implements GlobalThreatMongoService {

    private final GlobalThreatMongoRepository repository;

    @Autowired
    public GlobalThreatMongoServiceImpl(GlobalThreatMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public MongoGlobalThreat create(MongoGlobalThreat entity) {
        entity.setCreatedAt(java.time.LocalDateTime.now());
        entity.setUpdatedAt(java.time.LocalDateTime.now());
        return repository.save(entity);
    }

    @Override
    public List<MongoGlobalThreat> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<MongoGlobalThreat> getById(ObjectId id) {
        return repository.findById(id);
    }

    @Override
    public MongoGlobalThreat update(ObjectId id, MongoGlobalThreat entity) {
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


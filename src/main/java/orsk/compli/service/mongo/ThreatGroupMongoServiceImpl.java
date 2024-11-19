package orsk.compli.service.mongo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import orsk.compli.entities.mongo.MongoThreatGroup;
import orsk.compli.repository.mongo.ThreatGroupMongoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Component("mongoThreatGroupServiceImpl")
public class ThreatGroupMongoServiceImpl implements ThreatGroupMongoService {

    private final ThreatGroupMongoRepository repository;

    @Autowired
    public ThreatGroupMongoServiceImpl(ThreatGroupMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public MongoThreatGroup create(MongoThreatGroup entity) {
        entity.setCreatedAt(java.time.LocalDateTime.now());
        entity.setUpdatedAt(java.time.LocalDateTime.now());
        return repository.save(entity);
    }

    @Override
    public List<MongoThreatGroup> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<MongoThreatGroup> getById(ObjectId id) {
        return repository.findById(id);
    }

    @Override
    public MongoThreatGroup update(ObjectId id, MongoThreatGroup entity) {
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


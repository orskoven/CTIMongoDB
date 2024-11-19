package orsk.compli.service.mongo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import orsk.compli.entities.mongo.MongoIndicator;
import orsk.compli.repository.mongo.IndicatorMongoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Component("mongoIndicatorServiceImpl")
public class IndicatorMongoServiceImpl implements IndicatorMongoService {

    private final IndicatorMongoRepository repository;

    @Autowired
    public IndicatorMongoServiceImpl(IndicatorMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public MongoIndicator create(MongoIndicator entity) {
        entity.setCreatedAt(java.time.LocalDateTime.now());
        entity.setUpdatedAt(java.time.LocalDateTime.now());
        return repository.save(entity);
    }

    @Override
    public List<MongoIndicator> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<MongoIndicator> getById(ObjectId id) {
        return repository.findById(id);
    }

    @Override
    public MongoIndicator update(ObjectId id, MongoIndicator entity) {
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


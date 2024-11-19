package orsk.compli.service.mongo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import orsk.compli.entities.mongo.MongoTTP;
import orsk.compli.repository.mongo.TTPMongoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Component("mongoTTPServiceImpl")
public class TTPMongoServiceImpl implements TTPMongoService {

    private final TTPMongoRepository repository;

    @Autowired
    public TTPMongoServiceImpl(TTPMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public MongoTTP create(MongoTTP entity) {
        entity.setCreatedAt(java.time.LocalDateTime.now());
        entity.setUpdatedAt(java.time.LocalDateTime.now());
        return repository.save(entity);
    }

    @Override
    public List<MongoTTP> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<MongoTTP> getById(ObjectId id) {
        return repository.findById(id);
    }

    @Override
    public MongoTTP update(ObjectId id, MongoTTP entity) {
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


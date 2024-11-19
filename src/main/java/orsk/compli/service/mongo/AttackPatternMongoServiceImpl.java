package orsk.compli.service.mongo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import orsk.compli.entities.mongo.MongoAttackPattern;
import orsk.compli.repository.mongo.AttackPatternMongoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Component("mongoAttackPatternServiceImpl")
public class AttackPatternMongoServiceImpl implements AttackPatternMongoService {

    private final AttackPatternMongoRepository repository;

    @Autowired
    public AttackPatternMongoServiceImpl(AttackPatternMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public MongoAttackPattern create(MongoAttackPattern entity) {
        entity.setCreatedAt(java.time.LocalDateTime.now());
        entity.setUpdatedAt(java.time.LocalDateTime.now());
        return repository.save(entity);
    }

    @Override
    public List<MongoAttackPattern> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<MongoAttackPattern> getById(ObjectId id) {
        return repository.findById(id);
    }

    @Override
    public MongoAttackPattern update(ObjectId id, MongoAttackPattern entity) {
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


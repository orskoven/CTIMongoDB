package orsk.compli.service.mongo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import orsk.compli.entities.mongo.MongoAffectedProduct;
import orsk.compli.repository.mongo.AffectedProductMongoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Component("mongoAffectedProductServiceImpl")
public class AffectedProductMongoServiceImpl implements AffectedProductMongoService {

    private final AffectedProductMongoRepository repository;

    @Autowired
    public AffectedProductMongoServiceImpl(AffectedProductMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public MongoAffectedProduct create(MongoAffectedProduct entity) {
        entity.setCreatedAt(java.time.LocalDateTime.now());
        entity.setUpdatedAt(java.time.LocalDateTime.now());
        return repository.save(entity);
    }

    @Override
    public List<MongoAffectedProduct> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<MongoAffectedProduct> getById(ObjectId id) {
        return repository.findById(id);
    }

    @Override
    public MongoAffectedProduct update(ObjectId id, MongoAffectedProduct entity) {
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


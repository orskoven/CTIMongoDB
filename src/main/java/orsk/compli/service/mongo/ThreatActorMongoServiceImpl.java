package orsk.compli.service.mongo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import orsk.compli.entities.mongo.MongoThreatActor;
import orsk.compli.repository.mongo.ThreatActorMongoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Component("mongoThreatActorServiceImpl")
public class ThreatActorMongoServiceImpl implements ThreatActorMongoService {

    private final ThreatActorMongoRepository repository;

    @Autowired
    public ThreatActorMongoServiceImpl(ThreatActorMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public MongoThreatActor create(MongoThreatActor entity) {
        entity.setCreatedAt(java.time.LocalDateTime.now());
        entity.setUpdatedAt(java.time.LocalDateTime.now());
        return repository.save(entity);
    }

    @Override
    public List<MongoThreatActor> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<MongoThreatActor> getById(ObjectId id) {
        return repository.findById(id);
    }

    @Override
    public MongoThreatActor update(ObjectId id, MongoThreatActor entity) {
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


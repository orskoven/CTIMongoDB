package orsk.compli.service.mongo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import orsk.compli.entities.mongo.MongoIncident;
import orsk.compli.repository.mongo.IncidentMongoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Component("mongoIncidentServiceImpl")
public class IncidentMongoServiceImpl implements IncidentMongoService {

    private final IncidentMongoRepository repository;

    @Autowired
    public IncidentMongoServiceImpl(IncidentMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public MongoIncident create(MongoIncident entity) {
        entity.setCreatedAt(java.time.LocalDateTime.now());
        entity.setUpdatedAt(java.time.LocalDateTime.now());
        return repository.save(entity);
    }

    @Override
    public List<MongoIncident> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<MongoIncident> getById(ObjectId id) {
        return repository.findById(id);
    }

    @Override
    public MongoIncident update(ObjectId id, MongoIncident entity) {
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


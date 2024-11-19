package orsk.compli.service.mongo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import orsk.compli.entities.mongo.MongoCampaign;
import orsk.compli.repository.mongo.CampaignMongoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Component("mongoCampaignServiceImpl")
public class CampaignMongoServiceImpl implements CampaignMongoService {

    private final CampaignMongoRepository repository;

    @Autowired
    public CampaignMongoServiceImpl(CampaignMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public MongoCampaign create(MongoCampaign entity) {
        entity.setCreatedAt(java.time.LocalDateTime.now());
        entity.setUpdatedAt(java.time.LocalDateTime.now());
        return repository.save(entity);
    }

    @Override
    public List<MongoCampaign> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<MongoCampaign> getById(ObjectId id) {
        return repository.findById(id);
    }

    @Override
    public MongoCampaign update(ObjectId id, MongoCampaign entity) {
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


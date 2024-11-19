package orsk.compli.service.mongo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import orsk.compli.entities.mongo.MongoCountry;
import orsk.compli.repository.mongo.CountryMongoRepository;

import java.util.List;
import java.util.Optional;

@Service
@Component("mongoCountryServiceImpl")
public class CountryMongoServiceImpl implements CountryMongoService {

    private final CountryMongoRepository repository;

    @Autowired
    public CountryMongoServiceImpl(CountryMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public MongoCountry create(MongoCountry entity) {
        entity.setCreatedAt(java.time.LocalDateTime.now());
        entity.setUpdatedAt(java.time.LocalDateTime.now());
        return repository.save(entity);
    }

    @Override
    public List<MongoCountry> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<MongoCountry> getById(ObjectId id) {
        return repository.findById(id);
    }

    @Override
    public MongoCountry update(ObjectId id, MongoCountry entity) {
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


package orsk.compli.controller.mongo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import orsk.compli.service.mongo.CrudMongoService;

import java.util.List;

public abstract class AbstractCrudControllerMongo<T, ID> {

    private static final Logger logger = LoggerFactory.getLogger(AbstractCrudControllerMongo.class);

    protected abstract CrudMongoService<T, ID> getService();

    @PostMapping
    public ResponseEntity<T> create(@RequestBody T entity) {
        try {
            T created = getService().create(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (DataAccessException e) {
            logger.error("Error creating entity: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating entity");
        }
    }

    @GetMapping
    public ResponseEntity<List<T>> getAll() {
        try {
            List<T> entities = getService().getAll();
            if (entities.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(entities);
        } catch (DataAccessException e) {
            logger.error("Error retrieving entities: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving entities");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> getById(@PathVariable ID id) {
        try {
            return getService().getById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (DataAccessException e) {
            logger.error("Error retrieving entity by ID {}: {}", id, e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving entity");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable ID id, @RequestBody T entity) {
        try {
            T updated = getService().update(id, entity);
            return ResponseEntity.ok(updated);
        } catch (DataAccessException e) {
            logger.error("Error updating entity with ID {}: {}", id, e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating entity");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        try {
            boolean deleted = getService().delete(id);
            return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
        } catch (DataAccessException e) {
            logger.error("Error deleting entity with ID {}: {}", id, e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting entity");
        }
    }
}

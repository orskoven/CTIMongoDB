package orsk.compli.service.mongo;

import java.util.List;
import java.util.Optional;

public interface CrudMongoService<T, ID> {
    T create(T entity);

    List<T> getAll();

    Optional<T> getById(ID id);

    T update(ID id, T entity);

    boolean delete(ID id);
}

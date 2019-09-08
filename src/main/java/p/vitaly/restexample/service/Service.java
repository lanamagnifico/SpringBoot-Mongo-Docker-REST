package p.vitaly.restexample.service;

import java.util.List;
import java.util.Map;

public interface Service<T, ID> {
    T getById(ID id);
    List<T> getAll();
    List<T> getAllWithParams(Map<String, String> parameters);
    void save(T object);
    void delete(ID id);
}

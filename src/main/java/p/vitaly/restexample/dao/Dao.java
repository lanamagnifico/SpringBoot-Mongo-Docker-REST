package p.vitaly.restexample.dao;

import java.util.List;
import java.util.Map;

public interface Dao<ENTITY, ID> {
    ENTITY findById(ID id);
    List<ENTITY> findAll();
    List<ENTITY> findAllWithParams(Map<String, String> parameters);
    void save(ENTITY entity);
    void deleteById(ID id);
}

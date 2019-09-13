package p.vitaly.restexample.dao;

import p.vitaly.restexample.exception.EntityNotFoundException;

import java.util.List;
import java.util.Map;

public interface Dao<ENTITY, ID> {
    ENTITY findById(ID id) throws EntityNotFoundException;
    List<ENTITY> findAll();
    List<ENTITY> findAllWithParams(Map<String, String> parameters);
    void save(ENTITY entity);
    void deleteById(ID id) throws EntityNotFoundException;
}

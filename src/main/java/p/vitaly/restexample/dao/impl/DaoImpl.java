package p.vitaly.restexample.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import p.vitaly.restexample.dao.Dao;
import p.vitaly.restexample.entity.BasicEntity;
import p.vitaly.restexample.exception.EntityNotFoundException;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class DaoImpl<ENTITY extends BasicEntity, ID> implements Dao<ENTITY, ID> {
    @Autowired
    EntityManager em;

    Class<ENTITY> entityClass;

    DaoImpl(Class<ENTITY> clazz) {
        this.entityClass = clazz;
    }

    @Override
    public ENTITY findById(ID id) throws EntityNotFoundException {
        ENTITY entity = em.find(entityClass, id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        return entity;
    }

    @Override
    public List<ENTITY> findAll() {
        return em.createQuery("from " + entityClass.getName(), entityClass).getResultList();
    }

    @Override
    public void save(ENTITY entity) {
        em.getTransaction().begin();
        if (entity.getId() == null) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
        em.flush();
        em.getTransaction().commit();
    }

    @Override
    public void deleteById(ID id) throws EntityNotFoundException {
        em.getTransaction().begin();
        ENTITY entity = findById(id);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        em.remove(entity);
        em.flush();
        em.getTransaction().commit();
    }
}

package com.nttdata.idmccnobe.dao;

import java.io.Serializable;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.apache.log4j.Logger;

public abstract class AbstractDao<PK extends Serializable, T> {
    
    protected Logger logger = Logger.getLogger(this.getClass());
    
    private final Class<T> persistentClass;
    
    @SuppressWarnings("unchecked")
    public AbstractDao(){
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
    
    @PersistenceContext
    protected EntityManager entityManager;
    
    protected EntityManager getEntityManager(){
        return entityManager;
    }
    
    @SuppressWarnings("unchecked")
    public T getByKey(PK key) {
        return getEntityManager().find(persistentClass, key);
    }
    
    public void persist(T entity) {
        getEntityManager().persist(entity);
    }
    
    public T merge(T entity) {
        return getEntityManager().merge(entity);
    }
    
    public void saveOrUpdate(T entity) {
        getEntityManager().merge(entity);
    }
    
    public void delete(T entity) {
        getEntityManager().remove(getEntityManager().contains(entity) ? entity : getEntityManager().merge(entity));
    }
    
    public void delete(List<T> list) {
        list.forEach(item->delete(item));
    }
    
    protected CriteriaBuilder criteriaBuilder(){
        return getEntityManager().getCriteriaBuilder();
    }
    
    public List<T> findAll() {
        CriteriaBuilder builder = criteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(persistentClass);
        query.from(persistentClass);
        return getEntityManager().createQuery(query).getResultList();
    }
    
}

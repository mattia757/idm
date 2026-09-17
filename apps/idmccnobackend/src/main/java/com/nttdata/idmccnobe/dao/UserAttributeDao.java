package com.nttdata.idmccnobe.dao;

import com.nttdata.idmccnobe.model.UserAttribute;
import java.util.List;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

/**
 *
 * @author FacchettiM
 */
@Repository("UserAttributeDao")
public class UserAttributeDao extends AbstractDao<String, UserAttribute>{
    
    public List<UserAttribute> getAttributesByNameAndValue(String name, String value) {
        CriteriaBuilder builder = criteriaBuilder();
        CriteriaQuery<UserAttribute> query = builder.createQuery(UserAttribute.class);
        Root<UserAttribute> root = query.from(UserAttribute.class);
        query.where(builder.equal(root.get("name"), name),
                builder.like(builder.lower(root.get("value")), "%" + value.toLowerCase() + "%"));
        return getEntityManager().createQuery(query).getResultList();
    }
    
    public UserAttribute getAttributeByName(String name) {
        CriteriaBuilder builder = criteriaBuilder();
        CriteriaQuery<UserAttribute> query = builder.createQuery(UserAttribute.class);
        Root<UserAttribute> root = query.from(UserAttribute.class);
        query.where(builder.equal(root.get("name"), name));
        return getEntityManager().createQuery(query).getSingleResultOrNull();
    }
}

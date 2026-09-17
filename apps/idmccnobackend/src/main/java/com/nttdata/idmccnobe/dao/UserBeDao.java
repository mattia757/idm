package com.nttdata.idmccnobe.dao;

import com.nttdata.idmccnobe.dto.UserBeRequest;
import com.nttdata.idmccnobe.model.UserBe;
import com.nttdata.idmccnobe.util.CommonUtils;
import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/**
 *
 * @author DelorenziVa
 */
@Repository("UserBeDao")
public class UserBeDao extends AbstractDao<Integer, UserBe> {
    
    public UserBe findByUsernameAndPassword(String username, String password) {
        CriteriaBuilder builder = criteriaBuilder();
        CriteriaQuery<UserBe> query = builder.createQuery(UserBe.class);
        Root<UserBe> root = query.from(UserBe.class);
        query.where(builder.equal(builder.lower(root.get("username")), username.toLowerCase()),
                builder.equal(builder.lower(root.get("password")), password.toLowerCase()));
        return getEntityManager().createQuery(query).getSingleResultOrNull();
    }
    
    public UserBe findByUsername(String username) {
        CriteriaBuilder builder = criteriaBuilder();
        CriteriaQuery<UserBe> query = builder.createQuery(UserBe.class);
        Root<UserBe> root = query.from(UserBe.class);
        query.where(builder.equal(builder.lower(root.get("username")), username.toLowerCase()));
        return getEntityManager().createQuery(query).getSingleResultOrNull();
    }
    
    public List<UserBe> find(String username, String coop, String role, String customerCancellation) {
        CriteriaBuilder builder = criteriaBuilder();
        CriteriaQuery<UserBe> query = builder.createQuery(UserBe.class);
        Root<UserBe> root = query.from(UserBe.class);
        List<Predicate> predicates = new ArrayList<>();
        if(!CommonUtils.isNullOrEmpty(username)) {
            predicates.add(builder.like(builder.lower(root.get("username")), "%" + username.toLowerCase() + "%"));
        }
        if(!CommonUtils.isNullOrEmpty(coop)) {
            Join<Object, Object> cooperativa = root.join("cooperativa");
            predicates.add(builder.equal(cooperativa.get("numeroCooperativa"), Integer.valueOf(coop)));
        }
        if(!CommonUtils.isNullOrEmpty(role)) {
            predicates.add(builder.equal(builder.lower(root.get("role")), role.toLowerCase()));
        }
        if(!CommonUtils.isNullOrEmpty(customerCancellation)) {
            predicates.add(builder.equal(root.get("customerDeletionEnabled"), Integer.valueOf(customerCancellation)));
        }
        query.where(predicates.toArray(Predicate[]::new));
        return getEntityManager().createQuery(query).getResultList();
    }
    
    public String deleteUser(String id) {
        UserBe user = getByKey(Integer.valueOf(id));
        delete(user);
        return "Utente eliminato";
    }
    
}

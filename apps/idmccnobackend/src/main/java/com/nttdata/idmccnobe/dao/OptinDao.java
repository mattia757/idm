package com.nttdata.idmccnobe.dao;

import com.nttdata.idmccnobe.model.PrivacyOptin;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

/**
 *
 * @author FacchettiMa
 */
@Repository("OptinDao")
public class OptinDao extends AbstractDao<Integer, PrivacyOptin>{
    
    public List<PrivacyOptin> list(String coopId, String privacyId, String optinId) {
        List<PrivacyOptin> result = new ArrayList<>();
        if (optinId!=null) {
            result.add(getByKey(Integer.parseInt(optinId)));
        } else {
            CriteriaBuilder builder = criteriaBuilder();
            CriteriaQuery<PrivacyOptin> query = builder.createQuery(PrivacyOptin.class);
            Root<PrivacyOptin> root = query.from(PrivacyOptin.class);
            Join<Object, Object> privacy = root.join("privacy");
            Join<Object, Object> cooperativa = privacy.join("cooperativa");
            List<Predicate> predicates = new ArrayList<>();
            if (coopId!=null) {
                predicates.add(builder.equal(cooperativa.get("id"), Integer.valueOf(coopId)));
            }
            if (privacyId!=null) {
                predicates.add(builder.equal(privacy.get("id"), Integer.valueOf(privacyId)));
            }
            query.where(predicates.toArray(Predicate[]::new));
            query.orderBy(builder.asc(cooperativa.get("id")), builder.asc(root.get("version")), builder.asc(root.get("ordine")));
            result = getEntityManager().createQuery(query).getResultList();
        }
        return result;
        
    }
    
    public PrivacyOptin getOptinById(Integer optinId){
        return getByKey(optinId);
    }
}

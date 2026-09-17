package com.nttdata.idmccnobe.dao;

import com.nttdata.idmccnobe.model.CcnoTipologieEventiInteresse;
import java.util.List;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

@Repository("TipologieEventiInteresseDao")
public class TipologieEventiInteresseDao extends AbstractDao<String, CcnoTipologieEventiInteresse> {

    public List<CcnoTipologieEventiInteresse> findAllOrdered() {
        CriteriaBuilder builder = criteriaBuilder();
        CriteriaQuery<CcnoTipologieEventiInteresse> query = builder.createQuery(CcnoTipologieEventiInteresse.class);
        Root<CcnoTipologieEventiInteresse> root = query.from(CcnoTipologieEventiInteresse.class);
        query.orderBy(builder.asc(root.get("idInteresse")));
        return getEntityManager().createQuery(query).getResultList();
    }
}

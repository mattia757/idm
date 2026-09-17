package com.nttdata.idmccnobe.dao;

import com.nttdata.idmccnobe.dto.PartecipazioneInteresseSearchRequest;
import com.nttdata.idmccnobe.model.CcnoPartecipazioneInteresse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository("PartecipazioneInteresseDao")
public class PartecipazioneInteresseDao extends AbstractDao<Long, CcnoPartecipazioneInteresse> {

    public List<CcnoPartecipazioneInteresse> searchPartecipazioni(PartecipazioneInteresseSearchRequest request) {
        CriteriaBuilder builder = criteriaBuilder();
        CriteriaQuery<CcnoPartecipazioneInteresse> query = builder.createQuery(CcnoPartecipazioneInteresse.class);
        Root<CcnoPartecipazioneInteresse> root = query.from(CcnoPartecipazioneInteresse.class);
        List<Predicate> predicates = new ArrayList<>();

        if (request != null) {
            if (StringUtils.hasText(request.getNome())) {
                predicates.add(builder.like(builder.lower(root.get("nome")), "%" + request.getNome().trim().toLowerCase() + "%"));
            }
            if (StringUtils.hasText(request.getCognome())) {
                predicates.add(builder.like(builder.lower(root.get("cognome")), "%" + request.getCognome().trim().toLowerCase() + "%"));
            }
            if (StringUtils.hasText(request.getEmail())) {
                predicates.add(builder.like(builder.lower(root.get("email")), "%" + request.getEmail().trim().toLowerCase() + "%"));
            }
            if (StringUtils.hasText(request.getCoopId())) {
                predicates.add(builder.equal(root.get("coopId"), request.getCoopId().trim()));
            }
            if (StringUtils.hasText(request.getTipologiaEvento())) {
                predicates.add(builder.equal(root.get("tipologiaEvento"), request.getTipologiaEvento().trim()));
            }

            Date dataEventoFrom = parseDate(request.getDataEventoFrom());
            if (dataEventoFrom != null) {
                predicates.add(builder.greaterThanOrEqualTo(root.get("dataEvento"), dataEventoFrom));
            }

            Date dataEventoTo = parseDate(request.getDataEventoTo());
            if (dataEventoTo != null) {
                predicates.add(builder.lessThanOrEqualTo(root.get("dataEvento"), dataEventoTo));
            }
        }

        query.where(predicates.toArray(Predicate[]::new));
        query.orderBy(builder.desc(root.get("dataEvento")), builder.asc(root.get("cognome")), builder.asc(root.get("nome")));
        return getEntityManager().createQuery(query).getResultList();
    }

    public List<CcnoPartecipazioneInteresse> searchAllPartecipazioni() {
        CriteriaBuilder builder = criteriaBuilder();
        CriteriaQuery<CcnoPartecipazioneInteresse> query = builder.createQuery(CcnoPartecipazioneInteresse.class);
        Root<CcnoPartecipazioneInteresse> root = query.from(CcnoPartecipazioneInteresse.class);
        query.orderBy(builder.desc(root.get("dataEvento")), builder.asc(root.get("cognome")), builder.asc(root.get("nome")));
        return getEntityManager().createQuery(query).getResultList();
    }

    public void deleteById(Long id) {
        if (id == null) {
            return;
        }
        CcnoPartecipazioneInteresse entity = getByKey(id);
        if (entity != null) {
            delete(entity);
        }
    }

    public int deleteByUserId(String userId) {
        if (!StringUtils.hasText(userId)) {
            return 0;
        }
        CriteriaBuilder builder = criteriaBuilder();
        CriteriaQuery<CcnoPartecipazioneInteresse> query = builder.createQuery(CcnoPartecipazioneInteresse.class);
        Root<CcnoPartecipazioneInteresse> root = query.from(CcnoPartecipazioneInteresse.class);
        query.where(builder.equal(root.get("userId"), userId.trim()));
        List<CcnoPartecipazioneInteresse> entities = getEntityManager().createQuery(query).getResultList();
        int size = entities.size();
        delete(entities);
        return size;
    }

    private Date parseDate(String date) {
        if (!StringUtils.hasText(date)) {
            return null;
        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);
            return sdf.parse(date.trim());
        } catch (ParseException ex) {
            throw new IllegalArgumentException("Formato data non valido. Atteso: dd/MM/yyyy", ex);
        }
    }
}

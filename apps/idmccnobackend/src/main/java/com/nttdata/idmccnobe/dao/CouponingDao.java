/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.dao;

import com.nttdata.idmccnobe.dto.CouponingSearchRequestDto;
import com.nttdata.idmccnobe.model.CouponingVoucher;
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

/**
 *
 * @author EFERRASXG
 */
@Repository("CouponingDao")
public class CouponingDao extends AbstractDao<String,CouponingVoucher> {
    
    public List<CouponingVoucher> findAllOrderedById(){
        CriteriaBuilder builder = criteriaBuilder();
        CriteriaQuery<CouponingVoucher> query = builder.createQuery(CouponingVoucher.class);
        Root<CouponingVoucher> root = query.from(CouponingVoucher.class);
        query.orderBy(builder.asc(root.get("idVoucher")));
        return getEntityManager().createQuery(query).getResultList();
    }
    
    public List<CouponingVoucher> searchVoucher(CouponingSearchRequestDto request) {
        CriteriaBuilder builder = criteriaBuilder();
        CriteriaQuery<CouponingVoucher> query = builder.createQuery(CouponingVoucher.class);
        Root<CouponingVoucher> root = query.from(CouponingVoucher.class);
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(request.getVoucherId())) {
            predicates.add(builder.equal(root.get("idVoucher"), request.getVoucherId().trim()));
        }
        if (StringUtils.hasText(request.getBarcode())) {
            predicates.add(builder.equal(root.get("bcdEanDiscCoup8"), request.getBarcode().trim()));
        }
        if (StringUtils.hasText(request.getVoucherDate())) {
            try {
                String rawDate = request.getVoucherDate().trim().replace("/", "-");
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                sdf.setLenient(false);
                Date parsedDate = sdf.parse(rawDate);
                predicates.add(builder.greaterThanOrEqualTo(root.get("redStartDate"), parsedDate));

            } catch (ParseException e) {
                // loggalo se serve o rilancia eccezione custom
                throw new IllegalArgumentException("Formato data non valido. Atteso: dd/MM/yyyy o dd-MM-yyyy", e);
            }
        }

        query.where(predicates.toArray(Predicate[]::new));
        query.orderBy(builder.asc(root.get("idVoucher")));
        return getEntityManager().createQuery(query).getResultList();
    }
    
    public CouponingVoucher findById(String voucherId){
        return voucherId == null ? null : getByKey(voucherId.trim());
    }
}

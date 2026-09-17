/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.dao;

import com.nttdata.idmccnobe.model.Uri;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

/**
 *
 * @author BernardisMa
 */
@Repository("UriDao")
public class UriDao extends AbstractDao<Integer, Uri>{
    
    public Uri getUriByName(String value) {
        CriteriaBuilder builder = criteriaBuilder();
        CriteriaQuery<Uri> query = builder.createQuery(Uri.class);
        Root<Uri> root = query.from(Uri.class);
        query.where(builder.equal(root.get("nome"), value));
        return getEntityManager().createQuery(query).getSingleResultOrNull();
    }
    
}

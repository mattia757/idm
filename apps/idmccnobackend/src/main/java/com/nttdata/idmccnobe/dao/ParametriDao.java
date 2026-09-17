/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.dao;

import com.nttdata.idmccnobe.model.Parametri;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

/**
 *
 * @author BernardisMa
 */
@Repository("ParametriDao")
public class ParametriDao extends AbstractDao<Integer, Parametri>{
    
     public Parametri getParametroByName(String nome) {
        CriteriaBuilder builder = criteriaBuilder();
        CriteriaQuery<Parametri> query = builder.createQuery(Parametri.class);
        Root<Parametri> root = query.from(Parametri.class);
        query.where(builder.equal(root.get("nome"), nome));
        return getEntityManager().createQuery(query).getSingleResultOrNull();
    }
}

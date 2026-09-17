/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.service;

import com.nttdata.idmccnobe.dao.ParametriDao;
import com.nttdata.idmccnobe.model.Parametri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author BernardisMa
 */
@Service("ParametriService")
@Transactional
public class ParametriService extends AbstractService {

    @Autowired
    ParametriDao parametriDao;

    @Cacheable(cacheNames = "parametri", cacheManager = "springCM")
    public Parametri getParametroByName(String name) {
        Parametri parametro = parametriDao.getParametroByName(name);
        return parametro;
    }

}

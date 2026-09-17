/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.service;

import com.nttdata.idmccnobe.dao.UriDao;
import com.nttdata.idmccnobe.model.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("UriService")
@Transactional
public class UriService extends AbstractService {

    @Autowired
    UriDao uriDao;

    @Cacheable(cacheNames = "uri", cacheManager = "springCM")
    public Uri getUriByName(String name) {
        Uri uri = uriDao.getUriByName(name);
        return uri;
    }

}

package com.nttdata.idmccnobe.service;

import com.nttdata.idmccnobe.dao.ParameterBeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;

@Service("parameterBeService")
@Transactional
public class ParameterBeService extends AbstractService{    
      
    @Autowired
    private ParameterBeDao parameterBeDao;
    
    @Cacheable(cacheNames="parameterBe", cacheManager = "springCM")
    public String getParameterValueByName(String name) {
        logger.debug("--------------------Reading DB...");
        return parameterBeDao.getParameterValueByName(name);
    }
    
}

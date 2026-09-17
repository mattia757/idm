/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.idmccnobe.configuration;


import com.nttdata.idmccnobe.dao.ErrorCodeDao;
import java.util.Calendar;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;


@Configuration
@ConditionalOnProperty(name = "app.scheduling.enabled", havingValue = "true", matchIfMissing = true)
@Transactional
@EnableScheduling
public class StartupTasks {
    
    private final Logger logger = Logger.getLogger(this.getClass());
    
    private static boolean needToRunStartupMethod = true;
    
    @Autowired
    private ErrorCodeDao errorCodeDao;
    
    @Scheduled(fixedRate = 3600000)
    public void getAllErrorCodes() {
        logger.info("Running getAllErrorCodes task ..." + Calendar.getInstance().getTime());
        if (needToRunStartupMethod) {
            errorCodeDao.getAllErrorCodes();
            needToRunStartupMethod = false;
        }
    }
    
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.idmccnobe.dao;


import com.nttdata.idmccnobe.model.ErrorCode;
import com.nttdata.idmccnobe.model.ErrorCodePK;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

/**
 *
 * @author FacchettiM
 */
@Repository("ErrorCodeDao")
public class ErrorCodeDao extends AbstractDao<ErrorCodePK, ErrorCode>{
    
    private static final transient Logger logger = Logger.getLogger(ErrorCodeDao.class);
    
    private static Map<String,ErrorCode> errorCodeMap = new HashMap<String,ErrorCode>();
    
    public void getAllErrorCodes() {
        try {
            List<ErrorCode> errorCodes = findAll();
            //Configuration config = new Configuration();
            //sessionFactory = config.buildSessionFactory();
            //Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ErrorCodeDao.class);
            //List<ErrorCode> errorCodes = criteria.list();
            logger.info("Query executed: records = "+(errorCodes!=null ? errorCodes.size() : "empty"));
            for (ErrorCode error : errorCodes) {
                errorCodeMap.put(error.getErrorCodePK().getApplication()+"-"+error.getErrorCodePK().getCode(), error);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
    
    public ErrorCode getError(String application, Integer code) {
        return getByKey(new ErrorCodePK(application, code));
    }
	
    public static String getErrorMessage(String application, Integer code) {
        String message;
        logger.info("getErrorMessage: application="+application+", code="+code);
        ErrorCode error = errorCodeMap.get(application+"-"+code);
        message = error.getMessage();
        logger.info("getErrorMessage message="+message);
        return message;
    }
    
    public static String getErrorMessage(String application, Integer code, Map<String,String> placeholders) {
        String message;
        logger.info("getErrorMessage: application="+application+", code="+code);
        ErrorCode error = errorCodeMap.get(application+"-"+code);
        message = error.getMessage();
        if (message!=null && placeholders!=null && !placeholders.isEmpty()) {
            for (Map.Entry<String, String> entry : placeholders.entrySet()) {
                logger.info("getErrorMessage - placeholder: name = " + entry.getKey() + ", value = " + entry.getValue());
                error.getMessage().replaceAll(entry.getKey(), entry.getValue());
            }
        }
        logger.info("getErrorMessage message="+message);
        return message;
    }
    
}

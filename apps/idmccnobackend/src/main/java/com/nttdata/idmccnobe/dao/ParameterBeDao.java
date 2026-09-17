package com.nttdata.idmccnobe.dao;

import com.nttdata.idmccnobe.model.Parameter;
import org.springframework.stereotype.Repository;


/**
 *
 * @author Cristian
 */


@Repository("parameterBeDao")
public class ParameterBeDao extends AbstractDao<String, Parameter> {
  
    public String getParameterValueByName(String name) {
        String result = null;
        Parameter tmp = getByKey(name);
        if (tmp != null) {
            result = tmp.getValore();
        }
        return result;
    }
}

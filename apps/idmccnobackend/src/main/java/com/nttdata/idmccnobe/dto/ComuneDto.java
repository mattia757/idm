package com.nttdata.idmccnobe.dto;

import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author DelorenziVa
 */
@Component
@Scope("session")
public class ComuneDto implements Serializable{

    private static final long serialVersionUID = -715629251244678010L;

    private String idCity;
    private String name;
    
    public ComuneDto(){}
    
    public ComuneDto (String idCity, String name){
        this.idCity = idCity;
        this.name = name;
    }

    public String getIdCity() {
        return idCity;
    }

    public void setIdCity(String idCity) {
        this.idCity = idCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}

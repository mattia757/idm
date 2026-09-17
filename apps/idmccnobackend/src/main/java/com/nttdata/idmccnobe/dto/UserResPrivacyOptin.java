package com.nttdata.idmccnobe.dto;

import java.io.Serializable;

/**
 *
 * @author DelorenziVa
 */
public class UserResPrivacyOptin implements Serializable{

    private static final long serialVersionUID = 7251043848207978822L;
    
    private String id;
    private Integer privacyType;
    private Boolean value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPrivacyType() {
        return privacyType;
    }

    public void setPrivacyType(Integer privacyType) {
        this.privacyType = privacyType;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }
    
    
}

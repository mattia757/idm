package com.nttdata.idmccnobe.dto;

import java.io.Serializable;

/**
 *
 * @author DelorenziVa
 */
public class UserPrivacyDto implements Serializable{

    private static final long serialVersionUID = 4628610301917057657L;
    
    private String id;
    private String coopId;
    private String userType;
    private String version;
    private Boolean value;
    private Integer ordine;
    private Integer privacyType;

    public UserPrivacyDto() {
    }

    /*
    public UserPrivacyDto(String id, String coopId, String userType, String version, Boolean value, Integer ordine) {
        this.id = id;
        this.coopId = coopId;
        this.userType = userType;
        this.version = version;
        this.value = value;
        this.ordine = ordine;
    }

    public UserPrivacyDto(String id, String coopId, String version, Boolean value) {
        this.id = id;
        this.coopId = coopId;
        this.version = version;
        this.value = value;
    }*/

    public UserPrivacyDto(String id, String coopId, String userType, String version, Boolean value, Integer ordine, Integer privacyType) {
        this.id = id;
        this.coopId = coopId;
        this.userType = userType;
        this.version = version;
        this.value = value;
        this.ordine = ordine;
        this.privacyType = privacyType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoopId() {
        return coopId;
    }

    public void setCoopId(String coopId) {
        this.coopId = coopId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public Integer getOrdine() {
        return ordine;
    }

    public void setOrdine(Integer ordine) {
        this.ordine = ordine;
    }

    public Integer getPrivacyType() {
        return privacyType;
    }

    public void setPrivacyType(Integer privacyType) {
        this.privacyType = privacyType;
    }

    @Override
    public String toString() {
        return "id=" + id + ", coopId=" + coopId + ", userType = " +userType + ", version = " + version + ", value = " + value + ", ordine = " + ordine + ", privacyType = " + privacyType;
    }
    
    
}

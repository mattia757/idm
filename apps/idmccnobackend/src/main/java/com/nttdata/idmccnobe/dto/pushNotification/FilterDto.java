/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.dto.pushNotification;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author BernardisMa
 */
public class FilterDto implements Serializable {

    private static final long serialVersionUID = 7570081134162246533L;
    
    private String instance;
    private List<String> usernames;
    private List<String> dmIds;
    private List<String> technologyClasses;
    private String appVersionLike;

    public FilterDto() {
    }

    public FilterDto(String instance, List<String> usernames, List<String> dmIds, List<String> technologyClasses, String appVersionLike) {
        this.instance = instance;
        this.usernames = usernames;
        this.dmIds = dmIds;
        this.technologyClasses = technologyClasses;
        this.appVersionLike = appVersionLike;
    }

    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    public List<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }

    public List<String> getDmIds() {
        return dmIds;
    }

    public void setDmIds(List<String> dmIds) {
        this.dmIds = dmIds;
    }

    public List<String> getTechnologyClasses() {
        return technologyClasses;
    }

    public void setTechnologyClasses(List<String> technologyClasses) {
        this.technologyClasses = technologyClasses;
    }

    public String getAppVersionLike() {
        return appVersionLike;
    }

    public void setAppVersionLike(String appVersionLike) {
        this.appVersionLike = appVersionLike;
    }
    
}

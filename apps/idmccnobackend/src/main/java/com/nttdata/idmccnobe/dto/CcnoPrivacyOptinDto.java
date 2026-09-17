package com.nttdata.idmccnobe.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author DelorenziVa
 */
@Component
@Scope("session")
public class CcnoPrivacyOptinDto implements Serializable{

    private static final long serialVersionUID = 5038082353350150646L;
    
    private String id;
    private String version;
    private String privacyType;
    private boolean mandatory;
    private String order;
    private String title;
    private String text;
    private String image; 
    private List<CcnoPrivacyOptinBenefitDto> benefits = new ArrayList<>();
    

    public CcnoPrivacyOptinDto() {
    }

    /*public CcnoPrivacyOptinDto(String id, String version, boolean mandatory, String order, String title, String text, String privacyType) {
        this.id = id;
        this.version = version;
        this.mandatory = mandatory;
        this.order = order;
        this.title = title;
        this.text = text;
        this.privacyType = privacyType;
    }*/

    public CcnoPrivacyOptinDto(String id, String version, String privacyType, boolean mandatory, String order, String title, String text, String image) {
        this.id = id;
        this.version = version;
        this.privacyType = privacyType;
        this.mandatory = mandatory;
        this.order = order;
        this.title = title;
        this.text = text;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<CcnoPrivacyOptinBenefitDto> getBenefits() {
        return benefits;
    }

    public void setBenefits(List<CcnoPrivacyOptinBenefitDto> benefits) {
        this.benefits = benefits;
    }

    public String getPrivacyType() {
        return privacyType;
    }

    public void setPrivacyType(String privacyType) {
        this.privacyType = privacyType;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "id=" + id + ", version=" + version + ", mandatory = " +mandatory + ", order = " + order + ", title = " + title + ", text = " + text + ", image = " + image;
    }
    
}

package com.nttdata.idmccnobe.dto;


import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author DelorenziVa
 */
@Component
@Scope("session")
public class PrivacyPayload extends CommonPayload implements Serializable{

    private static final long serialVersionUID = -8406778890937857825L;
//    private String id;
    private String privacyLabel;
    private String privacyLink;
    private String privacyLinkType;
//    private String description;
//    private String application;
    private List <CcnoPrivacyOptinDto> optinPrivacy; 
    
    public PrivacyPayload (){}

    public String getPrivacyLabel() {
        return privacyLabel;
    }

    public void setPrivacyLabel(String privacyLabel) {
        this.privacyLabel = privacyLabel;
    }

    public String getPrivacyLink() {
        return privacyLink;
    }

    public void setPrivacyLink(String privacyLink) {
        this.privacyLink = privacyLink;
    }

    public String getPrivacyLinkType() {
        return privacyLinkType;
    }

    public void setPrivacyLinkType(String privacyLinkType) {
        this.privacyLinkType = privacyLinkType;
    }

    public List<CcnoPrivacyOptinDto> getOptinPrivacy() {
        return optinPrivacy;
    }

    public void setOptinPrivacy(List<CcnoPrivacyOptinDto> optinPrivacy) {
        this.optinPrivacy = optinPrivacy;
    }
    
}

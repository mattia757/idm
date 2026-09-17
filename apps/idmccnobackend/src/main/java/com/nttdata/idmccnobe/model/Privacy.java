package com.nttdata.idmccnobe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.nttdata.ccno.dto.CcnoPrivacyOptinDto;
//import com.nttdata.ccno.dto.PrivacyPayload;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

/**
 *
 * @author MartinenghiSt
 */
@Entity
@Table(name = "CCNO_PRIVACY")
public class Privacy implements Serializable {

    private static final long serialVersionUID = -6293377146668819505L;

    @Id
    @Column(name = "ID")
    private Integer id;
    @Column(name = "PRIVACY_LABEL")
    private String privacyLabel;
    @Column(name = "PRIVACY_LINK")
    private String privacyLink;
    @Column(name = "PRIVACY_LINK_TYPE")
    private String privacyLinkType;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "APPLICATION")
    private String application;
    @Column(name = "ID_PRIVACY")
    private Integer idPrivacy;
    @Column(name = "MANDATORY")
    private Integer mandatory;
    @Column(name = "ORDINE")
    private Integer ordine;
    @Lob
    @Size(max = 16777215)
    @Column(name = "TEXT")
    private String text;
    @Size(max = 255)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 255)
    @Column(name = "VERSION")
    private String version;
    @Column(name = "USER_TYPE")
    private Integer userType;
    @JsonIgnore
    @OneToMany(mappedBy = "privacy", fetch = FetchType.LAZY)
    private List<PrivacyOptin> privacyOptins;
    
    @JoinColumn(name = "CCNO_COOPERATIVA", referencedColumnName = "ID")
    @ManyToOne
    private Cooperativa cooperativa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public Cooperativa getCooperativa() {
        return cooperativa;
    }

    public void setCooperativa(Cooperativa cooperativa) {
        this.cooperativa = cooperativa;
    }

    public List<PrivacyOptin> getPrivacyOptins() {
        return privacyOptins;
    }

    public void setPrivacyOptins(List<PrivacyOptin> privacyOptins) {
        this.privacyOptins = privacyOptins;
    }
    
    /*
    public PrivacyPayload toDto(List<CcnoPrivacyOptin> customOptinList){
        PrivacyPayload obj = new PrivacyPayload();
        obj.setPrivacyLabel(privacyLabel);
        obj.setPrivacyLink(privacyLink);
        obj.setPrivacyLinkType(privacyLinkType);
        List<CcnoPrivacyOptin> privacyOptins = (customOptinList!=null ? customOptinList : getPrivacyOptins());
        List<CcnoPrivacyOptinDto> privacyOptinsDto = new ArrayList<CcnoPrivacyOptinDto>();
        if (!privacyOptins.isEmpty()) {
            for (CcnoPrivacyOptin optin : privacyOptins) {
                privacyOptinsDto.add(optin.toDto());
            }
        }
        obj.setOptinPrivacy(privacyOptinsDto);
        return obj;
    }
    */

    public Privacy() {
    }

    public Integer getIdPrivacy() {
        return idPrivacy;
    }

    public void setIdPrivacy(Integer idPrivacy) {
        this.idPrivacy = idPrivacy;
    }

    public Integer getMandatory() {
        return mandatory;
    }

    public void setMandatory(Integer mandatory) {
        this.mandatory = mandatory;
    }

    public Integer getOrdine() {
        return ordine;
    }

    public void setOrdine(Integer ordine) {
        this.ordine = ordine;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}

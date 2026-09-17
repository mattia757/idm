package com.nttdata.idmccnobe.model;

//import com.nttdata.ccno.dto.CcnoPrivacyOptinDto;
//import com.nttdata.ccno.dto.CcnoPrivacyOptinBenefitDto;
import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author DelorenziVa
 */
@Entity
@Table(name = "CCNO_PRIVACY_OPTIN")
public class PrivacyOptin implements Serializable{

    private static final long serialVersionUID = 552873788057458616L;
    
    @Id
    @Column(name = "ID")
    private Integer id;
    @Column(name = "MANDATORY")
    private Integer mandatory;
    @Column(name = "ORDINE")
    private Integer ordine;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "TEXT", columnDefinition = "mediumtext")
    private String text;
    @Column(name = "VERSION")
    private Integer version;
    @Column(name = "TYPE")
    private Integer type;
    
    @JoinColumn(name = "CCNO_PRIVACY", referencedColumnName = "ID")
    @ManyToOne
    private Privacy privacy;
    
    public PrivacyOptin() {
    }

    public PrivacyOptin(Integer id, Integer mandatory, Integer ordine, String title, String text, Integer version, Integer type, Privacy privacy) {
        this.id = id;
        this.mandatory = mandatory;
        this.ordine = ordine;
        this.title = title;
        this.text = text;
        this.version = version;
        this.type = type;
        this.privacy = privacy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Privacy getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Privacy privacy) {
        this.privacy = privacy;
    }
    
    /*
    public CcnoPrivacyOptinDto toDto(){
        CcnoPrivacyOptinDto obj = new CcnoPrivacyOptinDto();
        obj.setId(id.toString());
        obj.setVersion(version.toString());
        if (mandatory != null && mandatory != 0){
            obj.setMandatory(true);
        } else {
           obj.setMandatory(false);
        }
        obj.setTitle(title);
        obj.setText(text);
        obj.setOrder(ordine.toString());
        List<CcnoPrivacyOptinBenefitDto> privacyOptinBenefitDto = new ArrayList<CcnoPrivacyOptinBenefitDto>();
        obj.setBenefits(privacyOptinBenefitDto);
        return obj;
    }
    */
    
}

package com.nttdata.idmccnobe.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nttdata.idmccnobe.util.DateJsonDeserializer;
import com.nttdata.idmccnobe.util.DateJsonSerializer;
import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author DelorenziVa
 */
public class ExportUserExcelRequest implements Serializable {

    private static long serialVersionUID = -2073995086059727349L;
    
    private String coopId;
    private String emailVerified;
    private String socio;
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date registrationDateFrom;
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date registrationDateTo;
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date lastAppAccessDateFrom;
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date lastAppAccessDateTo;
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date lastEcommerceAccessDateFrom;
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date lastEcommerceAccessDateTo;
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date lastCommunityAccessDateFrom;
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date lastCommunityAccessDateTo;
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date lastPortalAccessDateFrom;
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date lastPortalAccessDateTo;
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date lastModifyDateFrom;
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date lastModifyDateTo;
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date lastPortalNovaAccessDateFrom;
    @JsonSerialize(using = DateJsonSerializer.class)
    @JsonDeserialize(using = DateJsonDeserializer.class)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date lastPortalNovaAccessDateTo;
    private String pdvId;
    private boolean includeRemovedUsers;
    private String codicePdv;

    public ExportUserExcelRequest() {
    }

    public ExportUserExcelRequest(String coopId, String emailVerified, String socio) {
        this.coopId = coopId;
        this.emailVerified = emailVerified;
        this.socio = socio;
    }

    public String getCoopId() {
        return coopId;
    }

    public void setCoopId(String coopId) {
        this.coopId = coopId;
    }

    public String getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(String emailVerified) {
        this.emailVerified = emailVerified;
    }

    public String getSocio() {
        return socio;
    }

    public void setSocio(String socio) {
        this.socio = socio;
    }
    
    public Date getRegistrationDateFrom() {
        return registrationDateFrom;
    }

    public void setRegistrationDateFrom(Date registrationDateFrom) {
        this.registrationDateFrom = registrationDateFrom;
    }

    public Date getRegistrationDateTo() {
        return registrationDateTo;
    }

    public void setRegistrationDateTo(Date registrationDateTo) {
        this.registrationDateTo = registrationDateTo;
    }

    public Date getLastAppAccessDateFrom() {
        return lastAppAccessDateFrom;
    }

    public void setLastAppAccessDateFrom(Date lastAppAccessDateFrom) {
        this.lastAppAccessDateFrom = lastAppAccessDateFrom;
    }

    public Date getLastAppAccessDateTo() {
        return lastAppAccessDateTo;
    }

    public void setLastAppAccessDateTo(Date lastAppAccessDateTo) {
        this.lastAppAccessDateTo = lastAppAccessDateTo;
    }

    public Date getLastEcommerceAccessDateFrom() {
        return lastEcommerceAccessDateFrom;
    }

    public void setLastEcommerceAccessDateFrom(Date lastEcommerceAccessDateFrom) {
        this.lastEcommerceAccessDateFrom = lastEcommerceAccessDateFrom;
    }

    public Date getLastEcommerceAccessDateTo() {
        return lastEcommerceAccessDateTo;
    }

    public void setLastEcommerceAccessDateTo(Date lastEcommerceAccessDateTo) {
        this.lastEcommerceAccessDateTo = lastEcommerceAccessDateTo;
    }

    public Date getLastCommunityAccessDateFrom() {
        return lastCommunityAccessDateFrom;
    }

    public void setLastCommunityAccessDateFrom(Date lastCommunityAccessDateFrom) {
        this.lastCommunityAccessDateFrom = lastCommunityAccessDateFrom;
    }

    public Date getLastCommunityAccessDateTo() {
        return lastCommunityAccessDateTo;
    }

    public void setLastCommunityAccessDateTo(Date lastCommunityAccessDateTo) {
        this.lastCommunityAccessDateTo = lastCommunityAccessDateTo;
    }

    public Date getLastPortalAccessDateFrom() {
        return lastPortalAccessDateFrom;
    }

    public void setLastPortalAccessDateFrom(Date lastPortalAccessDateFrom) {
        this.lastPortalAccessDateFrom = lastPortalAccessDateFrom;
    }

    public Date getLastPortalAccessDateTo() {
        return lastPortalAccessDateTo;
    }

    public void setLastPortalAccessDateTo(Date lastPortalAccessDateTo) {
        this.lastPortalAccessDateTo = lastPortalAccessDateTo;
    }
    
    public Date getLastModifyDateFrom() {
        return lastModifyDateFrom;
    }

    public void setLastModifyDateFrom(Date lastModifyDateFrom) {
        this.lastModifyDateFrom = lastModifyDateFrom;
    }

    public Date getLastModifyDateTo() {
        return lastModifyDateTo;
    }

    public void setLastModifyDateTo(Date lastModifyDateTo) {
        this.lastModifyDateTo = lastModifyDateTo;
    }

    public String getPdvId() {
        return pdvId;
    }

    public void setPdvId(String pdvId) {
        this.pdvId = pdvId;
    }

    public String getCodicePdv() {
        return codicePdv;
    }

    public void setCodicePdv(String codicePdv) {
        this.codicePdv = codicePdv;
    }

    public Date getLastPortalNovaAccessDateFrom() {
        return lastPortalNovaAccessDateFrom;
    }

    public void setLastPortalNovaAccessDateFrom(Date lastPortalNovaAccessDateFrom) {
        this.lastPortalNovaAccessDateFrom = lastPortalNovaAccessDateFrom;
    }

    public Date getLastPortalNovaAccessDateTo() {
        return lastPortalNovaAccessDateTo;
    }

    public void setLastPortalNovaAccessDateTo(Date lastPortalNovaAccessDateTo) {
        this.lastPortalNovaAccessDateTo = lastPortalNovaAccessDateTo;
    }

    public boolean isIncludeRemovedUsers() {
        return includeRemovedUsers;
    }

    public void setIncludeRemovedUsers(boolean includeRemovedUsers) {
        this.includeRemovedUsers = includeRemovedUsers;
    }
    
    @Override
    public String toString() {
        return "coopId="+getCoopId()+",emailVerified="+getEmailVerified()+",socio="+getSocio(); //To change body of generated methods, choose Tools | Templates.
    }
    
}

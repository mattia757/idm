/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.idmccnobe.model;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author FacchettiM
 */
@Entity
@Table(name = "CCNO_ERROR_CODE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ErrorCode.findAll", query = "SELECT c FROM ErrorCode c"),
    @NamedQuery(name = "ErrorCode.findByApplication", query = "SELECT c FROM ErrorCode c WHERE c.errorCodePK.application = :application"),
    @NamedQuery(name = "ErrorCode.findByCode", query = "SELECT c FROM ErrorCode c WHERE c.errorCodePK.code = :code"),
    @NamedQuery(name = "ErrorCode.findByName", query = "SELECT c FROM ErrorCode c WHERE c.name = :name"),
    @NamedQuery(name = "ErrorCode.findByMessage", query = "SELECT c FROM ErrorCode c WHERE c.message = :message"),
    @NamedQuery(name = "ErrorCode.findByResult", query = "SELECT c FROM ErrorCode c WHERE c.result = :result")})
public class ErrorCode implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ErrorCodePK errorCodePK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2000)
    @Column(name = "MESSAGE")
    private String message;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "RESULT")
    private String result;

    public ErrorCode() {
    }

    public ErrorCode(ErrorCodePK errorCodePK) {
        this.errorCodePK = errorCodePK;
    }

    public ErrorCode(ErrorCodePK errorCodePK, String name, String message, String result) {
        this.errorCodePK = errorCodePK;
        this.name = name;
        this.message = message;
        this.result = result;
    }

    public ErrorCode(String application, int code) {
        this.errorCodePK = new ErrorCodePK(application, code);
    }

    public ErrorCodePK getErrorCodePK() {
        return errorCodePK;
    }

    public void setErrorCodePK(ErrorCodePK errorCodePK) {
        this.errorCodePK = errorCodePK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (errorCodePK != null ? errorCodePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ErrorCode)) {
            return false;
        }
        ErrorCode other = (ErrorCode) object;
        if ((this.errorCodePK == null && other.errorCodePK != null) || (this.errorCodePK != null && !this.errorCodePK.equals(other.errorCodePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nttdata.idmccnobe.model.ErrorCode[ errorCodePK=" + errorCodePK + " ]";
    }
    
}

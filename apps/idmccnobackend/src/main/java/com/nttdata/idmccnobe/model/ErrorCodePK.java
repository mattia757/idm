/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.idmccnobe.model;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 *
 * @author FacchettiM
 */
@Embeddable
public class ErrorCodePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "APPLICATION")
    private String application;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODE")
    private int code;

    public ErrorCodePK() {
    }

    public ErrorCodePK(String application, int code) {
        this.application = application;
        this.code = code;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (application != null ? application.hashCode() : 0);
        hash += (int) code;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ErrorCodePK)) {
            return false;
        }
        ErrorCodePK other = (ErrorCodePK) object;
        if ((this.application == null && other.application != null) || (this.application != null && !this.application.equals(other.application))) {
            return false;
        }
        if (this.code != other.code) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nttdata.idmccnobe.model.ErrorCodePK[ application=" + application + ", code=" + code + " ]";
    }
    
}

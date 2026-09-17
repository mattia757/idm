package com.nttdata.idmccnobe.model;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "CCNO_TIPOLOGIE_EVENTI_INTERESSE")
public class CcnoTipologieEventiInteresse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "ID_INTERESSE")
    private String idInteresse;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "DESCRIPTION")
    private String description;

    public CcnoTipologieEventiInteresse() {
    }

    public String getIdInteresse() {
        return idInteresse;
    }

    public void setIdInteresse(String idInteresse) {
        this.idInteresse = idInteresse;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

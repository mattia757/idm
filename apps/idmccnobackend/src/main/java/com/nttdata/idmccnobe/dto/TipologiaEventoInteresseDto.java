package com.nttdata.idmccnobe.dto;

import java.io.Serializable;

public class TipologiaEventoInteresseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String idInteresse;
    private String description;

    public TipologiaEventoInteresseDto() {
    }

    public TipologiaEventoInteresseDto(String idInteresse, String description) {
        this.idInteresse = idInteresse;
        this.description = description;
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

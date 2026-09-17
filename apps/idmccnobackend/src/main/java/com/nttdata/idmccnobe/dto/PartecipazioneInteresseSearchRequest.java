package com.nttdata.idmccnobe.dto;

import java.io.Serializable;

public class PartecipazioneInteresseSearchRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String cognome;
    private String email;
    private String coopId;
    private String dataEventoFrom;
    private String dataEventoTo;
    private String tipologiaEvento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCoopId() {
        return coopId;
    }

    public void setCoopId(String coopId) {
        this.coopId = coopId;
    }

    public String getDataEventoFrom() {
        return dataEventoFrom;
    }

    public void setDataEventoFrom(String dataEventoFrom) {
        this.dataEventoFrom = dataEventoFrom;
    }

    public String getDataEventoTo() {
        return dataEventoTo;
    }

    public void setDataEventoTo(String dataEventoTo) {
        this.dataEventoTo = dataEventoTo;
    }

    public String getTipologiaEvento() {
        return tipologiaEvento;
    }

    public void setTipologiaEvento(String tipologiaEvento) {
        this.tipologiaEvento = tipologiaEvento;
    }
}

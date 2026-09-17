package com.nttdata.idmccnobe.dto;

import java.io.Serializable;

public class PartecipazioneInteresseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String userId;
    private String email;
    private String coopId;
    private String tesseraSocio;
    private String dataEvento;
    private String luogoEvento;
    private String tipologiaEvento;
    private String nome;
    private String cognome;
    private String dataInserimento;

    public PartecipazioneInteresseDto() {
    }

    public PartecipazioneInteresseDto(Long id, String userId, String email, String coopId, String tesseraSocio, String dataEvento, String luogoEvento, String tipologiaEvento, String nome, String cognome, String dataInserimento) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.coopId = coopId;
        this.tesseraSocio = tesseraSocio;
        this.dataEvento = dataEvento;
        this.luogoEvento = luogoEvento;
        this.tipologiaEvento = tipologiaEvento;
        this.nome = nome;
        this.cognome = cognome;
        this.dataInserimento = dataInserimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getTesseraSocio() {
        return tesseraSocio;
    }

    public void setTesseraSocio(String tesseraSocio) {
        this.tesseraSocio = tesseraSocio;
    }

    public String getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(String dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getLuogoEvento() {
        return luogoEvento;
    }

    public void setLuogoEvento(String luogoEvento) {
        this.luogoEvento = luogoEvento;
    }

    public String getTipologiaEvento() {
        return tipologiaEvento;
    }

    public void setTipologiaEvento(String tipologiaEvento) {
        this.tipologiaEvento = tipologiaEvento;
    }

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

    public String getDataInserimento() {
        return dataInserimento;
    }

    public void setDataInserimento(String dataInserimento) {
        this.dataInserimento = dataInserimento;
    }
}

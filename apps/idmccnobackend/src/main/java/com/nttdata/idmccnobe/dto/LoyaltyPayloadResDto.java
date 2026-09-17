package com.nttdata.idmccnobe.dto;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author DelorenziVa
 */
@Component
@Scope("session")
public class LoyaltyPayloadResDto extends LoyaltyCommonPayloadResDto implements Serializable{

    private static final long serialVersionUID = 20592653776757723L;
    
    private String esito;
    private String autorizzazione;
    private String nome;
    private String cognome;
    private String comuneRes;
    private String provRes;
    private String provResIso;
    private String indrEmail;
    private String cartaRelazione;
    private String cartaValida;
    private String statoCarta;
    private String registrato;

    public LoyaltyPayloadResDto() {
    }

    public LoyaltyPayloadResDto(String esito, String autorizzazione, String nome, String cognome, String comuneRes, String provRes, String provResIso, String indrEmail, String cartaRelazione, String cartaValida, String statoCarta, String registrato) {
        this.esito = esito;
        this.autorizzazione = autorizzazione;
        this.nome = nome;
        this.cognome = cognome;
        this.comuneRes = comuneRes;
        this.provRes = provRes;
        this.provResIso = provResIso;
        this.indrEmail = indrEmail;
        this.cartaRelazione = cartaRelazione;
        this.cartaValida = cartaValida;
        this.statoCarta = statoCarta;
        this.registrato = registrato;
    }
    
    public String getEsito() {
        return esito;
    }

    public void setEsito(String esito) {
        this.esito = esito;
    }
    
    public String getAutorizzazione() {
        return autorizzazione;
    }

    public void setAutorizzazione(String autorizzazione) {
        this.autorizzazione = autorizzazione;
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

    public String getComuneRes() {
        return comuneRes;
    }

    public void setComuneRes(String comuneRes) {
        this.comuneRes = comuneRes;
    }

    public String getProvRes() {
        return provRes;
    }

    public void setProvRes(String provRes) {
        this.provRes = provRes;
    }

    public String getProvResIso() {
        return provResIso;
    }

    public void setProvResIso(String provResIso) {
        this.provResIso = provResIso;
    }

    public String getIndrEmail() {
        return indrEmail;
    }

    public void setIndrEmail(String indrEmail) {
        this.indrEmail = indrEmail;
    }

    public String getCartaRelazione() {
        return cartaRelazione;
    }

    public void setCartaRelazione(String cartaRelazione) {
        this.cartaRelazione = cartaRelazione;
    }

    public String getCartaValida() {
        return cartaValida;
    }

    public void setCartaValida(String cartaValida) {
        this.cartaValida = cartaValida;
    }

    public String getStatoCarta() {
        return statoCarta;
    }

    public void setStatoCarta(String statoCarta) {
        this.statoCarta = statoCarta;
    }

    public String getRegistrato() {
        return registrato;
    }

    public void setRegistrato(String registrato) {
        this.registrato = registrato;
    }
 
}

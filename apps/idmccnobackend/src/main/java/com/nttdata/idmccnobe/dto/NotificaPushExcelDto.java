/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.dto;

import java.io.Serializable;

/**
 *
 * @author FerrandicoSi
 */
public class NotificaPushExcelDto implements Serializable {
    
    private static final long serialVersionUID = 5793058595778436610L;
    
    private String coopId;
    private String email;
    private String testo;
    private String messaggio;
    private String image;
    private String link;
    private String deepLink;
    private String profilazione;
    private String marketing;

    public NotificaPushExcelDto() {
    }

    public NotificaPushExcelDto(String coopId, String email, String testo, String messaggio, String image, String link, String deepLink, String profilazione, String marketing) {
        this.coopId = coopId;
        this.email = email;
        this.testo = testo;
        this.messaggio = messaggio;
        this.image = image;
        this.link = link;
        this.deepLink = deepLink;
        this.profilazione = profilazione;
        this.marketing = marketing;
    }

    public String getCoopId() {
        return coopId;
    }

    public void setCoopId(String coopId) {
        this.coopId = coopId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(String messaggio) {
        this.messaggio = messaggio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDeepLink() {
        return deepLink;
    }

    public void setDeepLink(String deepLink) {
        this.deepLink = deepLink;
    }

    public String getProfilazione() {
        return profilazione;
    }

    public void setProfilazione(String profilazione) {
        this.profilazione = profilazione;
    }

    public String getMarketing() {
        return marketing;
    }

    public void setMarketing(String marketing) {
        this.marketing = marketing;
    }
    
    
    
}

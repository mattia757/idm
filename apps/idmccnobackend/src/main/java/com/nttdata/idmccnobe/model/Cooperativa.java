package com.nttdata.idmccnobe.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 *
 * @author MartinenghiSt
 */
@Entity
@Table(name = "CCNO_COOPERATIVA")
public class Cooperativa implements Serializable {

    @Id
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NUMERO_COOPERATIVA")
    private Integer numeroCooperativa;
    @Column(name = "RAGIONE_SOCIALE")
    private String ragioneSociale;
    @Column(name = "CODICE_GLN")
    private String codiceGln;
    @Column(name = "INDIRIZZO")
    private String indirizzo;
    @Column(name = "P_IVA")
    private String partitaIva;
    @Column(name = "CITTA")
    private String citta;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "CAP")
    private Integer cap;
    @Column(name = "TELEFONO")
    private Integer telefono;
    
    @Transient
    public static final Integer ID_COOP_NAZIONALE = 10;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumeroCooperativa() {
        return numeroCooperativa;
    }

    public void setNumeroCooperativa(Integer numeroCooperativa) {
        this.numeroCooperativa = numeroCooperativa;
    }

    public String getRagioneSociale() {
        return ragioneSociale;
    }

    public void setRagioneSociale(String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }

    public boolean isNazionale() {
        return id != null && id.equals(ID_COOP_NAZIONALE);
    }

    public String getCodiceGln() {
        return codiceGln;
    }

    public void setCodiceGln(String codiceGln) {
        this.codiceGln = codiceGln;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getPartitaIva() {
        return partitaIva;
    }

    public void setPartitaIva(String partitaIva) {
        this.partitaIva = partitaIva;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCap() {
        return cap;
    }

    public void setCap(Integer cap) {
        this.cap = cap;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    }

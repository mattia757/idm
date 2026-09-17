package com.nttdata.idmccnobe.model;

import com.nttdata.idmccnobe.dto.PartecipazioneInteresseDto;
import com.nttdata.idmccnobe.util.Constants;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "CCNO_PARTECIPAZIONE_INTERESSE")
public class CcnoPartecipazioneInteresse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "USER_ID")
    private String userId;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "EMAIL")
    private String email;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "COOP_ID")
    private String coopId;

    @Size(max = 255)
    @Column(name = "TESSERA_SOCIO")
    private String tesseraSocio;

    @Basic(optional = false)
    @NotNull
    @Column(name = "DATA_EVENTO")
    @Temporal(TemporalType.DATE)
    private Date dataEvento;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "LUOGO_EVENTO")
    private String luogoEvento;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "TIPOLOGIA_EVENTO")
    private String tipologiaEvento;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NOME")
    private String nome;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "COGNOME")
    private String cognome;

    @Basic(optional = false)
    @NotNull
    @Column(name = "DATA_INSERIMENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInserimento;

    public CcnoPartecipazioneInteresse() {
    }

    public PartecipazioneInteresseDto toDto(String tipologiaEventoDescription) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return new PartecipazioneInteresseDto(
                id,
                userId,
                email,
                toCoopDescription(coopId),
                tesseraSocio,
                dataEvento != null ? dateFormat.format(dataEvento) : "",
                luogoEvento,
                tipologiaEventoDescription,
                nome,
                cognome,
                dataInserimento != null ? dateTimeFormat.format(dataInserimento) : "");
    }

    private String toCoopDescription(String coopIdValue) {
        if ("1".equals(coopIdValue)) {
            return Constants.COOP_NOVACOOP;
        }
        if ("2".equals(coopIdValue)) {
            return "Liguria";
        }
        if ("3".equals(coopIdValue)) {
            return "Lombardia";
        }
        return coopIdValue;
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

    public Date getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(Date dataEvento) {
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

    public Date getDataInserimento() {
        return dataInserimento;
    }

    public void setDataInserimento(Date dataInserimento) {
        this.dataInserimento = dataInserimento;
    }
}

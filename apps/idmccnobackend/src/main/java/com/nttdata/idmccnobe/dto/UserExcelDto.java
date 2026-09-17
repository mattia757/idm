package com.nttdata.idmccnobe.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DelorenziVa
 */
public class UserExcelDto implements Serializable {

    private static final long serialVersionUID = -8698069638614647927L;

    private String userId;
    private String nome;
    private String cognome;
    private String email;
    private String emailVerificata;
    private String tessereSocio;
    private String cooperativa;
    private String applicazioneDiRegistrazione; // servizio utilizzato in registrazione
    private String dataDiRegistrazione; // data di registrazione
    private String primoAccessoDaAppCoop;
    private String ultimoAccessoDaAppCoop;
    private String primoAccessoDaEcommerce;
    private String ultimoAccessoDaEcommerce;
    private String primoAccessoDaCommunity;
    private String ultimoAccessoDaCommunity;
    private String primoAccessoDaPortale;
    private String ultimoAccessoDaPortale;
    private String primoAccessoDaPortaleNova;
    private String ultimoAccessoDaPortaleNova;
    private String dataUltimaModifica;
    private String stato; // stato utente
    private String dataDiNascita;
    private String sesso;
    private String tipologiaPersona; // -	Type (“F” persona fisica o “G” giuridica)
    private String provinciaResidenza; // provincia di residenza
    private String cittaResidenza; // città di residenza
    private String pdvId; // id StoreLocator
    private String codicePdv; //codice di negozio della cooperativa
    private String consensoProfilazioneNovacoop;
    private String consensoMarketingNovacoop;
    private String consensoProfilazioneNovacoopSoci;
    private String consensoMarketingNovacoopSoci;
    private String consensoProfilazioneNovacoopNonSoci;
    private String consensoMarketingNovacoopNonSoci;
    private String consensoProfilazioneCoopLiguria;
    private String consensoMarketingCoopLiguria;
    private String consensoProfilazioneCoopLiguriaSoci;
    private String consensoMarketingCoopLiguriaSoci;
    private String consensoProfilazioneCoopLiguriaNonSoci;
    private String consensoMarketingCoopLiguriaNonSoci;
    private String consensoProfilazioneCoopLombardia;
    private String consensoMarketingCoopLombardia;
    private String consensoProfilazioneCoopLombardiaSoci;
    private String consensoMarketingCoopLombardiaSoci;
    private String consensoProfilazioneCoopLombardiaNonSoci;
    private String consensoMarketingCoopLombardiaNonSoci;
    private String lastPrivacyUpdate;
    private String utenteDisabilitatoSuApp;
    private String utenteDisabilitatoSuEcommerce;
    private String utenteDisabilitatoSuCommunity;
    private String utenteDisabilitatoSuPortal;
    private String utenteDisabilitatoSuPortaleNova;
    private String utenteCancellato;

    public UserExcelDto() {
    }

    public UserExcelDto(String userId, String nome, String cognome, String email, String emailVerificata, String tessereSocio, String cooperativa, String applicazioneDiRegistrazione, String dataDiRegistrazione, String primoAccessoDaAppCoop, String ultimoAccessoDaAppCoop, String primoAccessoDaEcommerce, String ultimoAccessoDaEcommerce, String primoAccessoDaCommunity, String ultimoAccessoDaCommunity, String primoAccessoDaPortale, String ultimoAccessoDaPortale, String primoAccessoDaPortaleNova, String ultimoAccessoDaPortaleNova, String dataUltimaModifica, String stato, String dataDiNascita, String sesso, String tipologiaPersona, String provinciaResidenza, String cittaResidenza, String pdvId, String consensoProfilazioneNovacoop, String consensoMarketingNovacoop, String consensoProfilazioneNovacoopSoci, String consensoMarketingNovacoopSoci, String consensoProfilazioneNovacoopNonSoci, String consensoMarketingNovacoopNonSoci, String consensoProfilazioneCoopLiguria, String consensoMarketingCoopLiguria, String consensoProfilazioneCoopLiguriaSoci, String consensoMarketingCoopLiguriaSoci, String consensoProfilazioneCoopLiguriaNonSoci, String consensoMarketingCoopLiguriaNonSoci, String consensoProfilazioneCoopLombardia, String consensoMarketingCoopLombardia, String consensoProfilazioneCoopLombardiaSoci, String consensoMarketingCoopLombardiaSoci, String consensoProfilazioneCoopLombardiaNonSoci, String consensoMarketingCoopLombardiaNonSoci, String lastPrivacyUpdate, String utenteDisabilitatoSuApp, String utenteDisabilitatoSuEcommerce, String utenteDisabilitatoSuCommunity, String utenteDisabilitatoSuPortal, String utenteDisabilitatoSuPortaleNova, String utenteCancellato) {
        this.userId = userId;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.emailVerificata = emailVerificata;
        this.tessereSocio = tessereSocio;
        this.cooperativa = cooperativa;
        this.applicazioneDiRegistrazione = applicazioneDiRegistrazione;
        this.dataDiRegistrazione = dataDiRegistrazione;
        this.primoAccessoDaAppCoop = primoAccessoDaAppCoop;
        this.ultimoAccessoDaAppCoop = ultimoAccessoDaAppCoop;
        this.primoAccessoDaEcommerce = primoAccessoDaEcommerce;
        this.ultimoAccessoDaEcommerce = ultimoAccessoDaEcommerce;
        this.primoAccessoDaCommunity = primoAccessoDaCommunity;
        this.ultimoAccessoDaCommunity = ultimoAccessoDaCommunity;
        this.primoAccessoDaPortale = primoAccessoDaPortale;
        this.ultimoAccessoDaPortale = ultimoAccessoDaPortale;
        this.primoAccessoDaPortaleNova = primoAccessoDaPortaleNova;
        this.ultimoAccessoDaPortaleNova = ultimoAccessoDaPortaleNova;
        this.dataUltimaModifica = dataUltimaModifica;
        this.stato = stato;
        this.dataDiNascita = dataDiNascita;
        this.sesso = sesso;
        this.tipologiaPersona = tipologiaPersona;
        this.provinciaResidenza = provinciaResidenza;
        this.cittaResidenza = cittaResidenza;
        this.pdvId = pdvId;
        this.codicePdv = codicePdv;
        this.consensoProfilazioneNovacoop = consensoProfilazioneNovacoop;
        this.consensoMarketingNovacoop = consensoMarketingNovacoop;
        this.consensoProfilazioneNovacoopSoci = consensoProfilazioneNovacoopSoci;
        this.consensoMarketingNovacoopSoci = consensoMarketingNovacoopSoci;
        this.consensoProfilazioneNovacoopNonSoci = consensoProfilazioneNovacoopNonSoci;
        this.consensoMarketingNovacoopNonSoci = consensoMarketingNovacoopNonSoci;
        this.consensoProfilazioneCoopLiguria = consensoProfilazioneCoopLiguria;
        this.consensoMarketingCoopLiguria = consensoMarketingCoopLiguria;
        this.consensoProfilazioneCoopLiguriaSoci = consensoProfilazioneCoopLiguriaSoci;
        this.consensoMarketingCoopLiguriaSoci = consensoMarketingCoopLiguriaSoci;
        this.consensoProfilazioneCoopLiguriaNonSoci = consensoProfilazioneCoopLiguriaNonSoci;
        this.consensoMarketingCoopLiguriaNonSoci = consensoMarketingCoopLiguriaNonSoci;
        this.consensoProfilazioneCoopLombardia = consensoProfilazioneCoopLombardia;
        this.consensoMarketingCoopLombardia = consensoMarketingCoopLombardia;
        this.consensoProfilazioneCoopLombardiaSoci = consensoProfilazioneCoopLombardiaSoci;
        this.consensoMarketingCoopLombardiaSoci = consensoMarketingCoopLombardiaSoci;
        this.consensoProfilazioneCoopLombardiaNonSoci = consensoProfilazioneCoopLombardiaNonSoci;
        this.consensoMarketingCoopLombardiaNonSoci = consensoMarketingCoopLombardiaNonSoci;
        this.lastPrivacyUpdate = lastPrivacyUpdate;
        this.utenteDisabilitatoSuApp = utenteDisabilitatoSuApp;
        this.utenteDisabilitatoSuEcommerce = utenteDisabilitatoSuEcommerce;
        this.utenteDisabilitatoSuCommunity = utenteDisabilitatoSuCommunity;
        this.utenteDisabilitatoSuPortal = utenteDisabilitatoSuPortal;
        this.utenteDisabilitatoSuPortaleNova = utenteDisabilitatoSuPortaleNova;
        this.utenteCancellato = utenteCancellato;
    }
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailVerificata() {
        return emailVerificata;
    }

    public void setEmailVerificata(String emailVerificata) {
        this.emailVerificata = emailVerificata;
    }

    public String getTessereSocio() {
        return tessereSocio;
    }

    public void setTessereSocio(List<UserEanCardDto> eanCards) {
        List<String> e = new ArrayList<>();

        for (UserEanCardDto eanCard : eanCards) {
            e.add(eanCard.getEanCard() + ", " + eanCard.getCoopId());
        }
        StringBuilder sb = new StringBuilder();
        String comma = ", ";
        int i = 0;
        while (i < e.size() - 1) {
            sb.append(e.get(i));
            sb.append(comma);
            i++;
        }
        sb.append(e.get(i));

        this.tessereSocio = sb.toString();
    }

    public void setTessereSocio(String eanCards) {
        this.tessereSocio = eanCards;
    }

    public String getCooperativa() {
        return cooperativa;
    }

    public void setCooperativa(String coop) {
        this.cooperativa = coop;
    }

    public String getApplicazioneDiRegistrazione() {
        return applicazioneDiRegistrazione;
    }

    public void setApplicazioneDiRegistrazione(String applicazioneDiRegistrazione) {
        this.applicazioneDiRegistrazione = applicazioneDiRegistrazione;
    }

    public String getDataDiRegistrazione() {
        return dataDiRegistrazione;
    }

    public void setDataDiRegistrazione(String dataDiRegistrazione) {
        this.dataDiRegistrazione = dataDiRegistrazione;
    }

    public String getPrimoAccessoDaAppCoop() {
        return primoAccessoDaAppCoop;
    }

    public void setPrimoAccessoDaAppCoop(String primoAccessoDaAppCoop) {
        this.primoAccessoDaAppCoop = primoAccessoDaAppCoop;
    }

    public String getUltimoAccessoDaAppCoop() {
        return ultimoAccessoDaAppCoop;
    }

    public void setUltimoAccessoDaAppCoop(String ultimoAccessoDaAppCoop) {
        this.ultimoAccessoDaAppCoop = ultimoAccessoDaAppCoop;
    }

    public String getPrimoAccessoDaEcommerce() {
        return primoAccessoDaEcommerce;
    }

    public void setPrimoAccessoDaEcommerce(String primoAccessoDaEcommerce) {
        this.primoAccessoDaEcommerce = primoAccessoDaEcommerce;
    }

    public String getUltimoAccessoDaEcommerce() {
        return ultimoAccessoDaEcommerce;
    }

    public void setUltimoAccessoDaEcommerce(String ultimoAccessoDaEcommerce) {
        this.ultimoAccessoDaEcommerce = ultimoAccessoDaEcommerce;
    }

    public String getPrimoAccessoDaCommunity() {
        return primoAccessoDaCommunity;
    }

    public void setPrimoAccessoDaCommunity(String primoAccessoDaCommunity) {
        this.primoAccessoDaCommunity = primoAccessoDaCommunity;
    }

    public String getUltimoAccessoDaCommunity() {
        return ultimoAccessoDaCommunity;
    }

    public void setUltimoAccessoDaCommunity(String ultimoAccessoDaCommunity) {
        this.ultimoAccessoDaCommunity = ultimoAccessoDaCommunity;
    }

    public String getPrimoAccessoDaPortale() {
        return primoAccessoDaPortale;
    }

    public void setPrimoAccessoDaPortale(String primoAccessoDaPortale) {
        this.primoAccessoDaPortale = primoAccessoDaPortale;
    }

    public String getUltimoAccessoDaPortale() {
        return ultimoAccessoDaPortale;
    }

    public void setUltimoAccessoDaPortale(String ultimoAccessoDaPortale) {
        this.ultimoAccessoDaPortale = ultimoAccessoDaPortale;
    }

    public String getDataUltimaModifica() {
        return dataUltimaModifica;
    }

    public void setDataUltimaModifica(String dataUltimaModifica) {
        this.dataUltimaModifica = dataUltimaModifica;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public String getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(String dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getSesso() {
        return sesso;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public String getTipologiaPersona() {
        return tipologiaPersona;
    }

    public void setType(String type) {
        if (type != null) {
            this.tipologiaPersona = type;
        } else {
            this.tipologiaPersona = "-";
        }
    }

    public String getProvinciaResidenza() {
        return provinciaResidenza;
    }

    public void setProvinciaResidenza(String provinciaResidenza) {
        this.provinciaResidenza = provinciaResidenza;
    }

    public String getCittaResidenza() {
        return cittaResidenza;
    }

    public void setCittaResidenza(String cittaResidenza) {
        this.cittaResidenza = cittaResidenza;
    }

    public String getPdvId() {
        return pdvId;
    }

    public void setPdvId(String pdvId) {
        this.pdvId = pdvId;
    }

    public String getCodicePdv() {
        return codicePdv;
    }

    public void setCodicePdv(String codicePdv) {
        this.codicePdv = codicePdv;
    }

    public String getConsensoProfilazioneNovacoop() {
        return consensoProfilazioneNovacoop;
    }

    public void setConsensoProfilazioneNovacoop(String consensoProfilazioneNovacoop) {
        this.consensoProfilazioneNovacoop = consensoProfilazioneNovacoop;
    }

    public String getConsensoMarketingNovacoop() {
        return consensoMarketingNovacoop;
    }

    public void setConsensoMarketingNovacoop(String consensoMarketingNovacoop) {
        this.consensoMarketingNovacoop = consensoMarketingNovacoop;
    }

    public String getConsensoProfilazioneNovacoopSoci() {
        return consensoProfilazioneNovacoopSoci;
    }

    public void setConsensoProfilazioneNovacoopSoci(String consensoProfilazioneNovacoopSoci) {
        this.consensoProfilazioneNovacoopSoci = consensoProfilazioneNovacoopSoci;
    }

    public String getConsensoMarketingNovacoopSoci() {
        return consensoMarketingNovacoopSoci;
    }

    public void setConsensoMarketingNovacoopSoci(String consensoMarketingNovacoopSoci) {
        this.consensoMarketingNovacoopSoci = consensoMarketingNovacoopSoci;
    }

    public String getConsensoProfilazioneNovacoopNonSoci() {
        return consensoProfilazioneNovacoopNonSoci;
    }

    public void setConsensoProfilazioneNovacoopNonSoci(String consensoProfilazioneNovacoopNonSoci) {
        this.consensoProfilazioneNovacoopNonSoci = consensoProfilazioneNovacoopNonSoci;
    }

    public String getConsensoMarketingNovacoopNonSoci() {
        return consensoMarketingNovacoopNonSoci;
    }

    public void setConsensoMarketingNovacoopNonSoci(String consensoMarketingNovacoopNonSoci) {
        this.consensoMarketingNovacoopNonSoci = consensoMarketingNovacoopNonSoci;
    }

    public String getConsensoProfilazioneCoopLiguria() {
        return consensoProfilazioneCoopLiguria;
    }

    public void setConsensoProfilazioneCoopLiguria(String consensoProfilazioneCoopLiguria) {
        this.consensoProfilazioneCoopLiguria = consensoProfilazioneCoopLiguria;
    }

    public String getConsensoMarketingCoopLiguria() {
        return consensoMarketingCoopLiguria;
    }

    public void setConsensoMarketingCoopLiguria(String consensoMarketingCoopLiguria) {
        this.consensoMarketingCoopLiguria = consensoMarketingCoopLiguria;
    }

    public String getConsensoProfilazioneCoopLiguriaSoci() {
        return consensoProfilazioneCoopLiguriaSoci;
    }

    public void setConsensoProfilazioneCoopLiguriaSoci(String consensoProfilazioneCoopLiguriaSoci) {
        this.consensoProfilazioneCoopLiguriaSoci = consensoProfilazioneCoopLiguriaSoci;
    }

    public String getConsensoMarketingCoopLiguriaSoci() {
        return consensoMarketingCoopLiguriaSoci;
    }

    public void setConsensoMarketingCoopLiguriaSoci(String consensoMarketingCoopLiguriaSoci) {
        this.consensoMarketingCoopLiguriaSoci = consensoMarketingCoopLiguriaSoci;
    }

    public String getConsensoProfilazioneCoopLiguriaNonSoci() {
        return consensoProfilazioneCoopLiguriaNonSoci;
    }

    public void setConsensoProfilazioneCoopLiguriaNonSoci(String consensoProfilazioneCoopLiguriaNonSoci) {
        this.consensoProfilazioneCoopLiguriaNonSoci = consensoProfilazioneCoopLiguriaNonSoci;
    }

    public String getConsensoMarketingCoopLiguriaNonSoci() {
        return consensoMarketingCoopLiguriaNonSoci;
    }

    public void setConsensoMarketingCoopLiguriaNonSoci(String consensoMarketingCoopLiguriaNonSoci) {
        this.consensoMarketingCoopLiguriaNonSoci = consensoMarketingCoopLiguriaNonSoci;
    }

    public String getConsensoProfilazioneCoopLombardia() {
        return consensoProfilazioneCoopLombardia;
    }

    public void setConsensoProfilazioneCoopLombardia(String consensoProfilazioneCoopLombardia) {
        this.consensoProfilazioneCoopLombardia = consensoProfilazioneCoopLombardia;
    }

    public String getConsensoMarketingCoopLombardia() {
        return consensoMarketingCoopLombardia;
    }

    public void setConsensoMarketingCoopLombardia(String consensoMarketingCoopLombardia) {
        this.consensoMarketingCoopLombardia = consensoMarketingCoopLombardia;
    }

    public String getConsensoProfilazioneCoopLombardiaSoci() {
        return consensoProfilazioneCoopLombardiaSoci;
    }

    public void setConsensoProfilazioneCoopLombardiaSoci(String consensoProfilazioneCoopLombardiaSoci) {
        this.consensoProfilazioneCoopLombardiaSoci = consensoProfilazioneCoopLombardiaSoci;
    }

    public String getConsensoMarketingCoopLombardiaSoci() {
        return consensoMarketingCoopLombardiaSoci;
    }

    public void setConsensoMarketingCoopLombardiaSoci(String consensoMarketingCoopLombardiaSoci) {
        this.consensoMarketingCoopLombardiaSoci = consensoMarketingCoopLombardiaSoci;
    }

    public String getConsensoProfilazioneCoopLombardiaNonSoci() {
        return consensoProfilazioneCoopLombardiaNonSoci;
    }

    public void setConsensoProfilazioneCoopLombardiaNonSoci(String consensoProfilazioneCoopLombardiaNonSoci) {
        this.consensoProfilazioneCoopLombardiaNonSoci = consensoProfilazioneCoopLombardiaNonSoci;
    }

    public String getConsensoMarketingCoopLombardiaNonSoci() {
        return consensoMarketingCoopLombardiaNonSoci;
    }

    public void setConsensoMarketingCoopLombardiaNonSoci(String consensoMarketingCoopLombardiaNonSoci) {
        this.consensoMarketingCoopLombardiaNonSoci = consensoMarketingCoopLombardiaNonSoci;
    }

    public String getPrimoAccessoDaPortaleNova() {
        return primoAccessoDaPortaleNova;
    }

    public void setPrimoAccessoDaPortaleNova(String primoAccessoDaPortaleNova) {
        this.primoAccessoDaPortaleNova = primoAccessoDaPortaleNova;
    }

    public String getUltimoAccessoDaPortaleNova() {
        return ultimoAccessoDaPortaleNova;
    }

    public void setUltimoAccessoDaPortaleNova(String ultimoAccessoDaPortaleNova) {
        this.ultimoAccessoDaPortaleNova = ultimoAccessoDaPortaleNova;
    }

    public String getLastPrivacyUpdate() {
        return lastPrivacyUpdate;
    }

    public void setLastPrivacyUpdate(String lastPrivacyUpdate) {
        this.lastPrivacyUpdate = lastPrivacyUpdate;
    }

    public String getUtenteDisabilitatoSuApp() {
        return utenteDisabilitatoSuApp;
    }

    public void setUtenteDisabilitatoSuApp(String utenteDisabilitatoSuApp) {
        this.utenteDisabilitatoSuApp = utenteDisabilitatoSuApp;
    }

    public String getUtenteDisabilitatoSuEcommerce() {
        return utenteDisabilitatoSuEcommerce;
    }

    public void setUtenteDisabilitatoSuEcommerce(String utenteDisabilitatoSuEcommerce) {
        this.utenteDisabilitatoSuEcommerce = utenteDisabilitatoSuEcommerce;
    }

    public String getUtenteDisabilitatoSuCommunity() {
        return utenteDisabilitatoSuCommunity;
    }

    public void setUtenteDisabilitatoSuCommunity(String utenteDisabilitatoSuCommunity) {
        this.utenteDisabilitatoSuCommunity = utenteDisabilitatoSuCommunity;
    }

    public String getUtenteDisabilitatoSuPortal() {
        return utenteDisabilitatoSuPortal;
    }

    public void setUtenteDisabilitatoSuPortal(String utenteDisabilitatoSuPortal) {
        this.utenteDisabilitatoSuPortal = utenteDisabilitatoSuPortal;
    }

    public String getUtenteDisabilitatoSuPortaleNova() {
        return utenteDisabilitatoSuPortaleNova;
    }

    public void setUtenteDisabilitatoSuPortaleNova(String utenteDisabilitatoSuPortaleNova) {
        this.utenteDisabilitatoSuPortaleNova = utenteDisabilitatoSuPortaleNova;
    }

    public String getUtenteCancellato() {
        return utenteCancellato;
    }

    public void setUtenteCancellato(String utenteCancellato) {
        this.utenteCancellato = utenteCancellato;
    }
    
}

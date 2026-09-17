package com.nttdata.idmccnobe.dto;

import com.nttdata.idmccnobe.util.Constants;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author DelorenziVa
 */
public class UserDto implements Serializable{

    private static final long serialVersionUID = -8732603145820885405L;
    
    private String userId;
    private String name;
    private String surname;
    private String email;
    private String emailVerified; 
    private List<UserEanCardDto> eanCards;
    private String coopId;
    private String regApplication; // servizio utilizzato in registrazione
    private String regDate; // data di registrazione
    private String firstAppAccessDate;
    private String lastAppAccessDate;
    private String userDisableApp;
    private String firstEcommerceAccessDate;
    private String lastEcommerceAccessDate;
    private String userDisableEcommerce;
    private String firstEventiInteressiAccessDate;
    private String lastEventiInteressiAccessDate;
    private String userDisableEventiInteressi;
    private String firstPortalAccessDate;
    private String lastPortalAccessDate;
    private String userDisablePortal;
    private String firstPortalNovaAccessDate;
    private String lastPortalNovaAccessDate;
    private String userDisablePortalNova;
    private String status; // stato utente
    private String birthDate;
    private String gender;
    private String type; // -	Type (“F” persona fisica o “G” giuridica)
    private ProvinciaDto resProvince; // provincia di residenza
    private ComuneDto resCity; // città di residenza
    private List<UserPrivacyDto> userPrivacyList;
    private String pdvId;
    private String codicePdv;
    private String lastModifyDate;
    private String lastPrivacyUpdate;

    public UserDto() {
    }

    public UserDto(String userId, String name, String surname, String email, String emailVerified, List<UserEanCardDto> eanCards, String coopId, String regApplication, String regDate, String firstAppAccessDate, String lastAppAccessDate, String userDisableApp, String firstEcommerceAccessDate, String lastEcommerceAccessDate, String userDisableEcommerce, String firstCommunityAccessDate, String lastCommunityAccessDate, String userDisableCommunity, String firstPortalAccessDate, String lastPortalAccessDate, String userDisablePortal, String firstPortalNovaAccessDate, String lastPortalNovaAccessDate, String userDisablePortalNova, String status, String birthDate, String gender, String type, ProvinciaDto resProvince, ComuneDto resCity, List<UserPrivacyDto> userPrivacyList, String pdvId, String lastModifyDate, String lastPrivacyUpdate) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.emailVerified = emailVerified;
        this.eanCards = eanCards;
        this.coopId = coopId;
        this.regApplication = regApplication;
        this.regDate = regDate;
        this.firstAppAccessDate = firstAppAccessDate;
        this.lastAppAccessDate = lastAppAccessDate;
        this.userDisableApp = userDisableApp;
        this.firstEcommerceAccessDate = firstEcommerceAccessDate;
        this.lastEcommerceAccessDate = lastEcommerceAccessDate;
        this.userDisableEcommerce = userDisableEcommerce;
        this.firstEventiInteressiAccessDate = firstCommunityAccessDate;
        this.lastEventiInteressiAccessDate = lastCommunityAccessDate;
        this.userDisableEventiInteressi = userDisableCommunity;
        this.firstPortalAccessDate = firstPortalAccessDate;
        this.lastPortalAccessDate = lastPortalAccessDate;
        this.userDisablePortal = userDisablePortal;
        this.firstPortalNovaAccessDate = firstPortalNovaAccessDate;
        this.lastPortalNovaAccessDate = lastPortalNovaAccessDate;
        this.userDisablePortalNova = userDisablePortalNova;
        this.status = status;
        this.birthDate = birthDate;
        this.gender = gender;
        this.type = type;
        this.resProvince = resProvince;
        this.resCity = resCity;
        this.userPrivacyList = userPrivacyList;
        this.pdvId = pdvId;
        this.codicePdv = codicePdv;
        this.lastModifyDate = lastModifyDate;
        this.lastPrivacyUpdate = lastPrivacyUpdate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(String emailVerified) {      
        if (emailVerified.equalsIgnoreCase("1")){
            this.emailVerified = "Si";
        } else {
            this.emailVerified = "No";
        }
    }

    public List<UserEanCardDto> getEanCards() {
        return eanCards;
    }

    public void setEanCards(List<UserEanCardDto> eanCards) {
        this.eanCards = eanCards;
    }

    public String getCoopId() {
        return coopId;
    }

    public void setCoopId(String coopId) {
        if (coopId.equalsIgnoreCase("1")) {
            this.coopId = Constants.COOP_NOVACOOP;
        }
        if (coopId.equalsIgnoreCase("2")) {
            this.coopId = Constants.COOP_LIGURIA;
        }
        if (coopId.equalsIgnoreCase("3")) {
            this.coopId = Constants.COOP_LOMBARDIA;
        }
    }

    public String getRegApplication() {
        return regApplication;
    }

    public void setRegApplication(String regApplication) {
        this.regApplication = regApplication;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getFirstAppAccessDate() {
        return firstAppAccessDate;
    }

    public void setFirstAppAccessDate(String firstAppAccessDate) {
        this.firstAppAccessDate = firstAppAccessDate;
    }

    public String getLastAppAccessDate() {
        return lastAppAccessDate;
    }

    public void setLastAppAccessDate(String lastAppAccessDate) {
        this.lastAppAccessDate = lastAppAccessDate;
    }

    public String getFirstEcommerceAccessDate() {
        return firstEcommerceAccessDate;
    }

    public void setFirstEcommerceAccessDate(String firstEcommerceAccessDate) {
        this.firstEcommerceAccessDate = firstEcommerceAccessDate;
    }

    public String getLastEcommerceAccessDate() {
        return lastEcommerceAccessDate;
    }

    public void setLastEcommerceAccessDate(String lastEcommerceAccessDate) {
        this.lastEcommerceAccessDate = lastEcommerceAccessDate;
    }
    
    public String getFirstEventiInteressiAccessDate() {
        return firstEventiInteressiAccessDate;
    }

    public void setFirstEventiInteressiAccessDate(String firstEventiInteressiAccessDate) {
        this.firstEventiInteressiAccessDate = firstEventiInteressiAccessDate;
    }

    public String getLastEventiInteressiAccessDate() {
        return lastEventiInteressiAccessDate;
    }

    public void setLastEventiInteressiAccessDate(String lastEventiInteressiAccessDate) {
        this.lastEventiInteressiAccessDate = lastEventiInteressiAccessDate;
    }

    public String getFirstPortalAccessDate() {
        return firstPortalAccessDate;
    }

    public void setFirstPortalAccessDate(String firstPortalAccessDate) {
        this.firstPortalAccessDate = firstPortalAccessDate;
    }

    public String getLastPortalAccessDate() {
        return lastPortalAccessDate;
    }

    public void setLastPortalAccessDate(String lastPortalAccessDate) {
        this.lastPortalAccessDate = lastPortalAccessDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status.equalsIgnoreCase("0")) {
            this.status = Constants.USER_STATUS_INACTIVE;
        } 
        if (status.equalsIgnoreCase("1")) {
            this.status = Constants.USER_STATUS_ACTIVE;
        } 
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender.equalsIgnoreCase("F")) {
            this.gender = Constants.USER_GENDER_FEMALE;
        }
        if (gender.equalsIgnoreCase("M")) {
            this.gender = Constants.USER_GENDER_MALE;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type.equalsIgnoreCase("F")) {
            this.type = Constants.USER_TYPE_F;
        }
        if (type.equalsIgnoreCase("G")) {
            this.type = Constants.USER_TYPE_G;
        }
    }

    public ProvinciaDto getResProvince() {
        return resProvince;
    }

    public void setResProvince(ProvinciaDto resProvince) {
        this.resProvince = resProvince;
    }

    public ComuneDto getResCity() {
        return resCity;
    }

    public void setResCity(ComuneDto resCity) {
        this.resCity = resCity;
    }

    public List<UserPrivacyDto> getUserPrivacyList() {
        return userPrivacyList;
    }

    public void setUserPrivacyList(List<UserPrivacyDto> userPrivacyList) {
        this.userPrivacyList = userPrivacyList;
    }
    
    public String getPdvId() {
        return pdvId;
    }

    public void setPdvId(String pdvId) {
        this.pdvId = pdvId;
    }
    
    public String getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(String lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }
    
    public String getFirstPortalNovaAccessDate() {
        return firstPortalNovaAccessDate;
    }

    public void setFirstPortalNovaAccessDate(String firstPortalNovaAccessDate) {
        this.firstPortalNovaAccessDate = firstPortalNovaAccessDate;
    }

    public String getLastPortalNovaAccessDate() {
        return lastPortalNovaAccessDate;
    }

    public void setLastPortalNovaAccessDate(String lastPortalNovaAccessDate) {
        this.lastPortalNovaAccessDate = lastPortalNovaAccessDate;
    }

    public String getLastPrivacyUpdate() {
        return lastPrivacyUpdate;
    }

    public void setLastPrivacyUpdate(String lastPrivacyUpdate) {
        this.lastPrivacyUpdate = lastPrivacyUpdate;
    }

    public String getCodicePdv() {
        return codicePdv;
    }

    public void setCodicePdv(String codicePdv) {
        this.codicePdv = codicePdv;
    }

    public String getUserDisableApp() {
        return userDisableApp;
    }

    public void setUserDisableApp(String userDisableApp) {
        this.userDisableApp = userDisableApp;
    }

    public String getUserDisableEcommerce() {
        return userDisableEcommerce;
    }

    public void setUserDisableEcommerce(String userDisableEcommerce) {
        this.userDisableEcommerce = userDisableEcommerce;
    }

    public String getUserDisableEventiInteressi() {
        return userDisableEventiInteressi;
    }

    public void setUserDisableEventiInteressi(String userDisableEventiInteressi) {
        this.userDisableEventiInteressi = userDisableEventiInteressi;
    }

    public String getUserDisablePortal() {
        return userDisablePortal;
    }

    public void setUserDisablePortal(String userDisablePortal) {
        this.userDisablePortal = userDisablePortal;
    }

    public String getUserDisablePortalNova() {
        return userDisablePortalNova;
    }

    public void setUserDisablePortalNova(String userDisablePortalNova) {
        this.userDisablePortalNova = userDisablePortalNova;
    }
    
    
    
}

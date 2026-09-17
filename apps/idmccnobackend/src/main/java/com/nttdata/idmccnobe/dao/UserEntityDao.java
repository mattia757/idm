package com.nttdata.idmccnobe.dao;

import com.google.gson.Gson;
import com.nttdata.idmccnobe.dto.ComuneDto;
import com.nttdata.idmccnobe.dto.ProvinciaDto;
import com.nttdata.idmccnobe.dto.UserDto;
import com.nttdata.idmccnobe.dto.UserEanCardDto;
import com.nttdata.idmccnobe.dto.UserPrivacyDto;
import com.nttdata.idmccnobe.model.UserEntity;
import com.nttdata.idmccnobe.model.UserAttribute;
import com.nttdata.idmccnobe.service.ParametriService;
import com.nttdata.idmccnobe.util.CommonUtils;
import com.nttdata.idmccnobe.util.Constants;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.query.NativeQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author FacchettiM
 */
@Repository("UserEntityDao")
public class UserEntityDao extends AbstractDao<String, UserEntity>{
    
    @Autowired
    private ParametriService parametriService;
    
    public List<UserEntity> search(String coopId, String emailVerified, String socio) {
        CriteriaBuilder builder = criteriaBuilder();
        CriteriaQuery<UserEntity> query = builder.createQuery(UserEntity.class);
        Root<UserEntity> root = query.from(UserEntity.class);
        List<Predicate> predicates = new ArrayList<>();
        if (!CommonUtils.isNullOrEmpty(emailVerified) && emailVerified.equalsIgnoreCase("0") && !socio.equalsIgnoreCase("all")) { // verificato
            predicates.add(builder.isTrue(root.get("emailVerified")));
        }
        if (!CommonUtils.isNullOrEmpty(emailVerified) && emailVerified.equalsIgnoreCase("1") && !socio.equalsIgnoreCase("all")) { // non verificato
            predicates.add(builder.isFalse(root.get("emailVerified")));
        }

        if (!CommonUtils.isNullOrEmpty(socio) && socio.equalsIgnoreCase("1") && !socio.equalsIgnoreCase("all")) { // non socio
            Subquery<String> attributes = query.subquery(String.class);
            Root<UserAttribute> attribute = attributes.from(UserAttribute.class);
            attributes.select(attribute.get("id")).where(
                    builder.equal(attribute.get("userId"), root),
                    builder.equal(attribute.get("name"), Constants.KEYCLOAK_USER_ATTRIBUTE_FIDELITY_CARDS),
                    builder.or(builder.isNull(attribute.get("value")), builder.equal(attribute.get("value"), " ")));
            predicates.add(builder.exists(attributes));
        }

        if (!CommonUtils.isNullOrEmpty(coopId) && !socio.equalsIgnoreCase("all")) {
            Subquery<String> attributes = query.subquery(String.class);
            Root<UserAttribute> attribute = attributes.from(UserAttribute.class);
            attributes.select(attribute.get("id")).where(
                    builder.equal(attribute.get("userId"), root),
                    builder.equal(attribute.get("name"), Constants.KEYCLOAK_USER_ATTRIBUTE_COOPID),
                    builder.equal(attribute.get("value"), coopId));
            predicates.add(builder.exists(attributes));
        }
        predicates.add(builder.notEqual(root.get("email"), "segnalazioni.jira.am@nttdata.com"));
        query.where(predicates.toArray(Predicate[]::new));
        return getEntityManager().createQuery(query).getResultList();
    }
    
    public List<UserDto> searchSql(
            String coopId, 
            String emailVerified, 
            String socio, 
            Date registrationDateFrom,
            Date registrationDateTo,
            Date lastAppAccessDateFrom,
            Date lastAppAccessDateTo,
            boolean lastAppAccessDateIncludeNulls,
            Date lastEcommerceAccessDateFrom,
            Date lastEcommerceAccessDateTo,
            boolean lastEcommerceAccessDateIncludeNulls,
            Date lastCommunityAccessDateFrom,
            Date lastCommunityAccessDateTo,
            boolean lastCommunityAccessDateIncludeNulls,
            Date lastPortalAccessDateFrom,
            Date lastPortalAccessDateTo,
            boolean lastPortalAccessDateIncludeNulls,
            Date lastPortalNovaAccessDateFrom,
            Date lastPortalNovaAccessDateTo,
            boolean lastPortalNovaAccessDateIncludeNulls,
            Date lastModifyDateFrom,
            Date lastModifyDateTo,
            String pdvId) {
        DateFormat searchDateformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String administratorUsername = parametriService.getParametroByName(Constants.KEYCLOAK_REALM_ADMIN_USERNAME).getValore();
        boolean filterEmailVerified = !CommonUtils.isNullOrEmpty(emailVerified) && !emailVerified.equalsIgnoreCase("all");
        String query =  "SELECT USER_ENTITY.ID as ID, " +
                        "	USER_ENTITY.FIRST_NAME as FIRST_NAME, " +
                        "       USER_ENTITY.LAST_NAME as LAST_NAME, " +
                        "       USER_ENTITY.EMAIL as EMAIL, " +
                        "       USER_ENTITY.EMAIL_VERIFIED as EMAIL_VERIFIED, " +
                        "       USER_ENTITY.CREATED_TIMESTAMP as CREATED_TIMESTAMP, " +
                        "       USER_ENTITY.ENABLED as ENABLED, " +
                        "	GROUP_CONCAT((case when USER_ATTRIBUTE.NAME='fidelityCards' and IFNULL(USER_ATTRIBUTE.VALUE,'')!='' then USER_ATTRIBUTE.VALUE else null end)) as fidelityCards," +
                        "	MAX((case when USER_ATTRIBUTE.NAME='coopId' then USER_ATTRIBUTE.VALUE else null end)) as coopId," +
                        "       MAX((case when USER_ATTRIBUTE.NAME='application' then USER_ATTRIBUTE.VALUE else null end)) as application," +
                        "       MAX((case when USER_ATTRIBUTE.NAME='firstAppAccessDate' then USER_ATTRIBUTE.VALUE else null end)) as firstAppAccessDate," +
                        "	MAX((case when USER_ATTRIBUTE.NAME='lastAppAccessDate' then USER_ATTRIBUTE.VALUE else null end)) as lastAppAccessDate," +
                        "       MAX((case when USER_ATTRIBUTE.NAME='userDisableApp' then USER_ATTRIBUTE.VALUE else null end)) as userDisableApp," +
                        "       MAX((case when USER_ATTRIBUTE.NAME='firstEcommerceAccessDate' then USER_ATTRIBUTE.VALUE else null end)) as firstEcommerceAccessDate," +
                        "       MAX((case when USER_ATTRIBUTE.NAME='lastEcommerceAccessDate' then USER_ATTRIBUTE.VALUE else null end)) as lastEcommerceAccessDate," +
                        "       MAX((case when USER_ATTRIBUTE.NAME='userDisableEcommerce' then USER_ATTRIBUTE.VALUE else null end)) as userDisableEcommerce," +
                        "       MAX((case when USER_ATTRIBUTE.NAME='firstEventiInteressiAccessDate' then USER_ATTRIBUTE.VALUE else null end)) as firstEventiInteressiAccessDate," +
                        "	MAX((case when USER_ATTRIBUTE.NAME='lastEventiInteressiAccessDate' then USER_ATTRIBUTE.VALUE else null end)) as lastEventiInteressiAccessDate," +
                        "       MAX((case when USER_ATTRIBUTE.NAME='userDisableEventiInteressi' then USER_ATTRIBUTE.VALUE else null end)) as userDisableEventiInteressi," +
                        "       MAX((case when USER_ATTRIBUTE.NAME='firstPortalAccessDate' then USER_ATTRIBUTE.VALUE else null end)) as firstPortalAccessDate," +
                        "       MAX((case when USER_ATTRIBUTE.NAME='lastPortalAccessDate' then USER_ATTRIBUTE.VALUE else null end)) as lastPortalAccessDate," +
                        "       MAX((case when USER_ATTRIBUTE.NAME='userDisablePortal' then USER_ATTRIBUTE.VALUE else null end)) as userDisablePortal," +
                        "       MAX((case when USER_ATTRIBUTE.NAME='firstPortalNovaAccessDate' then USER_ATTRIBUTE.VALUE else null end)) as firstPortalNovaAccessDate," +
                        "       MAX((case when USER_ATTRIBUTE.NAME='lastPortalNovaAccessDate' then USER_ATTRIBUTE.VALUE else null end)) as lastPortalNovaAccessDate," +
                        "       MAX((case when USER_ATTRIBUTE.NAME='userDisablePortalNova' then USER_ATTRIBUTE.VALUE else null end)) as userDisablePortalNova," +
                        "       MAX((case when USER_ATTRIBUTE.NAME='lastModifyDate' then USER_ATTRIBUTE.VALUE else null end)) as lastModifyDate," +
                        "       MAX((case when USER_ATTRIBUTE.NAME='birthdate' then USER_ATTRIBUTE.VALUE else null end)) as birthdate," +
                        "       MAX((case when USER_ATTRIBUTE.NAME='gender' then USER_ATTRIBUTE.VALUE else null end)) as gender," +
                        "       MAX((case when USER_ATTRIBUTE.NAME='type' then USER_ATTRIBUTE.VALUE else null end)) as type," +
                        "       MAX((case when USER_ATTRIBUTE.NAME='pdvId' then USER_ATTRIBUTE.VALUE else null end)) as pdvId," +
                        "       MAX(CCNO_PROVINCIA.NAME)as 'residenceProvince'," +
                        "       MAX(CCNO_COMUNE.NAME) as 'residenceCity'," +
                        "       GROUP_CONCAT((case when USER_ATTRIBUTE.NAME='privacy' then USER_ATTRIBUTE.VALUE else null end) SEPARATOR '-') as privacy," +
                        "       MAX((case when USER_ATTRIBUTE.NAME = 'lastPrivacyUpdate' then USER_ATTRIBUTE.VALUE else NULL end)) as lastPrivacyUpdate " +
                        "  FROM USER_ENTITY" +
                        "       INNER JOIN USER_ATTRIBUTE ON USER_ATTRIBUTE.USER_ID = USER_ENTITY.ID" +
                        "       LEFT OUTER JOIN CCNO_PROVINCIA ON CCNO_PROVINCIA.ID = (case when USER_ATTRIBUTE.NAME='residenceProvince' then USER_ATTRIBUTE.VALUE else null end)" +
                        "       LEFT OUTER JOIN CCNO_COMUNE ON CCNO_COMUNE.ID = (case when USER_ATTRIBUTE.NAME='residenceCity' then USER_ATTRIBUTE.VALUE else null end)" +
                        " WHERE USER_ENTITY.ENABLED = 1 " +
                        "       AND USER_ENTITY.USERNAME != :administratorUsername " +
                        (filterEmailVerified ? " AND USER_ENTITY.EMAIL_VERIFIED = :emailVerified" : "") +
                        (!CommonUtils.isNullOrEmpty(coopId) ? " AND EXISTS (select 1 from USER_ATTRIBUTE where USER_ID = USER_ENTITY.ID and USER_ATTRIBUTE.NAME = 'coopId' AND USER_ATTRIBUTE.VALUE = :coopId)" : "") +
                        (!CommonUtils.isNullOrEmpty(socio) && !socio.equalsIgnoreCase("all") ? " AND " + (socio.equalsIgnoreCase("0") ? "NOT" : "") + " EXISTS (select 1 from USER_ATTRIBUTE where USER_ID = USER_ENTITY.ID and USER_ATTRIBUTE.NAME = 'fidelityCards' AND IFNULL(USER_ATTRIBUTE.VALUE,'')!='')" : "") +
                        (registrationDateFrom!=null ? " AND USER_ENTITY.CREATED_TIMESTAMP >= :registrationDateFrom" : "") +
                        (registrationDateTo!=null ? " AND USER_ENTITY.CREATED_TIMESTAMP <= :registrationDateTo" : "") +
                        (lastAppAccessDateFrom!=null ? " AND " +(lastAppAccessDateIncludeNulls ? "(NOT EXISTS(select 1 from USER_ATTRIBUTE where USER_ID = USER_ENTITY.ID and USER_ATTRIBUTE.NAME = 'lastAppAccessDate') OR " : "")+ "EXISTS (select 1 from USER_ATTRIBUTE where USER_ID = USER_ENTITY.ID and USER_ATTRIBUTE.NAME = 'lastAppAccessDate' AND DATE_FORMAT(str_to_date(USER_ATTRIBUTE.VALUE,'%d/%m/%Y %H:%i:%s'),'%Y-%m-%d %H:%i:%s') >= :lastAppAccessDateFrom)" +(lastAppAccessDateIncludeNulls ? ")" : "") : "") +
                        (lastAppAccessDateTo!=null ? " AND " +(lastAppAccessDateIncludeNulls ? "(NOT EXISTS(select 1 from USER_ATTRIBUTE where USER_ID = USER_ENTITY.ID and USER_ATTRIBUTE.NAME = 'lastAppAccessDate') OR " : "")+ "EXISTS (select 1 from USER_ATTRIBUTE where USER_ID = USER_ENTITY.ID and USER_ATTRIBUTE.NAME = 'lastAppAccessDate' AND DATE_FORMAT(str_to_date(USER_ATTRIBUTE.VALUE,'%d/%m/%Y %H:%i:%s'),'%Y-%m-%d %H:%i:%s') <= :lastAppAccessDateTo)" +(lastAppAccessDateIncludeNulls ? ")" : "") : "") +
                        (lastEcommerceAccessDateFrom!=null ? " AND " +(lastEcommerceAccessDateIncludeNulls ? "(NOT EXISTS(select 1 from USER_ATTRIBUTE where USER_ID = USER_ENTITY.ID and USER_ATTRIBUTE.NAME = 'lastEcommerceAccessDate') OR " : "")+ "EXISTS (select 1 from USER_ATTRIBUTE where USER_ID = USER_ENTITY.ID and USER_ATTRIBUTE.NAME = 'lastEcommerceAccessDate' AND DATE_FORMAT(str_to_date(USER_ATTRIBUTE.VALUE,'%d/%m/%Y %H:%i:%s'),'%Y-%m-%d %H:%i:%s') >= '"+searchDateformatter.format(lastEcommerceAccessDateFrom)+"')" +(lastEcommerceAccessDateIncludeNulls ? ")" : "") : "") +
                        (lastEcommerceAccessDateTo!=null ? " AND " +(lastEcommerceAccessDateIncludeNulls ? "(NOT EXISTS(select 1 from USER_ATTRIBUTE where USER_ID = USER_ENTITY.ID and USER_ATTRIBUTE.NAME = 'lastEcommerceAccessDate') OR " : "")+ "EXISTS (select 1 from USER_ATTRIBUTE where USER_ID = USER_ENTITY.ID and USER_ATTRIBUTE.NAME = 'lastEcommerceAccessDate' AND DATE_FORMAT(str_to_date(USER_ATTRIBUTE.VALUE,'%d/%m/%Y %H:%i:%s'),'%Y-%m-%d %H:%i:%s') <= '"+searchDateformatter.format(lastEcommerceAccessDateTo)+"')" + (lastEcommerceAccessDateIncludeNulls ? ")" : "") : "") +
                        (lastCommunityAccessDateFrom!=null ? " AND " +(lastCommunityAccessDateIncludeNulls ? "(NOT EXISTS(select 1 from USER_ATTRIBUTE where USER_ID = USER_ENTITY.ID and USER_ATTRIBUTE.NAME = 'lastEventiInteressiAccessDate') OR " : "")+ "EXISTS (select 1 from USER_ATTRIBUTE where USER_ID = USER_ENTITY.ID and USER_ATTRIBUTE.NAME = 'lastEventiInteressiAccessDate' AND DATE_FORMAT(str_to_date(USER_ATTRIBUTE.VALUE,'%d/%m/%Y %H:%i:%s'),'%Y-%m-%d %H:%i:%s') >= '"+searchDateformatter.format(lastCommunityAccessDateFrom)+"')" +(lastCommunityAccessDateIncludeNulls ? ")" : "") : "") +
                        (lastCommunityAccessDateTo!=null ? " AND " +(lastCommunityAccessDateIncludeNulls ? "(NOT EXISTS(select 1 from USER_ATTRIBUTE where USER_ID = USER_ENTITY.ID and USER_ATTRIBUTE.NAME = 'lastEventiInteressiAccessDate') OR " : "")+ "EXISTS (select 1 from USER_ATTRIBUTE where USER_ID = USER_ENTITY.ID and USER_ATTRIBUTE.NAME = 'lastEventiInteressiAccessDate' AND DATE_FORMAT(str_to_date(USER_ATTRIBUTE.VALUE,'%d/%m/%Y %H:%i:%s'),'%Y-%m-%d %H:%i:%s') <= '"+searchDateformatter.format(lastCommunityAccessDateTo)+"')" +(lastCommunityAccessDateIncludeNulls ? ")" : "") : "") +
                        (lastPortalAccessDateFrom!=null ? " AND " +(lastPortalAccessDateIncludeNulls ? "(NOT EXISTS(select 1 from USER_ATTRIBUTE where USER_ID = USER_ENTITY.ID and USER_ATTRIBUTE.NAME = 'lastPortalAccessDate') OR " : "")+ "EXISTS (select 1 from USER_ATTRIBUTE where USER_ID = USER_ENTITY.ID and USER_ATTRIBUTE.NAME = 'lastPortalAccessDate' AND DATE_FORMAT(str_to_date(USER_ATTRIBUTE.VALUE,'%d/%m/%Y %H:%i:%s'),'%Y-%m-%d %H:%i:%s') >= '"+searchDateformatter.format(lastPortalAccessDateFrom)+"')" +(lastPortalAccessDateIncludeNulls ? ")" : "") : "") +
                        (lastPortalAccessDateTo!=null ? " AND " +(lastPortalAccessDateIncludeNulls ? "(NOT EXISTS(select 1 from USER_ATTRIBUTE where USER_ID = USER_ENTITY.ID and USER_ATTRIBUTE.NAME = 'lastPortalAccessDate') OR " : "")+ "EXISTS (select 1 from USER_ATTRIBUTE where USER_ID = USER_ENTITY.ID and USER_ATTRIBUTE.NAME = 'lastPortalAccessDate' AND DATE_FORMAT(str_to_date(USER_ATTRIBUTE.VALUE,'%d/%m/%Y %H:%i:%s'),'%Y-%m-%d %H:%i:%s') <= '"+searchDateformatter.format(lastPortalAccessDateTo)+"')" +(lastPortalAccessDateIncludeNulls ? ")" : "") : "") +
                        (lastPortalNovaAccessDateFrom!=null ? " AND " +(lastPortalNovaAccessDateIncludeNulls ? "(NOT EXISTS(select 1 from USER_ATTRIBUTE where USER_ID = USER_ENTITY.ID and USER_ATTRIBUTE.NAME = 'lastPortalNovaAccessDate') OR " : "")+ "EXISTS (select 1 from USER_ATTRIBUTE where USER_ID = USER_ENTITY.ID and USER_ATTRIBUTE.NAME = 'lastPortalNovaAccessDate' AND DATE_FORMAT(str_to_date(USER_ATTRIBUTE.VALUE,'%d/%m/%Y %H:%i:%s'),'%Y-%m-%d %H:%i:%s') >= '"+searchDateformatter.format(lastPortalNovaAccessDateFrom)+"')" +(lastPortalNovaAccessDateIncludeNulls ? ")" : "") : "") +
                        (lastPortalNovaAccessDateTo!=null ? " AND " +(lastPortalNovaAccessDateIncludeNulls ? "(NOT EXISTS(select 1 from USER_ATTRIBUTE where USER_ID = USER_ENTITY.ID and USER_ATTRIBUTE.NAME = 'lastPortalNovaAccessDate') OR " : "")+ "EXISTS (select 1 from USER_ATTRIBUTE where USER_ID = USER_ENTITY.ID and USER_ATTRIBUTE.NAME = 'lastPortalNovaAccessDate' AND DATE_FORMAT(str_to_date(USER_ATTRIBUTE.VALUE,'%d/%m/%Y %H:%i:%s'),'%Y-%m-%d %H:%i:%s') <= '"+searchDateformatter.format(lastPortalNovaAccessDateTo)+"')" +(lastPortalNovaAccessDateIncludeNulls ? ")" : "") : "") +
                        (lastModifyDateFrom!=null ? " AND EXISTS (select 1 from USER_ATTRIBUTE where USER_ID = USER_ENTITY.ID and USER_ATTRIBUTE.NAME = 'lastModifyDate' AND USER_ATTRIBUTE.VALUE >= '"+searchDateformatter.format(lastModifyDateFrom)+"')" : "") +
                        (lastModifyDateTo!=null ? " AND EXISTS (select 1 from USER_ATTRIBUTE where USER_ID = USER_ENTITY.ID and USER_ATTRIBUTE.NAME = 'lastModifyDate' AND USER_ATTRIBUTE.VALUE <= '"+searchDateformatter.format(lastModifyDateTo)+"')" : "") +
                        (!CommonUtils.isNullOrEmpty(pdvId) ? " AND EXISTS (select 1 from USER_ATTRIBUTE where USER_ID = USER_ENTITY.ID and USER_ATTRIBUTE.NAME = 'pdvId' AND USER_ATTRIBUTE.VALUE = :pdvId)" : "") +
                        " GROUP BY USER_ENTITY.ID, " +
                        "       USER_ENTITY.FIRST_NAME, " +
                        "       USER_ENTITY.LAST_NAME, " +
                        "       USER_ENTITY.EMAIL, " +
                        "       USER_ENTITY.EMAIL_VERIFIED, " +
                        "       USER_ENTITY.CREATED_TIMESTAMP, " +
                        "       USER_ENTITY.ENABLED" +
                        " ORDER BY USER_ENTITY.EMAIL";
        List<UserDto> users = new ArrayList<UserDto>();
        NativeQuery<Map<String, Object>> sqlQuery = getEntityManager().createNativeQuery(query).unwrap(NativeQuery.class);
        sqlQuery.setParameter("administratorUsername", administratorUsername);
        sqlQuery.setTupleTransformer((tuple, aliases) -> {
            Map<String, Object> row = new HashMap<>();
            for (int index = 0; index < aliases.length; index++) {
                row.put(aliases[index], tuple[index]);
            }
            return row;
        });
        if (filterEmailVerified) {
            sqlQuery.setParameter("emailVerified", "1".equals(emailVerified) || "true".equalsIgnoreCase(emailVerified));
        }
        if (!CommonUtils.isNullOrEmpty(coopId)) {
            sqlQuery.setParameter("coopId", coopId);
        }
        if (registrationDateFrom != null) {
            sqlQuery.setParameter("registrationDateFrom", registrationDateFrom.getTime());
        }
        if (registrationDateTo != null) {
            sqlQuery.setParameter("registrationDateTo", registrationDateTo.getTime());
        }
        if (lastAppAccessDateFrom != null) {
            sqlQuery.setParameter("lastAppAccessDateFrom", searchDateformatter.format(lastAppAccessDateFrom));
        }
        if (lastAppAccessDateTo != null) {
            sqlQuery.setParameter("lastAppAccessDateTo", searchDateformatter.format(lastAppAccessDateTo));
        }
        if (!CommonUtils.isNullOrEmpty(pdvId)) {
            sqlQuery.setParameter("pdvId", pdvId);
        }
        List<Map<String, Object>> result = sqlQuery.getResultList();
        if (result!=null && !result.isEmpty()) {
            for (Map<String, Object> row : result) {
                UserDto user = new UserDto();
                // testata utente
                user.setUserId((String)row.get("ID"));
                user.setName((String)row.get("FIRST_NAME"));
                user.setSurname((String)row.get("LAST_NAME"));
                user.setEmail((String)row.get("EMAIL"));
                user.setEmailVerified(((Boolean)row.get("EMAIL_VERIFIED")) ? "1" : "0");
                user.setRegDate(((BigInteger)row.get("CREATED_TIMESTAMP")).toString()); //private String regDate; // data di registrazione
                user.setStatus(((Boolean)row.get("ENABLED")) ? "1" : "0"); //private String status; // stato utente
                // attributi utente
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_FIDELITY_CARDS)!=null) {
                    String[] eanCards = ((String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_FIDELITY_CARDS)).split(",");
                    if (eanCards!=null && eanCards.length>0) {
                        user.setEanCards(new ArrayList<>());
                        for (String eanCard : eanCards) {
                            user.getEanCards().add(new UserEanCardDto(eanCard,null)); //private List<UserEanCardDto> eanCards;
                        }
                    }
                }
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_COOPID)!=null) {
                    user.setCoopId((String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_COOPID)); //private String coopId;
                }
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_REG_APPLICATION)!=null) {
                    user.setRegApplication((String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_REG_APPLICATION)); //private String regApplication; // servizio utilizzato in registrazione
                }
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_FIRST_APP_ACCESS_DATE)!=null) {
                    user.setFirstAppAccessDate((String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_FIRST_APP_ACCESS_DATE)); //private String firstAppAccessDate;
                }
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_LAST_APP_ACCESS_DATE)!=null) {
                    user.setLastAppAccessDate((String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_LAST_APP_ACCESS_DATE)); //private String lastAppAccessDate;
                }
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_DELETE_APP)!=null) {
                    user.setUserDisableApp(((String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_DELETE_APP)).equalsIgnoreCase("true") ? "SI" : "NO");
                }
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_FIRST_ECOMMERCE_ACCESS_DATE)!=null) {
                    user.setFirstEcommerceAccessDate((String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_FIRST_ECOMMERCE_ACCESS_DATE)); //private String firstEcommerceAccessDate;
                }
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_LAST_ECOMMERCE_ACCESS_DATE)!=null) {
                    user.setLastEcommerceAccessDate((String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_LAST_ECOMMERCE_ACCESS_DATE)); //private String lastEcommerceAccessDate;
                }
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_DELETE_ECOMMERCE)!=null) {
                    user.setUserDisableEcommerce(((String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_DELETE_ECOMMERCE)).equalsIgnoreCase("true") ? "SI" : "NO");
                }
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_FIRST_EVENTI_INTERESSI_ACCESS_DATE)!=null) {
                    user.setFirstEventiInteressiAccessDate((String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_FIRST_EVENTI_INTERESSI_ACCESS_DATE)); //private String firstEventiInteressiAccessDate;
                }
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_LAST_EVENTI_INTERESSI_ACCESS_DATE)!=null) {
                    user.setLastEventiInteressiAccessDate((String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_LAST_EVENTI_INTERESSI_ACCESS_DATE)); //private String lastEventiInteressiAccessDate;
                }
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_USER_DISABLE_EVENTI_INTERESSI)!=null) {
                    user.setUserDisableEventiInteressi(((String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_USER_DISABLE_EVENTI_INTERESSI)).equalsIgnoreCase("true") ? "SI" : "NO");
                }
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_FIRST_PORTAL_ACCESS_DATE)!=null) {
                    user.setFirstPortalAccessDate((String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_FIRST_PORTAL_ACCESS_DATE)); //private String firstPortalAccessDate;
                }
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_LAST_PORTAL_ACCESS_DATE)!=null) {
                    user.setLastPortalAccessDate((String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_LAST_PORTAL_ACCESS_DATE)); //private String lastPortalAccessDate;
                }
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_DELETE_PORTAL)!=null) {
                    user.setUserDisablePortal(((String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_DELETE_PORTAL)).equalsIgnoreCase("true") ? "SI" : "NO");
                }
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_FIRST_PORTAL_ACCESS_DATE)!=null) {
                    user.setFirstPortalNovaAccessDate((String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_FIRST_PORTAL_NOVA_ACCESS_DATE)); //private String firstPortalNovaAccessDate;
                }
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_LAST_PORTAL_ACCESS_DATE)!=null) {
                    user.setLastPortalNovaAccessDate((String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_LAST_PORTAL_NOVA_ACCESS_DATE)); //private String lastPortalNovaAccessDate;
                }
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_DELETE_PORTAL_NOVA)!=null) {
                    user.setUserDisablePortalNova(((String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_DELETE_PORTAL_NOVA)).equalsIgnoreCase("true") ? "SI" : "NO");
                }
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_BIRTHDATE)!=null) {
                    user.setBirthDate((String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_BIRTHDATE)); //private String birthDate;
                }
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_GENDER)!=null) {
                    user.setGender((String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_GENDER)); //private String gender;
                }
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_TYPE)!=null) {
                    user.setType((String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_TYPE)); //private String type; // -	Type (“F” persona fisica o “G” giuridica)
                }
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_PROVINCE)!=null) {
                    user.setResProvince(new ProvinciaDto(null,(String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_PROVINCE),null)); //private ProvinciaDto resProvince; // provincia di residenza
                }
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_CITY)!=null) {
                    user.setResCity(new ComuneDto(null,(String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_CITY))); //private ComuneDto resCity; // città di residenza
                }
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_PRIVACY)!=null) {
                    String[] optinJsonList = ((String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_PRIVACY)).split("-");
                    if (optinJsonList!=null && optinJsonList.length>0) {
                        user.setUserPrivacyList(new ArrayList<>());
                        for (String optinJson : optinJsonList) {
                            Gson gson = new Gson();
                            user.getUserPrivacyList().add(gson.fromJson(optinJson, UserPrivacyDto.class)); //private List<UserPrivacyDto> userPrivacyList;
                        }
                    }
                }
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_PDV_ID)!=null) {
                    user.setPdvId((String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_PDV_ID));
                }
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_LAST_MODIFY_DATE)!=null) {
                    try {
                        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        String lastModifyDateAttributeStr = (String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_LAST_MODIFY_DATE);
                        Date lastModifyDateAttributeDate = searchDateformatter.parse(lastModifyDateAttributeStr);
                        user.setLastModifyDate(formatter.format(lastModifyDateAttributeDate));
                    } catch (Exception ex) {
                        logger.error(ex);
                    }
                }
                if (row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_LAST_PRIVACY_UPDATE)!=null) {
                    try {
                        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        String lastPrivacyUpdateStr = (String)row.get(Constants.KEYCLOAK_USER_ATTRIBUTE_LAST_PRIVACY_UPDATE);                        
                        Date lastPrivacyUpdateDate = searchDateformatter.parse(lastPrivacyUpdateStr);
                        user.setLastPrivacyUpdate(formatter.format(lastPrivacyUpdateDate));
                    } catch (Exception ex) {
                        logger.error(ex);
                    }
                }                
                users.add(user);
            }
        }
        return users;
    }
    
}

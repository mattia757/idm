package com.nttdata.idmccnobe.controller;

import com.nttdata.idmccnobe.dto.ExportUserCancelledExcelRequest;
import com.nttdata.idmccnobe.dto.ExportUserExcelRequest;
import com.nttdata.idmccnobe.dto.ExportUserInactiveExcelRequest;
import com.nttdata.idmccnobe.dto.UserExcelDto;
import com.nttdata.idmccnobe.dto.UserProfile;
import com.nttdata.idmccnobe.service.ExportUsersService;
import com.nttdata.idmccnobe.service.UtilsService;
import com.nttdata.idmccnobe.util.CommonUtils;
import com.nttdata.idmccnobe.util.Constants;
import com.nttdata.idmccnobe.view.ExcelView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author DelorenziVa
 */
@RestController
@RequestMapping("/exportExcel")
public class ExportExcelController extends AbstractController {

    @Autowired
    private ExportUsersService exportUsersService;

    @Autowired
    private UtilsService utilsService;
    
    @RequestMapping(value={"/utenti"}, method = RequestMethod.GET)
    public ModelAndView viewExportPageUtenti(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile){
        ModelAndView modelAndView = new ModelAndView("/jsp/template/template", "page", "/jsp/exportExcel/utenti");
        modelAndView.addObject("section", "Esporta utenti");
        modelAndView.addObject("subsection", "Ricerca");
        modelAndView.addObject("userProfile", userProfile);
        return modelAndView;
    }
    
    @RequestMapping(value={"/utentiInattivi"}, method = RequestMethod.GET)
    public ModelAndView viewExportPageUtentiInattivi(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile){
        ModelAndView modelAndView = new ModelAndView("/jsp/template/template", "page", "/jsp/exportExcel/utentiInattivi");
        modelAndView.addObject("section", "Esporta utenti inattivi");
        modelAndView.addObject("subsection", "Ricerca");
        modelAndView.addObject("userProfile", userProfile);
        return modelAndView;
    }
    
    @RequestMapping(value={"/utentiCancellati"}, method = RequestMethod.GET)
    public ModelAndView viewExportPageUtentiCancellati(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile){
        ModelAndView modelAndView = new ModelAndView("/jsp/template/template", "page", "/jsp/exportExcel/utentiCancellati");
        modelAndView.addObject("section", "Esporta utenti cancellati");
        modelAndView.addObject("subsection", "Ricerca");
        modelAndView.addObject("userProfile", userProfile);
        return modelAndView;
    }
    
    @PostMapping(value="/exportUtenti")
    public ModelAndView exportUtenti(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile, @ModelAttribute("exportUserExcelRequest") ExportUserExcelRequest exportUserExcelRequest, HttpServletResponse response, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        String coopId = exportUserExcelRequest.getCoopId();
        String emailVerified = exportUserExcelRequest.getEmailVerified();
        String socio = exportUserExcelRequest.getSocio();
        Integer cooperativa = userProfile.getIdCooperativa();
        String username = userProfile.getUsername();
        Date registrationDateFrom = exportUserExcelRequest.getRegistrationDateFrom();
        Date registrationDateTo = exportUserExcelRequest.getRegistrationDateTo();
        Date lastAppAccessDateFrom = exportUserExcelRequest.getLastAppAccessDateFrom();
        Date lastAppAccessDateTo = exportUserExcelRequest.getLastAppAccessDateTo();
        Date lastEcommerceAccessDateFrom = exportUserExcelRequest.getLastEcommerceAccessDateFrom();
        Date lastEcommerceAccessDateTo = exportUserExcelRequest.getLastEcommerceAccessDateTo();
        Date lastCommunityAccessDateFrom = exportUserExcelRequest.getLastCommunityAccessDateFrom();
        Date lastCommunityAccessDateTo = exportUserExcelRequest.getLastCommunityAccessDateTo();
        Date lastPortalAccessDateFrom = exportUserExcelRequest.getLastPortalAccessDateFrom();
        Date lastPortalAccessDateTo = exportUserExcelRequest.getLastPortalAccessDateTo();
        Date lastModifyDateFrom = exportUserExcelRequest.getLastModifyDateFrom();
        Date lastModifyDateTo = exportUserExcelRequest.getLastModifyDateTo();
        Date lastPortalNovaAccessDateFrom = exportUserExcelRequest.getLastPortalNovaAccessDateFrom();
        Date lastPortalNovaAccessDateTo = exportUserExcelRequest.getLastPortalNovaAccessDateTo();
        String pdvId = exportUserExcelRequest.getPdvId();
        String codicePdv = exportUserExcelRequest.getCodicePdv();
        boolean includeRemovedUsers = exportUserExcelRequest.isIncludeRemovedUsers();
        String parameters = exportUserExcelRequest.toString();
        String descrizione = "Esportazione utenti con parametri: "
                             + "id cooperativa = "+exportUserExcelRequest.getCoopId()
                             + ", email verificata = "+ (emailVerified!=null && !emailVerified.isEmpty() ? (emailVerified.equals("all") ? "tutti" : (emailVerified.equals("1") ? "si" : "no")) : "") 
                             + ", socio = "+(socio!=null && !socio.isEmpty() ? (socio.equals("all") ? "tutti" : (socio.equals("1") ? "si" : "no")) : "")
                             + ", data registrazione da " + registrationDateFrom + " a " + registrationDateTo
                             + ", data ultimo accesso App da " + lastAppAccessDateFrom + " a " + lastAppAccessDateTo
                             + ", data ultimo accesso Ecommerce da " + lastEcommerceAccessDateFrom + " a " + lastEcommerceAccessDateTo
                             + ", data ultimo accesso Community da " + lastCommunityAccessDateFrom + " a " + lastCommunityAccessDateTo
                             + ", data ultimo accesso Portale da " + lastPortalAccessDateFrom + " a " + lastPortalAccessDateTo
                             + ", data ultimo accesso Portale da " + lastPortalNovaAccessDateFrom + " a " + lastPortalNovaAccessDateTo
                             + ", data ultima modifica da " + lastModifyDateFrom + " a " + lastModifyDateTo
                             + ", pdvId = " + pdvId
                             + ", includeRemovedUsers = " + includeRemovedUsers;
        logger.info(descrizione);
        utilsService.traceLog(cooperativa,Constants.IDM_CCNO_BACKEND_APPLICATION,username,logger.getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName(),parameters,descrizione);
        long startTime = System.currentTimeMillis();
        if( CommonUtils.isNullOrEmpty(coopId) ||
            CommonUtils.isNullOrEmpty(socio) ||
            CommonUtils.isNullOrEmpty(emailVerified)) {
            modelAndView = viewExportPageUtenti(userProfile);
            modelAndView.addObject(Constants.ERROR_MESSAGE, "Per avviare l'export selezionare la cooperativa");
            logger.info("exportUsers -> " + Constants.ERROR_MESSAGE + ", Compilare tutti i campi");
        } else if (!canAccessCooperativa(userProfile, coopId)) {
            logger.warn("Tentativo di export utenti non autorizzato: utente=" + username + ", coopId=" + coopId);
            modelAndView = viewExportPageUtenti(userProfile);
            modelAndView.addObject(Constants.ERROR_MESSAGE, "Operazione non autorizzata per la cooperativa selezionata");
        } else {
            try {
                List<UserExcelDto> users = new ArrayList<>();
                List<UserExcelDto> activeUsers = exportUsersService.searchUsersToExport(
                        coopId, 
                        emailVerified, 
                        socio,
                        registrationDateFrom,
                        registrationDateTo,
                        lastAppAccessDateFrom,
                        lastAppAccessDateTo,
                        false,
                        lastEcommerceAccessDateFrom,
                        lastEcommerceAccessDateTo,
                        false,
                        lastCommunityAccessDateFrom,
                        lastCommunityAccessDateTo,
                        false,
                        lastPortalAccessDateFrom,
                        lastPortalAccessDateTo,
                        false,
                        lastPortalNovaAccessDateFrom,
                        lastPortalNovaAccessDateTo,
                        false,
                        lastModifyDateFrom,
                        lastModifyDateTo,
                        pdvId                        
                );
                if (activeUsers!=null && !activeUsers.isEmpty()) {
                    users.addAll(activeUsers);
                }
                if (includeRemovedUsers) {
                    List<UserExcelDto> removedUsers = exportUsersService.getRemovedUsersToExcel(coopId, null);
                    if (removedUsers!=null && !removedUsers.isEmpty()) {
                        users.addAll(removedUsers);
                    }
                }
                if (users != null && !users.isEmpty()) {
                    startTime = System.currentTimeMillis();
                    logger.info("exportUsers -> Start to create Excel file");
                    modelAndView = exportExcel(users, "Estrazione-utenti");
                    modelAndView.addObject(Constants.SUCCESS_MESSAGE, "Esportazione terminata con successo");
                } else {
                    modelAndView = viewExportPageUtenti(userProfile);
                    logger.info(Constants.ERROR_MESSAGE+", Nessun utente trovato");
                    modelAndView.addObject(Constants.ERROR_MESSAGE, "Nessun utente trovato");
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                modelAndView = viewExportPageUtenti(userProfile);
                modelAndView.addObject(Constants.ERROR_MESSAGE, "Errore durante la generazione del file excel");
            }
        }
        long stopTime = System.currentTimeMillis();  
        logger.info("exportUsers -> Finish to create Excel file, tempo impiegato: " + (stopTime - startTime)/1000 + "s");
        addExportCompletedCookie(response, request);
        return modelAndView;
    }
    
    @PostMapping(value="/exportUtentiInattivi")
    public ModelAndView exportUtentiInattivi(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile, @ModelAttribute("exportUserInactiveExcelRequest") ExportUserInactiveExcelRequest exportUserInactiveExcelRequest, HttpServletResponse response, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        String application = exportUserInactiveExcelRequest.getApplication();
        Integer cooperativa = userProfile.getIdCooperativa();
        String username = userProfile.getUsername();
        String parameters = exportUserInactiveExcelRequest.toString();
        String descrizione = "Esportazione utenti inattivi con parametri: "
                             + "application = "+application
                             + ", cooperativa = "+cooperativa;
        logger.info(descrizione);
        utilsService.traceLog(cooperativa,Constants.IDM_CCNO_BACKEND_APPLICATION,username,logger.getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName(),parameters,descrizione);
        long startTime = System.currentTimeMillis();
        try {
            if (!CommonUtils.isNullOrEmpty(application)) {
                List<UserExcelDto> users = exportUsersService.getInactiveUsersToExcel(application);
                if (users != null && !users.isEmpty()) {
                    startTime = System.currentTimeMillis();
                    logger.info("exportUsers -> Start to create Excel file");
                    modelAndView = exportExcel(users,"Estrazione-utenti-inattivi");
                    modelAndView.addObject(Constants.SUCCESS_MESSAGE, "Esportazione terminata con successo");
                } else {
                    modelAndView = viewExportPageUtentiInattivi(userProfile);
                    logger.info(Constants.ERROR_MESSAGE+", Nessun utente trovato");
                    modelAndView.addObject(Constants.ERROR_MESSAGE, "Nessun utente trovato");
                }
            } else {
                modelAndView = viewExportPageUtentiInattivi(userProfile);
                modelAndView.addObject(Constants.ERROR_MESSAGE, "Parametro applicativo non specificato");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            modelAndView = viewExportPageUtentiInattivi(userProfile);
            modelAndView.addObject(Constants.ERROR_MESSAGE, "Errore durante la generazione del file excel");
        }
        long stopTime = System.currentTimeMillis();  
        logger.info("exportUsers -> Finish to create Excel file, tempo impiegato: " + (stopTime - startTime)/1000 + "s");
        addExportCompletedCookie(response, request);
        return modelAndView;
    }
    
    @PostMapping(value="/exportUtentiCancellati")
    public ModelAndView exportUtentiCancellati(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile, @ModelAttribute("exportUserCancelledExcelRequest") ExportUserCancelledExcelRequest exportUserCancelledExcelRequest, HttpServletResponse response, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        String coopId = exportUserCancelledExcelRequest.getCoopId();
        String email = exportUserCancelledExcelRequest.getEmail();
        Integer cooperativa = userProfile.getIdCooperativa();
        String username = userProfile.getUsername();
        String parameters = exportUserCancelledExcelRequest.toString();
        String descrizione = "Esportazione utenti cancellati con parametri: "
                            + "coopId = "+coopId
                            + ", email = "+email;
        logger.info(descrizione);
        utilsService.traceLog(cooperativa,Constants.IDM_CCNO_BACKEND_APPLICATION,username,logger.getName()+"."+new Object(){}.getClass().getEnclosingMethod().getName(),parameters,descrizione);
        long startTime = System.currentTimeMillis();
        if (!canAccessCooperativa(userProfile, coopId)) {
            logger.warn("Tentativo di export utenti cancellati non autorizzato: utente=" + username + ", coopId=" + coopId);
            modelAndView = viewExportPageUtentiCancellati(userProfile);
            modelAndView.addObject(Constants.ERROR_MESSAGE, "Operazione non autorizzata per la cooperativa selezionata");
        } else try {
            List<UserExcelDto> users = exportUsersService.getRemovedUsersToExcel(coopId, email);
            if (users != null && !users.isEmpty()) {
                startTime = System.currentTimeMillis();
                logger.info("exportUsers -> Start to create Excel file");
                modelAndView = exportExcel(users,"Estrazione-utenti-cancellati");
                modelAndView.addObject(Constants.SUCCESS_MESSAGE, "Esportazione terminata con successo");
            } else {
                modelAndView = viewExportPageUtentiCancellati(userProfile);
                logger.info(Constants.ERROR_MESSAGE+", Nessun utente trovato");
                modelAndView.addObject(Constants.ERROR_MESSAGE, "Nessun utente trovato");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            modelAndView = viewExportPageUtentiCancellati(userProfile);
            modelAndView.addObject(Constants.ERROR_MESSAGE, "Errore durante la generazione del file excel");
        }
        long stopTime = System.currentTimeMillis();  
        logger.info("exportUsers -> Finish to create Excel file, tempo impiegato: " + (stopTime - startTime)/1000 + "s");
        addExportCompletedCookie(response, request);
        return modelAndView;
    }
    
    private ModelAndView exportExcel(List<UserExcelDto> resultList, String fileName) {
        ModelAndView modelAndView = new ModelAndView(new ExcelView(), Constants.EXCEL_DATA_KEY, resultList);
        modelAndView.addObject(Constants.EXCEL_FILENAME_KEY, fileName);
        return modelAndView;
    }

    private void addExportCompletedCookie(HttpServletResponse response, HttpServletRequest request) {
        Cookie cookie = new Cookie("ExportCompleted", "1");
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);
    }

    private boolean canAccessCooperativa(UserProfile userProfile, String coopId) {
        if (userProfile == null || userProfile.getRole() == null || coopId == null) {
            return false;
        }
        if ("ADMIN".equalsIgnoreCase(userProfile.getRole()) || "CCNO".equalsIgnoreCase(userProfile.getRole())) {
            return true;
        }
        return userProfile.getIdCooperativa() != null
                && String.valueOf(userProfile.getIdCooperativa()).equals(coopId.trim());
    }
}

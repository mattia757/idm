/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.controller;

import com.nttdata.idmccnobe.dto.NotificaPushExcelDto;
import com.nttdata.idmccnobe.dto.UserProfile;
import com.nttdata.idmccnobe.service.CaricamentoService;
import com.nttdata.idmccnobe.util.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author BernardisMa
 */

@Controller
@RequestMapping("/caricamento")
public class CaricamentoController extends AbstractController {
    
    protected Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());
    
    @Autowired
    private CaricamentoService caricamentoService;
    
    @GetMapping("/notifichePush")
    public ModelAndView loadPageForPushNotification() {
        ModelAndView modelAndView = new ModelAndView("/jsp/template/template", "page", "/jsp/notifichePush/caricamentoNotifichePush");
        modelAndView.addObject("section", "Notifiche Push");
        modelAndView.addObject("subsection", "Carica");
        return modelAndView;
    }   
    
    @PostMapping(value = "/caricaNotifichePush", consumes = { "multipart/form-data" })
    public ModelAndView pushNotificationLoad(@RequestAttribute(Constants.USER_PROFILE) UserProfile userProfile, @ModelAttribute("uplFileNotifichePush") MultipartFile uplFileNotifichePush) throws IOException {
        ModelAndView modelAndView = loadPageForPushNotification();
        if (uplFileNotifichePush != null && uplFileNotifichePush.getOriginalFilename() != null 
                    && (uplFileNotifichePush.getOriginalFilename().endsWith("xls") || uplFileNotifichePush.getOriginalFilename().endsWith("xlsx"))) {
            logger.info("Filename: " + uplFileNotifichePush.getOriginalFilename());
            try {
                Map<String, Object> returnObj = caricamentoService.importNotifichePushExcel(userProfile, uplFileNotifichePush);
                if (returnObj != null && returnObj.containsKey(Constants.ERROR_MESSAGE)) {
                    modelAndView.addObject(Constants.ERROR_MESSAGE, returnObj.get(Constants.ERROR_MESSAGE));
               } else {
                    List<NotificaPushExcelDto> notificheListExcel = (List<NotificaPushExcelDto>) returnObj.get(Constants.ESTRAZIONE_DATI_EXCEL);                    
                    if (notificheListExcel.isEmpty()){
                        modelAndView.addObject(Constants.ERROR_MESSAGE, "Errore nel caricamento della lista utenti notificabili.");
                    } else {
                        List<NotificaPushExcelDto> utentiNotificabili = new ArrayList<>();
                        if (notificheListExcel.get(0).getProfilazione().equalsIgnoreCase("NO") && notificheListExcel.get(0).getMarketing().equalsIgnoreCase("NO"))
                        {
                            utentiNotificabili.addAll(notificheListExcel); // tutti gli utenti dell'excel sono notificabili dalla prima riga (non rischiesto controllo privacy)
                        }else{
                            utentiNotificabili = caricamentoService.estrazioneUtentiValidi(notificheListExcel);
                        }
                        if (utentiNotificabili.isEmpty()){
                            modelAndView.addObject(Constants.ERROR_MESSAGE, "Non ci sono utenti notificabili!");
                        } else {
                            String finalResult = caricamentoService.generazioneNotifichePush(utentiNotificabili);
                            if (finalResult.contains("OK")){
                                modelAndView.addObject(Constants.SUCCESS_MESSAGE, "Caricamento Notifiche Push completato con successo.");
                            } else {
                                modelAndView.addObject(Constants.ERROR_MESSAGE, finalResult);
                            }
                        } 
                    }
                    
               }
            } catch (Exception ex) {
                logger.error(ex);
                modelAndView.addObject(Constants.ERROR_MESSAGE, "Errore nel Caricamento Notifiche Push.");

            }
        } else {
            modelAndView.addObject(Constants.ERROR_MESSAGE, "Sono ammessi solo file Excel (.xls, .xlsx)");
        }
        return modelAndView;
    }
}

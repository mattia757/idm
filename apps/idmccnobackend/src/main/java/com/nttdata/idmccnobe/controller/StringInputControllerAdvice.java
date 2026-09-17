package com.nttdata.idmccnobe.controller;

import java.beans.PropertyEditorSupport;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import com.nttdata.idmccnobe.dto.SaveCashbackRequest;

@ControllerAdvice
public class StringInputControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
    	
    	//cachback
        dataBinder.registerCustomEditor(SaveCashbackRequest.class, new PropertyEditorSupport() {
        	
        	@Override
        	public SaveCashbackRequest getValue() {
        		
        		SaveCashbackRequest dto = (SaveCashbackRequest) super.getValue();
        		if(ObjectUtils.isEmpty(dto.getAccumulationFlyerPdfImageUrl())) {
        			dto.setAccumulationFlyerPdfImageUrl(null);
        		}
        		if(ObjectUtils.isEmpty(dto.getAccumulationFlyerPdfLink())) {
        			dto.setAccumulationFlyerPdfLink(null);
        		}
        		if(ObjectUtils.isEmpty(dto.getFruitionFlyerPdfImageUrl())) {
        			dto.setFruitionFlyerPdfImageUrl(null);
        		}
        		if(ObjectUtils.isEmpty(dto.getFruitionFlyerPdfLink())) {
        			dto.setFruitionFlyerPdfLink(null);
        		}
        		if(ObjectUtils.isEmpty(dto.getId())) {
        			dto.setId(null);
        		}
        		return dto;
        	} 	
        });
    }
}


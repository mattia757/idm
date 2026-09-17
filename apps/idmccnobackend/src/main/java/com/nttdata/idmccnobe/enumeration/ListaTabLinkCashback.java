/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nttdata.idmccnobe.enumeration;

/**
 *
 * @author BernardisMa
 */
public enum ListaTabLinkCashback {
    
    CASHBACK_VIEW_DETTAGLIO(0),    
    CASHBACK_TAB_PDV(1);
    
    private final int tabCashback;

    ListaTabLinkCashback(int tabCashback) {
        this.tabCashback = tabCashback;
    }

    public int tabCashback() {
        return tabCashback;
    }
    
}

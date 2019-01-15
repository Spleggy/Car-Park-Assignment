/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevin.carpark.swingcient;

import org.kevin.carpark.model.TicketMachineDAO;

/**
 *
 * @author cgallen
 */
public class RestUpdater {
    TicketMachineDAO ticketMachineDAO = null;
    String updatechron ="";
    String baseUrl = "";
    
    public TicketMachineDAO getTicketMachineDAO() {
        return ticketMachineDAO;
    }

    public void setTicketMachineDAO(TicketMachineDAO ticketMachineDAO) {
        this.ticketMachineDAO = ticketMachineDAO;
    }

    public String getUpdatechron() {
        return updatechron;
    }

    public void setUpdatechron(String updatechron) {
        this.updatechron = updatechron;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    
    public boolean immediateUpdate(){
        return false;
    }
            
}

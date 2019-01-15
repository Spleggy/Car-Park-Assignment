/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevin.carpark.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cgallen
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketMachineList {

    // only used by persistance layer
    private Integer lastTicketMachineId = null;

    @XmlElementWrapper(name = "ticketMachine")
    @XmlElement(name = "ticketMachine")
    private List<TicketMachine> ticketMachines = new ArrayList<TicketMachine>();

    public List<TicketMachine> getTicketMachines() {
        return ticketMachines;
    }

    public void setTicketMachines(List<TicketMachine> ticketMachines) {this.ticketMachines = ticketMachines; }

    public Integer getLastTicketMachineId() { return lastTicketMachineId;}

    public void setLastTicketMachineId(Integer lastTicketMachineId) {
        this.lastTicketMachineId = lastTicketMachineId;
    }

    @Override
    public String toString() {
        return "TicketMachineList{" + "lastTicketMachineId=" + lastTicketMachineId + ", ticketMachines=" + ticketMachines + '}';
    }



}

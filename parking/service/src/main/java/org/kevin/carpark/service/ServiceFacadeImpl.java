/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevin.carpark.service;

import java.util.List;
import org.kevin.carpark.model.TicketMachine;
import org.kevin.carpark.model.TicketMachineDAO;
import org.kevin.carpark.model.ServiceFacade;

/**
 *
 * @author cgallen
 */
public class ServiceFacadeImpl implements ServiceFacade {

    TicketMachineDAO ticketMachineDAO = null;

    public void setTicketMachineDAO(TicketMachineDAO ticketMachineDAO) {
        this.ticketMachineDAO = ticketMachineDAO;
    }

    @Override
    public void deleteAllTicketMachines() {
        ticketMachineDAO.deleteAllTicketMachines();
    }

    @Override
    public TicketMachine createTicketMachine(TicketMachine ticketMachine) {
        return ticketMachineDAO.createTicketMachine(ticketMachine);
    }

    @Override
    public boolean deleteTicketMachine(Integer id) {
        return ticketMachineDAO.deleteTicketMachine(id);
    }

    @Override
    public TicketMachine retrieveTicketMachine(Integer id) {
        return ticketMachineDAO.retrieveTicketMachine(id);
    }

    @Override
    public List<TicketMachine> retrieveAllTicketMachines() {
        return ticketMachineDAO.retrieveAllTicketMachines();
    }

    @Override
    public List<TicketMachine> retrieveMatchingTicketMachines(TicketMachine ticketMachineTemplate) {
        return ticketMachineDAO.retrieveMatchingTicketMachines(ticketMachineTemplate);
    }

    @Override
    public TicketMachine updateTicketMachine(TicketMachine ticketMachine) {
        return ticketMachineDAO.updateTicketMachine(ticketMachine);
    }
    
}

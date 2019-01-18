package org.kevin.carpark.model;

import java.util.List;

public interface ServiceFacade extends TicketMachineDAO {

    public TicketMachine createTicketMachine(TicketMachine ticketMachine);

    public boolean deleteTicketMachine(Integer id);

    public void deleteAllTicketMachines();

    public TicketMachine retrieveTicketMachine(Integer id);

    public List<TicketMachine> retrieveAllTicketMachines();

    public List<TicketMachine> retrieveMatchingTicketMachines(TicketMachine ticketMachineTemplate);

    public TicketMachine updateTicketMachine(TicketMachine ticketMachine);


}

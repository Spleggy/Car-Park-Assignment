package org.kevin.carpark.model;

public interface RestInterface {

    public ReplyMessage retrieveMatchingTicketMachine(TicketMachine ticketMachineTemplate);
    
    public ReplyMessage retrieveTicketMachine(Integer id);
    
}

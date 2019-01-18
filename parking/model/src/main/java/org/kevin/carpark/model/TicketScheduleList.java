package org.kevin.carpark.model;

import sun.security.krb5.internal.Ticket;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketScheduleList
{
    @XmlElementWrapper(name = "scheduleList")
    @XmlElement(name = "schedule")
    // only used by persistance layer


    private  List<TicketSchedule> ticketSchedules = new ArrayList<>();

    public List<TicketSchedule> getTicketSchedules() {
        return this.ticketSchedules;
    }

    public void setTicketSchedules(List<TicketSchedule> ticketSchedule) {ticketSchedules = ticketSchedule; }


    @Override
    public String toString() {
        return "TicketScheduleList{" + "ticketSchedule=" + ticketSchedules + '}';
    }



}

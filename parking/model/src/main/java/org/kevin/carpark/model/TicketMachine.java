package org.kevin.carpark.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Simple example entity with 3 fields
 *
 * @author cgallen
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketMachine {

    private Integer id;

    private String location = null;

//    field b is a list of the scheduleList for that machine
    private TicketScheduleList scheduleList = new TicketScheduleList();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }



    public void setSchedule(TicketScheduleList scheduleList) {
        this.scheduleList = scheduleList;
    }

    public TicketScheduleList getSchedule() {
        return scheduleList;
    }

    @Override
    public String toString() {
        return "Entity{" + "id=" + id
                + ", location=" + location
                + ", schedule=" + scheduleList + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TicketMachine other = (TicketMachine) obj;
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.scheduleList, other.scheduleList)) {
            return false;
        }

        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public void addSchedule(TicketSchedule ticketSchedule)
    {

        for (TicketSchedule schedule : scheduleList.getTicketSchedules()) {
            if(schedule.getStartTime().equals(ticketSchedule.getStartTime())){
                scheduleList.getTicketSchedules().remove(schedule);
            }
        }
        scheduleList.getTicketSchedules().add(ticketSchedule);

    }

}

package org.kevin.carpark.model;

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

    private List<TicketSchedule> ticketScheduleList = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getField_A() {
        return location;
    }

    public void setField_A(String location) {
        this.location = location;
    }

    public List<TicketSchedule> getField_B() {
        return ticketScheduleList;
    }

    public void setField_B(List<TicketSchedule> ticketScheduleList) {
        this.ticketScheduleList = ticketScheduleList;
    }

    @Override
    public String toString() {
        return "Entity{" + "id=" + id
                + ", location=" + location
                + ", ticketScheduleList=" + ticketScheduleList + '}';
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
        if (!Objects.equals(this.ticketScheduleList, other.ticketScheduleList)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}

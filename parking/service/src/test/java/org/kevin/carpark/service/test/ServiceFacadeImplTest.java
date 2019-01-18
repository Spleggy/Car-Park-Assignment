/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevin.carpark.service.test;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.kevin.carpark.model.TicketMachine;
import org.kevin.carpark.model.ServiceFacade;
import org.kevin.carpark.model.ServiceFactory;
import org.kevin.carpark.model.TicketSchedule;
import org.kevin.carpark.service.ServiceFactoryImpl;

/**
 *
 * @author cgallen
 */
public class ServiceFacadeImplTest {

    public static final String TEST_DATA_FILE = "./target/testfile.xml";

    // Only some basic tests as most tests already done in TicketMachineDAO tests
    @Test
    public void simpleServiceFacadeTest() {

        // use service factory to get access to service
        ServiceFactory serviceFactory = new ServiceFactoryImpl(TEST_DATA_FILE);
        assertNotNull(serviceFactory);

        ServiceFacade serviceFacade = serviceFactory.getServiceFacade();
        assertNotNull(serviceFacade);
        
        // clear file before anything else
        serviceFacade.deleteAllTicketMachines();

        TicketMachine ticketMachine = new TicketMachine();
        ticketMachine.setId(1);
        ticketMachine.setLocation("Test");

        TicketSchedule schedule1 = new TicketSchedule();
        schedule1.setScheduleId(1);
        schedule1.setStartTime("13:00");
        schedule1.setPricePerHour(5.00);

        ticketMachine.addSchedule(schedule1);

        TicketMachine e = serviceFacade.createTicketMachine(ticketMachine);

        List<TicketMachine> retrievedEntities = serviceFacade.retrieveMatchingTicketMachines(e);




        assertEquals(1, retrievedEntities.size());
    }
}

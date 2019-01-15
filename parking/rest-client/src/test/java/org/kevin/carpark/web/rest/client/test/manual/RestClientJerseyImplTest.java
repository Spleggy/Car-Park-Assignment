/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevin.carpark.web.rest.client.test.manual;

import java.util.List;
import javax.ws.rs.core.MediaType;
import org.junit.Test;
import static org.junit.Assert.*;
import org.kevin.carpark.model.TicketMachine;
import org.kevin.carpark.model.ReplyMessage;
import org.kevin.carpark.web.rest.client.RestClientJerseyImpl;

/**
 *
 * @author cgallen
 */
public class RestClientJerseyImplTest {

    String baseUrl = "http://localhost:8680/";

    MediaType mediaType = MediaType.APPLICATION_XML_TYPE;

    @Test
    public void restClientRetreiveTest() {

        RestClientJerseyImpl restClient = new RestClientJerseyImpl(baseUrl, mediaType);

        // try to retreive an unknown entity
        ReplyMessage replyMessage = restClient.retrieveTicketMachine(Integer.SIZE);
        assertNotNull(replyMessage);
        assertTrue(replyMessage.getTicketMachineList().getTicketMachines().isEmpty());

        // try to retreive entity with id 1
        ReplyMessage replyMessage2 = restClient.retrieveTicketMachine(1);
        assertNotNull(replyMessage2);
        assertEquals(1, replyMessage2.getTicketMachineList().getTicketMachines().size());

        TicketMachine entity = replyMessage2.getTicketMachineList().getTicketMachines().get(0);
        System.out.println("Received Entity: " + entity);

    }

    @Test
    public void restClientRetreiveTemplateTest() {

        RestClientJerseyImpl restClient = new RestClientJerseyImpl(baseUrl, mediaType);

        TicketMachine ticketMachineTemplate = new TicketMachine();
        ticketMachineTemplate.setField_A("abcd");

        // try to retreive an unknown entity
        ReplyMessage replyMessage = restClient.retrieveMatchingTicketMachine(ticketMachineTemplate);
        assertNotNull(replyMessage);

        List<TicketMachine> ticketMachineList =  replyMessage.getTicketMachineList().getTicketMachines();
        System.out.println("Received "
                + ticketMachineList.size()
                + " Entities");
        
       for(TicketMachine e: ticketMachineList){
           System.out.println("   "+ e);
       }
        
    }
}

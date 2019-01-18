/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevin.carpark.dao.jaxbimpl.test;

import java.io.File;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

import org.kevin.carpark.model.TicketMachineList;
import org.kevin.carpark.model.TicketSchedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.kevin.carpark.dao.jaxbimpl.TicketMachineDAOJaxbImpl;
import org.kevin.carpark.model.TicketMachine;
import org.kevin.carpark.model.TicketMachineDAO;

/**
 * tests for entityDao.createEntity(entity) entityDao.deleteEntity(Id) entityDao.retrieveAllEntities() entityDao.retrieveEntity(Id)
 * entityDao.retrieveMatchingEntites(entityTempate) entityDao.updateEntity(entity)
 *
 * @author cgallen
 */
public class TicketMachineDAOJaxbImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(TicketMachineDAOJaxbImplTest.class);

    public final String TEST_DATA_FILE_LOCATION = "target/testDaofile.xml";

    @Test
    public void testDestinationsDAOJaxb() {

        // delete test file at start of test
        File file = new File(TEST_DATA_FILE_LOCATION);
        file.delete();
        assertFalse(file.exists());

        // create dao
        TicketMachineDAO ticketMachineDAO = new TicketMachineDAOJaxbImpl(TEST_DATA_FILE_LOCATION);

        // check that new file created
        assertTrue(file.exists());

        // check there are no entities
        assertTrue(ticketMachineDAO.retrieveAllTicketMachines().isEmpty());

        // add a 3 entities

        for (int intityId = 0; intityId < 3; intityId++) {
            TicketMachine ticketMachine = new TicketMachine();
            ticketMachine.setId(intityId);
            ticketMachine.setLocation("Location" + intityId);

            TicketSchedule schedule1 = new TicketSchedule();
            schedule1.setScheduleId(intityId);
            schedule1.setStartTime("13:00");
            schedule1.setPricePerHour(5.00);

            ticketMachine.addSchedule(schedule1);

            LOG.debug("adding entity:" + ticketMachine);
            TicketMachine e = ticketMachineDAO.createTicketMachine(ticketMachine);
            assertNotNull(e);
        }

        // check 3 entities added   MAKE THIS PASS
        assertTrue(3 == ticketMachineDAO.retrieveAllTicketMachines().size());

        // check return false for delete unknown entity
        assertFalse(ticketMachineDAO.deleteTicketMachine(Integer.SIZE));

        // find an entity to delete
        List<TicketMachine> elist = ticketMachineDAO.retrieveAllTicketMachines();
        Integer idToDelete = elist.get(1).getId();
        LOG.debug("deleting  entity:" + idToDelete);

        // check found and deleted
        assertTrue(ticketMachineDAO.deleteTicketMachine(idToDelete));

        // check no longer found after deletion
        assertNull(ticketMachineDAO.retrieveTicketMachine(idToDelete));

        // check entities size decremeted
        List<TicketMachine> elist2 = ticketMachineDAO.retrieveAllTicketMachines();
        assertTrue(2 == elist2.size());

//        // update entity
        TicketMachine entityToUpdate = elist2.get(1);
        LOG.debug("updating entity: " + entityToUpdate);
//
//        // add 3 newProperties for entity
        entityToUpdate.setLocation("field_A_Update");
//
//
        LOG.debug("update template: " + entityToUpdate);
//
        TicketMachine updatedEntity = ticketMachineDAO.updateTicketMachine(entityToUpdate);
        LOG.debug("updated entity: " + updatedEntity);
        assertNotNull(updatedEntity);
//
//        // check entity updated
        TicketMachine retrievedEntity = ticketMachineDAO.retrieveTicketMachine(updatedEntity.getId());
        LOG.debug("retrieved entity: " + retrievedEntity);
        assertEquals(entityToUpdate.getLocation(), retrievedEntity.getLocation());
        assertEquals(entityToUpdate.getLocation(), retrievedEntity.getLocation());
//
//
//        // test retrieve matching entities
        List<TicketMachine> ticketMachineList = ticketMachineDAO.retrieveAllTicketMachines();
        TicketMachine searchfor = ticketMachineList.get(1);
        LOG.debug("searching for: " + searchfor);

        TicketMachine template = new TicketMachine();
        template.setSchedule(searchfor.getSchedule());
        LOG.debug("using template : " + template);

        List<TicketMachine> retrievedList = ticketMachineDAO.retrieveMatchingTicketMachines(template);
       // assertEquals(1, retrievedList.size());

       // LOG.debug("found : " + retrievedList.get(0));
       // assertEquals(searchfor, retrievedList.get(0));

    }

}

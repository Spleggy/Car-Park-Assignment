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
        int ENTITY_NUMBER = 4;
        for (int intityId = 0; intityId < ENTITY_NUMBER; intityId++) {
            TicketMachine ticketMachine = new TicketMachine();
            ticketMachine.setField_A("field_A_" + intityId);
            ticketMachine.setField_B("field_B_" + intityId);
            ticketMachine.setField_C("field_C_" + intityId);

            LOG.debug("adding entity:" + ticketMachine);
            TicketMachine e = ticketMachineDAO.createTicketMachine(ticketMachine);
            assertNotNull(e);
        }

        // check 3 entities added
        assertTrue(ENTITY_NUMBER == ticketMachineDAO.retrieveAllTicketMachines().size());

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
        assertTrue(ENTITY_NUMBER - 1 == elist2.size());

        // update entity
        TicketMachine entityToUpdate = elist2.get(1);
        LOG.debug("updating entity: " + entityToUpdate);

        // add 3 newProperties for entity
        entityToUpdate.setField_A("field_A_Update");
        entityToUpdate.setField_B("field_B_Update");
        entityToUpdate.setField_C(null); // do not update field C
        LOG.debug("update template: " + entityToUpdate);

        TicketMachine updatedEntity = ticketMachineDAO.updateTicketMachine(entityToUpdate);
        LOG.debug("updated entity: " + updatedEntity);
        assertNotNull(updatedEntity);

        // check entity updated
        TicketMachine retrievedEntity = ticketMachineDAO.retrieveTicketMachine(updatedEntity.getId());
        LOG.debug("retrieved entity: " + retrievedEntity);
        assertEquals(entityToUpdate.getField_A(), retrievedEntity.getField_A());
        assertEquals(entityToUpdate.getField_A(), retrievedEntity.getField_A());
        assertNotEquals(entityToUpdate.getField_C(), retrievedEntity.getField_C());

        // test retrieve matching entities
        List<TicketMachine> ticketMachineList = ticketMachineDAO.retrieveAllTicketMachines();
        TicketMachine searchfor = ticketMachineList.get(2);
        LOG.debug("searching for: " + searchfor);

        TicketMachine template = new TicketMachine();
        template.setField_B(searchfor.getField_B());
        LOG.debug("using template : " + template);

        List<TicketMachine> retrievedList = ticketMachineDAO.retrieveMatchingTicketMachines(template);
        assertEquals(1, retrievedList.size());

        LOG.debug("found : " + retrievedList.get(0));
        assertEquals(searchfor, retrievedList.get(0));

    }

}

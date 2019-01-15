/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevin.carpark.swingcient;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.kevin.carpark.model.TicketMachine;
import org.kevin.carpark.model.TicketMachineDAO;

/**
 *
 * @author cgallen
 */
public class ModelControllerImpl implements ModelController {

    private static final Logger LOG = LoggerFactory.getLogger(ModelControllerImpl.class);

    private TicketMachineDAO ticketMachineDAO = null;

    private EntityListTableModel ticketMachineListTableModel = new EntityListTableModel();

    private EntityClientLoader entityClientLoader = null;

    public ModelControllerImpl(TicketMachineDAO ticketMachineDAO, EntityClientLoader entityClientLoader) {
        this.entityClientLoader = entityClientLoader;
        this.ticketMachineDAO = ticketMachineDAO;
        List<TicketMachine> entities = ticketMachineDAO.retrieveAllTicketMachines();
        ticketMachineListTableModel.setEntities(entities);
    }

    @Override
    public EntityListTableModel getEntityListTableModel() {
        return ticketMachineListTableModel;
    }

    @Override
    public void clearSearch() {
        LOG.debug("clear search selected");

        List<TicketMachine> ticketMachines = ticketMachineDAO.retrieveAllTicketMachines();
        ticketMachineListTableModel.setEntities(ticketMachines);
    }

    @Override
    public void findMatchingSearch(TicketMachine ticketMachineTemplate) {
        LOG.debug("find matching with templateEntity=" + ticketMachineTemplate);

        List<TicketMachine> ticketMachines = ticketMachineDAO.retrieveMatchingTicketMachines(ticketMachineTemplate);
        LOG.debug("found " + ticketMachines.size() + " matching with templateEntity=" + ticketMachineTemplate);
        ticketMachineListTableModel.setEntities(ticketMachines);

    }

    @Override
    public boolean forceReloadData() {
        LOG.debug("forceReloadData called");
        boolean success = false;
        if (entityClientLoader != null) {
            success = entityClientLoader.restClientRetrieveAll();
        }
        LOG.debug("forceReloadData result=" + success);
        return success;
    }

}

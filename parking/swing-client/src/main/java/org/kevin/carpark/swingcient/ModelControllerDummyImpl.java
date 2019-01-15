/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevin.carpark.swingcient;


import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.kevin.carpark.model.TicketMachine;

/**
 *
 * @author cgallen
 */
public class ModelControllerDummyImpl implements ModelController {

    private static final Logger LOG = LoggerFactory.getLogger(ModelControllerDummyImpl.class);

    private EntityListTableModel ticketMachineListTableModel = null;

    private void initialiseTableModel() {

        ticketMachineListTableModel = new EntityListTableModel();
        List<TicketMachine> elist = new ArrayList<TicketMachine>();

        int ENTITY_NUMBER = 40;
        for (int intityId = 0; intityId < ENTITY_NUMBER; intityId++) {
            TicketMachine entity = new TicketMachine();
            entity.setId(intityId);
            entity.setField_A("field_A_" + intityId);
            entity.setField_B("field_B_" + intityId);
            entity.setField_C("field_C_" + intityId);
            elist.add(entity);
        }
        ticketMachineListTableModel.setEntities(elist);
    }

    @Override
    public EntityListTableModel getEntityListTableModel() {
        if (ticketMachineListTableModel == null) {
            synchronized (ModelControllerDummyImpl.class) {
                if (ticketMachineListTableModel == null) {
                    initialiseTableModel();
                }
            }
        }
        return ticketMachineListTableModel;

    }
    


    @Override
    public void clearSearch() {
        LOG.debug("clear search selected");
    }

    @Override
    public void findMatchingSearch(TicketMachine templateEntity) {
        LOG.debug("find matching with templateEntity="+templateEntity);
    }

    @Override
    public boolean forceReloadData() {
        return false;
    }



}

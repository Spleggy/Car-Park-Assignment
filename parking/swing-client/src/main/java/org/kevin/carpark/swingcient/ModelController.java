/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevin.carpark.swingcient;

import org.kevin.carpark.model.TicketMachine;

/**
 *
 * @author cgallen
 */
public interface ModelController {

    void clearSearch();

    void findMatchingSearch(TicketMachine templateEntity);

    EntityListTableModel getEntityListTableModel();
    
    boolean forceReloadData();
    
}

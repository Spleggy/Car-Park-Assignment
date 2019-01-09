/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevin.carpark.service;

import org.kevin.carpark.dao.jaxbimpl.EntityDAOJaxbImpl;
import org.kevin.carpark.model.EntityDAO;
import org.kevin.carpark.model.ServiceFacade;
import org.kevin.carpark.model.ServiceFactory;

/**
 *
 * @author cgallen
 */
public class ServiceFactoryImpl implements ServiceFactory {

    ServiceFacade serviceFacade = null;

    String dataFileUri = null;

    public ServiceFactoryImpl(String dataFileUri) {
        if (dataFileUri == null) {
            throw new IllegalArgumentException("dataFileUri must not be null");
        }
        
        EntityDAO entityDao = new EntityDAOJaxbImpl(dataFileUri);
        ServiceFacadeImpl serviceFacadeImpl = new ServiceFacadeImpl();
        serviceFacadeImpl.setEntityDAO(entityDao);
        serviceFacade = serviceFacadeImpl;
        
    }

    @Override
    public ServiceFacade getServiceFacade() {
        return serviceFacade;
    }

}

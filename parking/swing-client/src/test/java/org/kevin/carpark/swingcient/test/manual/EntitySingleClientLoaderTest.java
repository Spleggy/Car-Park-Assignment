/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevin.carpark.swingcient.test.manual;

import java.io.File;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.kevin.carpark.dao.jaxbimpl.EntityDAOJaxbImpl;
import org.kevin.carpark.model.Entity;
import org.kevin.carpark.model.EntityDAO;
import org.kevin.carpark.swingcient.EntityClientLoader;

/**
 *
 * @author cgallen
 */
public class EntitySingleClientLoaderTest {

    private static final Logger LOG = LoggerFactory.getLogger(EntitySingleClientLoaderTest.class);

    public static final String TEST_DATA_FILE_LOCATION = "target/testDaofile.xml";

    public static final String TEST_BASE_URL = "http://localhost:8680/";

    @Test
    public void testSingleClientLoader() {

        // delete test file at start of test
        File file = new File(TEST_DATA_FILE_LOCATION);
        file.delete();
        assertFalse(file.exists());

        // create dao
        EntityDAO entityDAO = new EntityDAOJaxbImpl(TEST_DATA_FILE_LOCATION);
        assertTrue(file.exists());

        List<Entity> list = entityDAO.retrieveAllEntities();
        assertTrue(list.isEmpty());

        String baseUrl = "http://localhost:8680/";

        EntityClientLoader entityClientLoader = new EntityClientLoader(entityDAO, baseUrl);

        // try to load from service
        boolean success = entityClientLoader.restClientRetrieveAll();
        assertTrue(success);

        list = entityDAO.retrieveAllEntities();
        LOG.debug("retrieved enties = " + list.size());

    }

}

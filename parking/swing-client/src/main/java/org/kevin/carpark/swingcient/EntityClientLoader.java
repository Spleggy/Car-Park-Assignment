/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevin.carpark.swingcient;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.kevin.carpark.model.TicketMachine;
import org.kevin.carpark.model.TicketMachineDAO;
import org.kevin.carpark.model.ReplyMessage;
import org.kevin.carpark.web.rest.client.RestClientJerseyImpl;

/**
 *
 * @author cgallen
 */
public class EntityClientLoader {

    private static final Logger LOG = LoggerFactory.getLogger(EntityClientLoader.class);

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private ScheduledFuture<?> schedulerHandle = null;

    private TicketMachineDAO ticketMachineDAO = null;

    private String baseUrl = "http://localhost:8680/";

    private MediaType mediaType = MediaType.APPLICATION_XML_TYPE;

    private RestClientJerseyImpl restClient = null;

    private AtomicInteger totalClientRetrieveSuccessful = new AtomicInteger();
    private AtomicInteger totalClientRetrieveTries = new AtomicInteger();

    public int getTotalClientRetrieveTries() {
        return totalClientRetrieveTries.get();
    }

    public int getTotalClientRetrieveSuccessful() {
        return totalClientRetrieveSuccessful.get();
    }

    public EntityClientLoader(TicketMachineDAO ticketMachineDAO, String baseUrl) {
        this.ticketMachineDAO = ticketMachineDAO;
        this.baseUrl = baseUrl;
        restClient = new RestClientJerseyImpl(baseUrl, mediaType);
    }

    // synchronized so that can only run one call at a time
    public synchronized boolean restClientRetrieveAll() {

        TicketMachine ticketMachineTemplate = new TicketMachine();
        // you could add a filter value here but leaving values null retrieves all values
        // e.g entityTempate.setField_A("xxxx");

        // try to retreive a list of entities
        try {
            ReplyMessage replyMessage = restClient.retrieveMatchingTicketMachine(ticketMachineTemplate);
            if (replyMessage.getCode() == 200) {

                List<TicketMachine> ticketMachineList = replyMessage.getTicketMachineList().getTicketMachines();

                if (LOG.isDebugEnabled()) {
                    StringBuffer sb = new StringBuffer("Received " + ticketMachineList.size() + " Entities");
                    for (TicketMachine e : ticketMachineList) {
                        sb.append("\n   " + e);
                    }
                    LOG.debug(sb.toString());
                }

                // note that each entity will have a new id which is unique to local dao instance
                ticketMachineDAO.deleteAllTicketMachines();
                for (TicketMachine newTicketMachine : ticketMachineList) {
                    ticketMachineDAO.createTicketMachine(newTicketMachine);
                }

                return true;
            } else {
                LOG.error("problem retrieving entities from " + baseUrl
                        + " " + replyMessage.toString());
            }

        } catch (Exception e) {
            LOG.error("problem retrieving entities from " + baseUrl, e);
        }
        // some problem retreiving data
        return false;
    }

    public synchronized void scheduleLoadData(Integer scheduleIntervalSeconds, Integer retryAttempts, Integer retryIntervalSeconds) {
        if (scheduleIntervalSeconds == null) {
            throw new IllegalArgumentException("scheduleIntervalSeconds cannot be null");
        }
        if (retryAttempts == null) {
            throw new IllegalArgumentException("retryAttempts cannot be null");
        }
        if (retryIntervalSeconds == null) {
            throw new IllegalArgumentException("retryIntervalSeconds cannot be null");
        }
        LOG.info("load data starting scheduleIntervalSeconds=" + scheduleIntervalSeconds
                + " retryAttempts=" + retryAttempts
                + " retryIntervalSecond=" + retryIntervalSeconds);
        if (schedulerHandle == null) {

            final Runnable loadData = new Runnable() {
                public void run() {
                    boolean keepTrying = true;
                    int gettries = 0;

                    while (keepTrying && gettries < retryAttempts) {
                        gettries++;
                        LOG.debug("retrieving data from service attempt:" + gettries);

                        totalClientRetrieveTries.incrementAndGet();
                        try {
                            // try to retrieve data                 
                            keepTrying = !restClientRetrieveAll();
                            if (!keepTrying) {
                                totalClientRetrieveSuccessful.getAndIncrement();
                                LOG.debug("retrieving data successful. Total retrieve success=" + totalClientRetrieveSuccessful.get());
                            } else {
                                LOG.debug("retrieving data unsuccessful (Current retry=" + gettries
                                        + ") Total try attempts " + totalClientRetrieveTries.get());
                            }
                            Thread.sleep(retryIntervalSeconds * 1000);
                        } catch (InterruptedException ex) {
                            keepTrying = false;
                            LOG.debug("retrieving data interrupted");
                        }
                    }
                    LOG.debug("retrieving data thread finished (Current retry=" + gettries + ")");
                }

            };
            // scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit)
            schedulerHandle = scheduler.scheduleAtFixedRate(loadData, 0, scheduleIntervalSeconds, TimeUnit.SECONDS);
            LOG.debug("load data scheduled");
        }

    }

    public synchronized void cancelLoadDataSchedule() {
        LOG.debug("cancelling scheduled load");
        if (schedulerHandle != null) {
            schedulerHandle.cancel(true);
            schedulerHandle = null;
        }

    }
}

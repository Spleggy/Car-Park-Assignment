package org.kevin.carpark.web.rest;

import org.glassfish.jersey.server.ResourceConfig;

public class RestApp extends ResourceConfig {

    public RestApp() {
        packages("org.kevin.carpark.web.rest");
    }
}

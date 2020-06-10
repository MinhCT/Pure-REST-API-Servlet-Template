package com.minhct.servlet.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ResourceManager {

    private static final Logger LOGGER = LogManager.getLogger(ResourceManager.class);
    private static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(4);

    public static void init() {
        LOGGER.info("Resources are being located....");

        // Register separate threads to handle logic that required longer time to process
        executorService.scheduleAtFixedRate(() -> LOGGER.info("Hello World!"),
                5, 10, TimeUnit.MINUTES);
    }
}

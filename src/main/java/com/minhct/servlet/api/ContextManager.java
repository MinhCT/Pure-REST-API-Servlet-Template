package com.minhct.servlet.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextManager implements ServletContextListener {

    private static final Logger LOGGER = LogManager.getLogger(ContextManager.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("Context initializing...");
        ResourceManager.init();

        // - Open many connections here (Redis, MySQL, etc.)
        // - Initialize many global variables
        // ...
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.info("Context is being destroyed...");

        // Safely close all connections / handle post process before shutdown
    }
}
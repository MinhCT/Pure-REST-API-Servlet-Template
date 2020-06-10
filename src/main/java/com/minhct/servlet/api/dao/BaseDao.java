package com.minhct.servlet.api.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class BaseDao {

    private static final Logger LOGGER = LogManager.getLogger(BaseDao.class);

    protected Connection connection;

    protected BaseDao() {
        try {
            // Get connection from pool
        } catch (SQLException e) {
            LOGGER.error("Error creating SQL connection from pool", e);
        }
    }
}

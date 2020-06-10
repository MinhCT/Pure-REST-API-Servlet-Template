package com.minhct.servlet.api.dao;

import com.minhct.servlet.api.request.ExampleRequest;
import com.minhct.servlet.api.response.ExampleResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PageViewDao extends BaseDao {

    private static final Logger LOGGER = LogManager.getLogger(PageViewDao.class);

    public List<ExampleResponse> getSomeData(ExampleRequest exampleRequest) {
        List<ExampleResponse> exampleResponseList = new ArrayList<>();

        String query = "SELECT id, name, SUM(view_count) as view_count " +
                "FROM example_analysis " +
                "GROUP BY id, name";

        if (exampleRequest.getFromDate() != null && exampleRequest.getToDate() == null) {
            query += " AND date >= " + exampleRequest.getFromDate();
        } else if (exampleRequest.getFromDate() == null && exampleRequest.getToDate() != null) {
            query += " AND date <= " + exampleRequest.getToDate();
        } else {
            query += String.format(" AND date BETWEEN %d AND %d",
                    exampleRequest.getFromDate(), exampleRequest.getToDate());
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                // Add data to response list
            }
        } catch (SQLException e) {
            LOGGER.error("Error executing query news ids statement", e);
        } finally {
            // Return connection to pool
        }

        return exampleResponseList;
    }
}

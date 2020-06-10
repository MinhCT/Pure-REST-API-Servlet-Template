package com.minhct.servlet.api.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.minhct.servlet.api.dao.PageViewDao;
import com.minhct.servlet.api.exception.BadRequestException;
import com.minhct.servlet.api.request.ExampleRequest;
import com.minhct.servlet.api.response.ExampleResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ExampleService {

    private static final Logger LOGGER = LogManager.getLogger(ExampleService.class);

    public List<ExampleResponse> getData(String jsonRequest) throws BadRequestException {
        ExampleRequest exampleRequest = processRequest(jsonRequest);
        PageViewDao pageViewDao = new PageViewDao();
        return pageViewDao.getSomeData(exampleRequest);
    }

    private ExampleRequest processRequest(String jsonRequest) throws BadRequestException {
        ExampleRequest exampleRequest;

        if (StringUtils.isEmpty(jsonRequest)) {
            throw new BadRequestException("JSON request body can not be empty!");
        }

        try {
            exampleRequest = new Gson().fromJson(jsonRequest, ExampleRequest.class);

            if (exampleRequest.getId() == null) {
                throw new BadRequestException("'id' must be existed in the request!");
            }
        } catch (JsonSyntaxException e) {
            LOGGER.error(e.getMessage(), e);
            throw new BadRequestException("Failed to parse from string. Request body must be json");
        }

        return exampleRequest;
    }
}

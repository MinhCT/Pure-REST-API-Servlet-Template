package com.minhct.servlet.api.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.minhct.servlet.api.exception.BadRequestException;
import com.minhct.servlet.api.response.ExampleResponse;
import com.minhct.servlet.api.service.ExampleService;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/api/get-some-data")
public class ExampleServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(ExampleServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String respMessage = "OK";
        boolean withParentNode = true;
        resp.setStatus(HttpServletResponse.SC_OK);

        try {
            String json = parseRequestBody(req);
            LOGGER.info(String.format("Request body content: %s", json));

            ExampleService service = new ExampleService();
            List<ExampleResponse> newsViewResponseList = service.getData(json);

            respMessage = new Gson().toJson(newsViewResponseList);
            withParentNode = false;
        } catch (Exception e) {
            if (e instanceof IOException) {
                LOGGER.error("Error parsing request body to json String", e);
            } else if (e instanceof BadRequestException) {
                LOGGER.error(e);
                respMessage = e.getMessage();
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } else {
                LOGGER.error(e.getMessage(), e);
            }
        }

        resp.setContentType("application/json");
        resp.getWriter().print(createResponse(respMessage, withParentNode));
    }

    private String createResponse(String message, boolean withParentNode) {
        if (!withParentNode) {
            return message;
        }

        JsonObject response = new JsonObject();
        response.addProperty("message", message);
        return response.toString();
    }

    private String parseRequestBody(HttpServletRequest request) throws IOException {
        return IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
    }
}

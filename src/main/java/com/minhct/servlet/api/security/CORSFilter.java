package com.minhct.servlet.api.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebFilter(filterName = "CORSFilter", urlPatterns = {"/*"})
public class CORSFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(CORSFilter.class);
    private final Set<String> allowedOrigins = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("CORSFilter initialized!");

        // Add allowed origins here
        allowedOrigins.add("abc.example.com");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        doBeforeProcessing(request, response);

        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            LOGGER.error(t);
        }
    }

    private void doBeforeProcessing(ServletRequest request, ServletResponse response) {
        if (response instanceof HttpServletResponse) {
            HttpServletResponse alteredResponse = ((HttpServletResponse) response);

            if (request instanceof HttpServletRequest) {
                HttpServletRequest alteredRequest = (HttpServletRequest) request;
                String origin = alteredRequest.getHeader("Origin");
                if (isValidDomain(origin)) {
                    alteredResponse.setHeader("Access-Control-Allow-Origin", origin);
                } else {
                    alteredResponse.setHeader("Access-Control-Allow-Origin", "");
                }
            } else {
                alteredResponse.setHeader("Access-Control-Allow-Origin", "*");
            }

            alteredResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
            alteredResponse.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, " +
                    "Content-Type, Accept, Content-Encoding");
            alteredResponse.setHeader("Access-Control-Max-Age", "86400");
            alteredResponse.setHeader("Access-Control-Allow-Credentials", "true");
        }
    }

    private boolean isValidDomain(String origin) {
        if (origin == null) {
            return false;
        }
        for (String allowedOrigin : allowedOrigins) {
            if (origin.contains(allowedOrigin)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {
        LOGGER.info("CORSFilter destroyed!");
    }
}

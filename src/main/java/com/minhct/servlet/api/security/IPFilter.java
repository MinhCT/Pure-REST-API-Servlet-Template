package com.minhct.servlet.api.security;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "IPFilter", urlPatterns = {"/*"})
public class IPFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(IPFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("IPFilter initialized!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException, IOException {
        String requestIP = "" /* Code to get IP from servlet request */;
        LOGGER.info(String.format("Requester's IP: %s", requestIP));

        // Logic to filter out unwanted IP addresses

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        LOGGER.info("IPFilter destroyed");
    }
}
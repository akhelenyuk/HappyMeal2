package com.khelenyuk.controller.filter;

import com.khelenyuk.model.User;
import com.khelenyuk.utils.ConfigurationManager;
import com.khelenyuk.utils.UtilManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * Checks if user is admin
     * if user is not logged in or has no admin rights - redirect to error page
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = (User) request.getSession().getAttribute("user");

        if (user != null && UtilManager.getProperty("role.admin").equalsIgnoreCase(user.getRoleId().toString())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            logger.info("User tried to reach " + request.getRequestURI() + " without being logged in as administrator! Redirected to error page!");
//            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(ConfigurationManager.getProperty("path.page.error"));
//            dispatcher.forward(request, response);
            response.sendRedirect(ConfigurationManager.getProperty("path.page.error"));
        }

    }

    @Override
    public void destroy() {
    }
}

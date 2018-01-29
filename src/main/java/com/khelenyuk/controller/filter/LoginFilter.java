package com.khelenyuk.controller.filter;

import com.khelenyuk.model.User;
import com.khelenyuk.utils.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    /**
     * Checks if user is logged in
     * if user is null - redirect to login.jsp
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // getting user from session
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            logger.warn("User tried to reach " + request.getRequestURI() + " without being logged in! Redirected to login page!");
//            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(ConfigurationManager.getProperty("path.page.login"));
//            dispatcher.forward(request, response);
            response.sendRedirect(ConfigurationManager.getProperty("path.page.login"));
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}

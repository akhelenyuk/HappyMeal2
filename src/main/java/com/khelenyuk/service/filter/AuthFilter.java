package com.khelenyuk.service.filter;

import com.khelenyuk.model.User;
import com.khelenyuk.utils.ConfigurationManager;
import com.khelenyuk.utils.UtilManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.apache.logging.log4j.web.WebLoggerContextUtils.getServletContext;

public class AuthFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // getting user from session
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = (User) request.getSession().getAttribute("user");

// if user is not logged in or has no admin rights - redirect to error page
        if (user != null && UtilManager.getProperty("role.admin").equalsIgnoreCase(user.getRoleId().toString())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            logger.info("User is not logged in or has no admin rights to enter this page!");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(ConfigurationManager.getProperty("path.page.error"));
            dispatcher.forward(request, response);
//            response.sendRedirect(ConfigurationManager.getProperty("path.page.error"));
        }

    }

    @Override
    public void destroy() {
    }
}

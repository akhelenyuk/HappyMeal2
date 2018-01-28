package com.khelenyuk.service.filter;

import com.khelenyuk.model.User;
import com.khelenyuk.utils.ConfigurationManager;
import com.khelenyuk.utils.MessageManager;
import com.khelenyuk.utils.UtilManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BlockUserFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(BlockUserFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // getting user from session
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = (User) request.getSession().getAttribute("user");


        /**
         *  if user is null or blocked - forward to login.jsp
         */
        RequestDispatcher dispatcher = request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.login"));
        if (user == null) {
            logger.info("User tried to reach " + request.getRequestURI() + " being not logged in! Forwarding to login page...");
            request.setAttribute("userNullMessage", MessageManager.getProperty("message.usernullerror"));
            dispatcher.forward(request, response);
        } else if (UtilManager.getProperty("status.blocked").equalsIgnoreCase(user.getStatusId().toString())) {
            logger.info("User tried to reach " + request.getRequestURI() + " having status 'blocked'! Forwarding to login page...");
            request.setAttribute("userBlockMessage", MessageManager.getProperty("message.userblocked"));
            dispatcher.forward(request, response);
        } else {
            logger.debug("++++ test ++++");
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}

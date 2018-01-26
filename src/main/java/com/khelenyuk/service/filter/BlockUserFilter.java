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
        logger.debug("User: " + user);
        if (user != null) {
            logger.debug("User status id: " + user.getStatusId());
        }

        logger.debug("********************" + UtilManager.getProperty("status.unblocked").equalsIgnoreCase(user.getStatusId().toString()));
        // if user is blocked - redirect to login.jsp
        if (user != null && UtilManager.getProperty("status.unblocked").equalsIgnoreCase(user.getStatusId().toString())) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            logger.info("User tried to reach " + request.getRequestURI() + " having status 'blocked'! Redirected to login page!");
            request.setAttribute("userBlockMessage", MessageManager.getProperty("message.userblocked"));
            RequestDispatcher dispatcher = request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.login"));
            dispatcher.forward(request, response);
//            response.sendRedirect(ConfigurationManager.getProperty("path.page.login"));
        }
    }

    @Override
    public void destroy() {
    }
}

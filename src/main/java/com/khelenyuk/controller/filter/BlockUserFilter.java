package com.khelenyuk.controller.filter;

import com.khelenyuk.model.User;
import com.khelenyuk.utils.ConfigurationManager;
import com.khelenyuk.utils.MessageManager;
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        logger.debug("User: " + user);
        if (user != null) {
            logger.debug("User status id: " + user.getStatusId());
        }

        // if user is blocked - redirect to login.jsp

        if (user == null) {
            user = new User();
            user.setStatusId(1);
            session.setAttribute("user", user);
        }
        logger.debug("*******************"+request.getRequestURL());
        logger.debug("^^^^^^^^^^^^^^^^^^^"+request.getRequestURI());

        if (user.getStatusId() == 2 && !request.getRequestURI().equals(ConfigurationManager.getProperty("path.page.login"))) {
            session.setAttribute("userBlockMessage", MessageManager.getProperty("message.userblocked"));
            response.sendRedirect(ConfigurationManager.getProperty("path.page.login"));

        } else {
            filterChain.doFilter(servletRequest, servletResponse);

        }

    }

    @Override
    public void destroy() {
    }
}

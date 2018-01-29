package com.khelenyuk.controller;

import com.khelenyuk.controller.command.ActionCommand;
import com.khelenyuk.controller.command.factory.ActionFactory;
import com.khelenyuk.service.IPageService;
import com.khelenyuk.service.factory.ServiceFactory;
import com.khelenyuk.utils.ConfigurationManager;
import com.khelenyuk.utils.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MyServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(MyServlet.class);
    private IPageService pageService = ServiceFactory.getPageService();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // todo check if this is needed
        request.setCharacterEncoding("UTF-8");

        String page = null;
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        logger.info("Obtained command: " + command.getClass().getSimpleName());
        page = command.execute(request);

        if (page != null) {
            if (pageService.isRedirect()) {
                logger.info("Request will be redirected to " + page);
                pageService.setRedirect(false);
                response.sendRedirect(page);
            } else {
                logger.info("Request will be forwarded to " + page);
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
            }

        } else {
            page = ConfigurationManager.getProperty("path.page.error");
            request.getSession().setAttribute("nullPage", MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}


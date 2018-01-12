package com.khelenyuk.service.command.commands;

import com.khelenyuk.service.command.ActionCommand;
import com.khelenyuk.model.User;
import com.khelenyuk.service.IPageService;
import com.khelenyuk.service.factory.ServiceFactory;
import com.khelenyuk.controller.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class SelectDateCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(SelectDateCommand.class);

    private static final String PARAM_NAME_SELECTED_DATE = "chosenDate";

    private IPageService pageService = ServiceFactory.getPageService();




    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = ConfigurationManager.getProperty("path.page.main");

        session.setAttribute("chosenDateSession", LocalDate.parse(
                request.getParameter(PARAM_NAME_SELECTED_DATE).toString()));

        pageService.updatePageData(session, ((User)session.getAttribute("user")).getId());

        return page;
    }

}

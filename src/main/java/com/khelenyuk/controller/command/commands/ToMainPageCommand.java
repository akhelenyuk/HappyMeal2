package com.khelenyuk.controller.command.commands;

import com.khelenyuk.model.User;
import com.khelenyuk.controller.service.IPageService;
import com.khelenyuk.controller.service.IUserService;
import com.khelenyuk.controller.command.ActionCommand;
import com.khelenyuk.controller.service.factory.ServiceFactory;
import com.khelenyuk.utils.ConfigurationManager;
import com.khelenyuk.utils.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class ToMainPageCommand implements ActionCommand {
    private IUserService userService = ServiceFactory.getUserService();
    private IPageService pageService = ServiceFactory.getPageService();

    /**
     * Checks if session has attribute user. If yes method returns main page. Otherwise method returns
     * login page.
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.login");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        if (user != null) {
            pageService.updateMainPageData(session, user.getId());
            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
        }

        return page;
    }
}

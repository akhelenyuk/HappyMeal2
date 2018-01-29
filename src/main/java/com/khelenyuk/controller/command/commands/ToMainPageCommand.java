package com.khelenyuk.controller.command.commands;

import com.khelenyuk.model.User;
import com.khelenyuk.service.IPageService;
import com.khelenyuk.controller.command.ActionCommand;
import com.khelenyuk.service.factory.ServiceFactory;
import com.khelenyuk.utils.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class ToMainPageCommand implements ActionCommand {
    private IPageService pageService = ServiceFactory.getPageService();

    /**
     * @return main page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.main");

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        // TO DELETE is done by filter
        if (user == null) {
            page = ConfigurationManager.getProperty("path.page.login");
        } else {
            pageService.updateMainPageData(session, user.getId());
        }

        return page;
    }
}

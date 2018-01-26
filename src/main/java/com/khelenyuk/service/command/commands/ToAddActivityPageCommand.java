package com.khelenyuk.service.command.commands;

import com.khelenyuk.model.User;
import com.khelenyuk.service.IPageService;
import com.khelenyuk.service.command.ActionCommand;
import com.khelenyuk.service.factory.ServiceFactory;
import com.khelenyuk.utils.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ToAddActivityPageCommand implements ActionCommand {
    private IPageService pageService = ServiceFactory.getPageService();

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.addActivity");
        pageService.updateUser(request, (User) request.getSession().getAttribute("user"));

        return page;
    }
}

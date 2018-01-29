package com.khelenyuk.controller.command.commands;

import com.khelenyuk.service.IPageService;
import com.khelenyuk.model.User;
import com.khelenyuk.service.IUserService;
import com.khelenyuk.controller.command.ActionCommand;
import com.khelenyuk.service.factory.ServiceFactory;
import com.khelenyuk.utils.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToAdminPageCommand implements ActionCommand {
    private IPageService pageService = ServiceFactory.getPageService();

    /**
     * @return admin page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.admin");

        pageService.updateAdminPageData(request);
        return page;
    }
}

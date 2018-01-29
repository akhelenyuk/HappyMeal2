package com.khelenyuk.controller.command.commands;

import com.khelenyuk.controller.command.ActionCommand;
import com.khelenyuk.service.IPageService;
import com.khelenyuk.model.Gender;
import com.khelenyuk.model.Lifestyle;
import com.khelenyuk.service.factory.ServiceFactory;
import com.khelenyuk.service.IUserService;
import com.khelenyuk.utils.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToRegistrationPageCommand implements ActionCommand {
    private IPageService pageService = ServiceFactory.getPageService();

    /**
     * @return registration page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.registration");
        pageService.updateRegistrationPageData(request);

        return page;
    }
}

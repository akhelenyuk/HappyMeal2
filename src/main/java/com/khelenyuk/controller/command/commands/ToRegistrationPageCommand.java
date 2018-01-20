package com.khelenyuk.controller.command.commands;

import com.khelenyuk.controller.command.ActionCommand;
import com.khelenyuk.controller.service.IPageService;
import com.khelenyuk.model.Gender;
import com.khelenyuk.model.Lifestyle;
import com.khelenyuk.controller.service.factory.ServiceFactory;
import com.khelenyuk.controller.service.IUserService;
import com.khelenyuk.utils.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToRegistrationPageCommand implements ActionCommand {
    private IPageService pageService = ServiceFactory.getPageService();


    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.registration");
        pageService.updateRegistrationPageData(request);

        return page;
    }
}

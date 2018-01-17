package com.khelenyuk.controller.command.commands;

import com.khelenyuk.controller.command.ActionCommand;
import com.khelenyuk.controller.service.ILoginRegistrationService;
import com.khelenyuk.controller.service.IPageService;
import com.khelenyuk.controller.service.factory.ServiceFactory;
import com.khelenyuk.controller.service.IUserService;
import com.khelenyuk.utils.MessageManager;
import com.khelenyuk.utils.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private HttpSession session;

    private IUserService userService = ServiceFactory.getUserService();
    private ILoginRegistrationService loginRegistrationService = ServiceFactory.getLoginRegistrationService();
    private IPageService pageService = ServiceFactory.getPageService();



    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        session = request.getSession();
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);


        if (loginRegistrationService.checkLogin(login, password)) {
            pageService.updatePageData(session, userService.getUser(login).getId());
            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = ConfigurationManager.getProperty("path.page.login");
        }


        return page;
    }
}

package com.khelenyuk.service.command.commands;

import com.khelenyuk.service.command.ActionCommand;
import com.khelenyuk.service.ILoginRegistrationService;
import com.khelenyuk.service.IPageService;
import com.khelenyuk.service.factory.ServiceFactory;
import com.khelenyuk.service.IUserService;
import com.khelenyuk.controller.MessageManager;

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
            page = com.khelenyuk.controller.ConfigurationManager.getProperty("path.page.main");
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = com.khelenyuk.controller.ConfigurationManager.getProperty("path.page.login");
        }


        return page;
    }
}

package com.khelenyuk.command.commands;

import com.khelenyuk.command.ActionCommand;
import com.khelenyuk.service.LoginRegistrationLogic;
import com.khelenyuk.service.PageLogic;
import com.khelenyuk.service.UserLogic;
import com.khelenyuk.servlet.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private HttpSession session;

    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
        session = request.getSession();
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);


        if (LoginRegistrationLogic.checkLogin(login, password)) {
            PageLogic.updatePageData(session, UserLogic.getUser(login).getId());
            page = com.khelenyuk.servlet.ConfigurationManager.getProperty("path.page.main");
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = com.khelenyuk.servlet.ConfigurationManager.getProperty("path.page.login");
        }


        return page;
    }
}

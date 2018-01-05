package command.commands;

import command.ActionCommand;
import entity.User;
import service.LoginRegistrationLogic;
import service.PageLogic;
import service.UserLogic;
import servlet.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

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
            page = servlet.ConfigurationManager.getProperty("path.page.main");
        } else {
            request.setAttribute("errorLoginPassMessage", MessageManager.getProperty("message.loginerror"));
            page = servlet.ConfigurationManager.getProperty("path.page.login");
        }


        return page;
    }
}

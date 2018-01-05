package command.commands;

import command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = servlet.ConfigurationManager.getProperty("path.page.index");
        request.getSession().invalidate();
        return page;
    }
}

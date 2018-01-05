package command.commands;

import command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        System.out.println("EmptyCommand");
        return null;
    }
}

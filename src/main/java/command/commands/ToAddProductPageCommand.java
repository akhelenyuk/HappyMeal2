package command.commands;

import command.ActionCommand;
import entity.Lifestyle;
import entity.Sex;
import service.UserLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToAddProductPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = servlet.ConfigurationManager.getProperty("path.page.addproduct");
        return page;
    }
}

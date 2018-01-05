package command.commands;

import command.ActionCommand;
import entity.Lifestyle;
import entity.Sex;
import entity.User;
import service.UserLogic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToRegistrationPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = servlet.ConfigurationManager.getProperty("path.page.registration");
        HttpSession session = request.getSession();
        List<Sex> sex = UserLogic.getSex();
        List<Lifestyle> lifestyles = UserLogic.getLifestyles();

        session.setAttribute("sex",sex);
        session.setAttribute("lifestyle",lifestyles);


        return page;
    }
}

package com.khelenyuk.command.commands;

import com.khelenyuk.command.ActionCommand;
import com.khelenyuk.entity.Lifestyle;
import com.khelenyuk.entity.Sex;
import com.khelenyuk.service.UserLogic;
import com.khelenyuk.servlet.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToRegistrationPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.registration");
        HttpSession session = request.getSession();
        List<Sex> sex = UserLogic.getSex();
        List<Lifestyle> lifestyles = UserLogic.getLifestyles();

        session.setAttribute("sex",sex);
        session.setAttribute("lifestyle",lifestyles);


        return page;
    }
}

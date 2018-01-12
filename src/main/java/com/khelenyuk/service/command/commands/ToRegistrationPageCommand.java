package com.khelenyuk.service.command.commands;

import com.khelenyuk.service.command.ActionCommand;
import com.khelenyuk.model.Lifestyle;
import com.khelenyuk.model.Sex;
import com.khelenyuk.service.factory.ServiceFactory;
import com.khelenyuk.service.IUserService;
import com.khelenyuk.controller.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToRegistrationPageCommand implements ActionCommand {
    private IUserService userService = ServiceFactory.getUserService();


    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.registration");
        HttpSession session = request.getSession();
        List<Sex> sex = userService.getSex();
        List<Lifestyle> lifestyles = userService.getLifestyles();

        session.setAttribute("sex",sex);
        session.setAttribute("lifestyle",lifestyles);


        return page;
    }
}

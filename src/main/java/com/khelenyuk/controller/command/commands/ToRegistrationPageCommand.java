package com.khelenyuk.controller.command.commands;

import com.khelenyuk.controller.command.ActionCommand;
import com.khelenyuk.model.Gender;
import com.khelenyuk.model.Lifestyle;
import com.khelenyuk.controller.service.factory.ServiceFactory;
import com.khelenyuk.controller.service.IUserService;
import com.khelenyuk.utils.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ToRegistrationPageCommand implements ActionCommand {
    private IUserService userService = ServiceFactory.getUserService();


    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.registration");
        HttpSession session = request.getSession();
        List<Gender> genders = userService.getGender();
        List<Lifestyle> lifestyles = userService.getLifestyles();

        session.setAttribute("genders", genders);
        session.setAttribute("lifestyle",lifestyles);


        return page;
    }
}

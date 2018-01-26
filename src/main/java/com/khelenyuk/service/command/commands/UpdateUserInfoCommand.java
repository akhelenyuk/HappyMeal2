package com.khelenyuk.service.command.commands;

import com.khelenyuk.service.command.ActionCommand;
import com.khelenyuk.service.ILoginRegistrationService;
import com.khelenyuk.service.IPageService;
import com.khelenyuk.service.IUserService;
import com.khelenyuk.service.factory.ServiceFactory;
import com.khelenyuk.model.User;
import com.khelenyuk.utils.ConfigurationManager;
import com.khelenyuk.utils.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;

public class UpdateUserInfoCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(UpdateUserInfoCommand.class);


    private static final String PARAM_NAME_BIRTHDAY = "birthday";
    private static final String PARAM_NAME_WEIGHT = "weight";
    private static final String PARAM_NAME_GOAL_WEIGHT = "goalWeight";
    private static final String PARAM_NAME_HEIGHT = "height";
    private static final String PARAM_NAME_GENDER = "genderId";
    private static final String PARAM_NAME_LIFESTYLE = "lifestyleId";

    private IUserService userService = ServiceFactory.getUserService();
    private IPageService pageService = ServiceFactory.getPageService();

    private HttpSession session;

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.main");
        session = request.getSession();


        User newUser = updateUser(request, (User) session.getAttribute("user"));
        logger.info("User to be updated: " + newUser);


        if (userService.updateUser(newUser)) {
            request.setAttribute("updateUserSuccessMessage", MessageManager.getProperty("message.updateuserconfirm"));
            pageService.updateUser(request, newUser);
        } else {
            request.setAttribute("updateUserErrorMessage", MessageManager.getProperty("message.updateusererror"));
        }

        return page;
    }

    private User updateUser(HttpServletRequest request, User user) {
        user.setBirthday(LocalDate.parse(request.getParameter(PARAM_NAME_BIRTHDAY)));
        user.setGenderId(Integer.valueOf(request.getParameter(PARAM_NAME_GENDER)));
        user.setWeight(Integer.valueOf(request.getParameter(PARAM_NAME_WEIGHT)));
        user.setGoalWeight(Integer.valueOf(request.getParameter(PARAM_NAME_GOAL_WEIGHT)));
        user.setLifestyleId(Integer.valueOf(request.getParameter(PARAM_NAME_LIFESTYLE)));
        user.setHeight(Integer.valueOf(request.getParameter(PARAM_NAME_HEIGHT)));

        return user;
    }
}

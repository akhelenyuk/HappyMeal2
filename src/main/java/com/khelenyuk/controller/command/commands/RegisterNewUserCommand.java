package com.khelenyuk.controller.command.commands;

import com.khelenyuk.controller.command.ActionCommand;
import com.khelenyuk.service.IPageService;
import com.khelenyuk.model.User;
import com.khelenyuk.service.ILoginRegistrationService;
import com.khelenyuk.service.factory.ServiceFactory;
import com.khelenyuk.service.IUserService;
import com.khelenyuk.utils.ConfigurationManager;
import com.khelenyuk.utils.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;

public class RegisterNewUserCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(RegisterNewUserCommand.class);

    private static final String PARAM_NAME_FIRST_NAME = "first_name";
    private static final String PARAM_NAME_LAST_NAME = "last_name";
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_EMAIL = "email";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final String PARAM_NAME_PASSWORD_CONFIRM = "password_confirmation";
    private static final String PARAM_NAME_BIRTHDAY = "birthday";
    private static final String PARAM_NAME_WEIGHT = "weight";
    private static final String PARAM_NAME_GOAL_WEIGHT = "goalWeight";
    private static final String PARAM_NAME_HEIGHT = "height";
    private static final String PARAM_NAME_GENDER = "gender";
    private static final String PARAM_NAME_LIFESTYLE = "lifestyle";

    private IUserService userService = ServiceFactory.getUserService();
    private ILoginRegistrationService loginRegistrationService = ServiceFactory.getLoginRegistrationService();



    /**
     * Adds new User
     * if success - returns login page
     * if fail - returns registration page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.registration");
        User newUser = getUserFromRequest(request);
        request.setAttribute("registrationUser", newUser);

        String password_confirm = request.getParameter(PARAM_NAME_PASSWORD_CONFIRM);

        if (!loginRegistrationService.confirmPassword(newUser.getPassword(), password_confirm)) {
            request.setAttribute("errorPassConfirmMessage", MessageManager.getProperty("message.passconfirmerror"));
            return page;
        }
        logger.info("Pass confirm OK:");


        if (loginRegistrationService.checkLoginExist(newUser.getLogin())) {
            request.setAttribute("errorLoginExistMessage", MessageManager.getProperty("message.loginexisterror"));
            return page;
        }
        logger.info("Login check OK:");


        if (userService.addUser(newUser)) {
            request.setAttribute("registrationSuccessMessage", MessageManager.getProperty("message.registrationconfirm"));
            page = ConfigurationManager.getProperty("path.page.login");
        } else {
            request.setAttribute("errorRegistrationMessage", MessageManager.getProperty("message.registrationerror"));
        }

        return page;
    }

    /**
     * constructs 'User' from 'HttpServletRequest'
     * @param request
     * @return User
     */
    private User getUserFromRequest(HttpServletRequest request) {
        User user = new User(request.getParameter(PARAM_NAME_LOGIN),
                request.getParameter(PARAM_NAME_PASSWORD),
                request.getParameter(PARAM_NAME_FIRST_NAME),
                request.getParameter(PARAM_NAME_LAST_NAME),
                request.getParameter(PARAM_NAME_EMAIL),
                LocalDate.parse(request.getParameter(PARAM_NAME_BIRTHDAY)),
                Integer.valueOf(request.getParameter(PARAM_NAME_GENDER)),
                Integer.valueOf(request.getParameter(PARAM_NAME_WEIGHT)),
                Integer.valueOf(request.getParameter(PARAM_NAME_GOAL_WEIGHT)),
                Integer.valueOf(request.getParameter(PARAM_NAME_HEIGHT)),
                Integer.valueOf(request.getParameter(PARAM_NAME_LIFESTYLE))
        );
        return user;
    }
}

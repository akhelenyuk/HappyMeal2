package com.khelenyuk.command.commands;

import com.khelenyuk.command.ActionCommand;
import com.khelenyuk.entity.Meal;
import com.khelenyuk.service.MenuLogic;
import com.khelenyuk.service.PageLogic;
import com.khelenyuk.servlet.ConfigurationManager;
import com.khelenyuk.servlet.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddMealCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AddMealCommand.class);

    private static final String PARAM_NAME_USER_ID = "user_id";
    private static final String PARAM_NAME_PRODUCT_ID = "product_id";
    private static final String PARAM_NAME_WEIGHT = "weight";
    private static final String PARAM_NAME_MEAL_NUMBER = "meal_number";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = ConfigurationManager.getProperty("path.page.main");

        Meal meal = new Meal(
                Integer.valueOf(request.getParameter(PARAM_NAME_USER_ID)),
                Integer.valueOf(request.getParameter(PARAM_NAME_PRODUCT_ID)),
                Integer.valueOf(request.getParameter(PARAM_NAME_WEIGHT)),
                Integer.valueOf(request.getParameter(PARAM_NAME_MEAL_NUMBER))
        );


        if (MenuLogic.addMeal(meal)) {
            PageLogic.updatePageData(session, meal.getUserId());
        } else {
            session.setAttribute("errorAddMealMessage", MessageManager.getProperty("message.addmealerror"));
        }

        return page;
    }

}

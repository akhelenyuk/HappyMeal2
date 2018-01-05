package command.commands;

import command.ActionCommand;
import entity.Meal;
import entity.MealFull;
import service.MenuLogic;
import service.PageLogic;
import servlet.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class AddMealCommand implements ActionCommand {
    private static final String PARAM_NAME_USER_ID = "user_id";
    private static final String PARAM_NAME_PRODUCT_ID = "product_id";
    private static final String PARAM_NAME_WEIGHT = "weight";
    private static final String PARAM_NAME_MEAL_NUMBER = "meal_number";

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = servlet.ConfigurationManager.getProperty("path.page.main");

        System.out.println("test");

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

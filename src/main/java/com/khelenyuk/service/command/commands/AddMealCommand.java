package com.khelenyuk.service.command.commands;

import com.khelenyuk.service.command.ActionCommand;
import com.khelenyuk.controller.ConfigurationManager;
import com.khelenyuk.controller.MessageManager;
import com.khelenyuk.model.Meal;
import com.khelenyuk.service.IMenuService;
import com.khelenyuk.service.IPageService;
import com.khelenyuk.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class AddMealCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AddMealCommand.class);

    private static final String PARAM_NAME_USER_ID = "user_id";
    private static final String SESSION_ATTR_NAME_DATE = "chosenDateSession";
    private static final String PARAM_NAME_PRODUCT_ID = "product_id";
    private static final String PARAM_NAME_WEIGHT = "weight";
    private static final String PARAM_NAME_MEAL_NUMBER = "meal_number";

    private IPageService pageService = ServiceFactory.getPageService();
    private IMenuService menuService = ServiceFactory.getMenuService();

    /**
     * {@inheritDoc}.
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = ConfigurationManager.getProperty("path.page.main");

        logger.debug("Chosen date param: '" + SESSION_ATTR_NAME_DATE + "'");
        logger.debug("Get parameter: '" + session.getAttribute(SESSION_ATTR_NAME_DATE) + "'");

        Meal meal = new Meal(
                Integer.valueOf(request.getParameter(PARAM_NAME_USER_ID)),
                (LocalDate) session.getAttribute(SESSION_ATTR_NAME_DATE),
                Integer.valueOf(request.getParameter(PARAM_NAME_PRODUCT_ID)),
                Integer.valueOf(request.getParameter(PARAM_NAME_WEIGHT)),
                Integer.valueOf(request.getParameter(PARAM_NAME_MEAL_NUMBER))
        );


        if (menuService.addMeal(meal)) {
            pageService.updatePageData(session, meal.getUserId());
        } else {
            session.setAttribute("errorAddMealMessage", MessageManager.getProperty("message.addmealerror"));
        }

        return page;
    }

}

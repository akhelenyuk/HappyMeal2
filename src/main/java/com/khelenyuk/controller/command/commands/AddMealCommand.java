package com.khelenyuk.controller.command.commands;

import com.khelenyuk.model.User;
import com.khelenyuk.controller.command.ActionCommand;
import com.khelenyuk.utils.ConfigurationManager;
import com.khelenyuk.utils.MessageManager;
import com.khelenyuk.model.Meal;
import com.khelenyuk.service.IMenuService;
import com.khelenyuk.service.IPageService;
import com.khelenyuk.service.factory.ServiceFactory;
import com.khelenyuk.utils.UtilManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class AddMealCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AddMealCommand.class);

    private static final String SESSION_ATTR_NAME_USER = UtilManager.getProperty("session.user");
    private static final String SESSION_ATTR_NAME_DATE = UtilManager.getProperty("session.chosenDate");
    private static final String REQUEST_PARAM_PRODUCT_ID = UtilManager.getProperty("request.productId");
    private static final String REQUEST_PARAM_WEIGHT = UtilManager.getProperty("request.weight");
    private static final String REQUEST_PARAM_MEAL_TYPE_ID = UtilManager.getProperty("request.mealTypeId");

    private IPageService pageService = ServiceFactory.getPageService();
    private IMenuService menuService = ServiceFactory.getMenuService();

    /**
     * Adds meal to Food Diary.
     * @Return main page
     * if meal was not added - sets 'error' attr to request
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = ConfigurationManager.getProperty("path.page.main");

        Meal mealEntry = new Meal(
                ((User) session.getAttribute(SESSION_ATTR_NAME_USER)).getId(),
                (LocalDate) session.getAttribute(SESSION_ATTR_NAME_DATE),
                Integer.valueOf(request.getParameter(REQUEST_PARAM_PRODUCT_ID)),
                Integer.valueOf(request.getParameter(REQUEST_PARAM_WEIGHT)),
                Integer.valueOf(request.getParameter(REQUEST_PARAM_MEAL_TYPE_ID))
        );

        if (menuService.addMeal(mealEntry)) {
            pageService.updateMainPageData(session, mealEntry.getUserId());
        } else {
            request.setAttribute("errorAddMealMessage", MessageManager.getProperty("message.addmealerror"));
        }

        return page;
    }

}

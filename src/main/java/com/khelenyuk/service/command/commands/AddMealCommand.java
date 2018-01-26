package com.khelenyuk.service.command.commands;

import com.khelenyuk.model.User;
import com.khelenyuk.service.command.ActionCommand;
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
    private static final String REQUEST_PARAM_PRODUCT_ID = "product_id";
    private static final String REQUEST_PARAM_WEIGHT = UtilManager.getProperty("request.weight");
    private static final String REQUEST_PARAM_MEAL_TYPE_ID = UtilManager.getProperty("request.mealTypeId");

    private IPageService pageService = ServiceFactory.getPageService();
    private IMenuService menuService = ServiceFactory.getMenuService();

    /**
     * {@inheritDoc}.
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = ConfigurationManager.getProperty("path.page.main");

        logger.info("Chosen meal type: '" + request.getParameter(REQUEST_PARAM_MEAL_TYPE_ID) + "'");


        Meal mealEntry = new Meal(
                ((User) session.getAttribute(SESSION_ATTR_NAME_USER)).getId(),
                (LocalDate) session.getAttribute(SESSION_ATTR_NAME_DATE),
                Integer.valueOf(request.getParameter(REQUEST_PARAM_PRODUCT_ID)),
                Integer.valueOf(request.getParameter(REQUEST_PARAM_WEIGHT)),
                Integer.valueOf(request.getParameter(REQUEST_PARAM_MEAL_TYPE_ID))
        );
        logger.info("Meal entry to add: " + mealEntry);


        if (menuService.addMeal(mealEntry)) {
            logger.info("Meal successfully added!");
            pageService.updateMainPageData(session, mealEntry.getUserId());
        } else {
            logger.info("Meal adding error!");
            session.setAttribute("errorAddMealMessage", MessageManager.getProperty("message.addmealerror"));
        }

        return page;
    }

}

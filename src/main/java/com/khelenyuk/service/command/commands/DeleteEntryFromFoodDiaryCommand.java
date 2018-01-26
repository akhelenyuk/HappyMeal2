package com.khelenyuk.service.command.commands;

import com.khelenyuk.model.User;
import com.khelenyuk.service.IMenuService;
import com.khelenyuk.service.IPageService;
import com.khelenyuk.service.command.ActionCommand;
import com.khelenyuk.service.factory.ServiceFactory;
import com.khelenyuk.utils.ConfigurationManager;
import com.khelenyuk.utils.MessageManager;
import com.khelenyuk.utils.UtilManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeleteEntryFromFoodDiaryCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(DeleteEntryFromFoodDiaryCommand.class);

    private static final String REQUEST_PARAM_MEAL_ID = UtilManager.getProperty("request.mealId");

    private IPageService pageService = ServiceFactory.getPageService();
    private IMenuService menuService = ServiceFactory.getMenuService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = ConfigurationManager.getProperty("path.page.main");

        int id = Integer.valueOf(request.getParameter(REQUEST_PARAM_MEAL_ID));
        logger.debug("$$$$$$$$$$$$$$$$$$$$$$$$$$$  3 id:" + id);


        if (menuService.deleteFromFoodDiary(id)) {
            logger.info("Meal successfully deleted from diary!");
            pageService.updateMainPageData(session, ((User)session.getAttribute("user")).getId());
        } else {
            logger.info("Meal deleting error!");
            session.setAttribute("errorDeleteMealFromDiaryMessage", MessageManager.getProperty("message.deletemealerror"));
        }

        return page;
    }

}

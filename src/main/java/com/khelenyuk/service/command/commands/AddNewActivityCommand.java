package com.khelenyuk.service.command.commands;

import com.khelenyuk.service.command.ActionCommand;
import com.khelenyuk.service.IActivityService;
import com.khelenyuk.service.IPageService;
import com.khelenyuk.service.factory.ServiceFactory;
import com.khelenyuk.model.Activity;
import com.khelenyuk.model.User;
import com.khelenyuk.utils.ConfigurationManager;
import com.khelenyuk.utils.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AddNewActivityCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AddNewActivityCommand.class);

    private static final String PARAM_NAME_NAME = "name";
    private static final String PARAM_NAME_CALORIES = "calories";


    private IPageService pageService = ServiceFactory.getPageService();
    private IActivityService activityService = ServiceFactory.getActivityService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = ConfigurationManager.getProperty("path.page.addActivity");

        Activity newActivity = getActivityFromRequest(request);
        request.setAttribute("newActivity", newActivity);


        if (activityService.checkActivityExist(newActivity.getName())) {
            logger.info("Such activity already exists!");
            request.setAttribute("errorActivityExistMessage", MessageManager.getProperty("message.activityexist"));
            return page;
        }

        if (activityService.addActivity(newActivity)) {
            session.setAttribute("successAddActivityMessage", MessageManager.getProperty("message.addnewactivitysuccess"));
            pageService.updateMainPageData(session, ((User) session.getAttribute("user")).getId());
            page = ConfigurationManager.getProperty("path.page.main");
        } else {
            session.setAttribute("errorAddActivityMessage", MessageManager.getProperty("message.addnewactivityerror"));
            page = ConfigurationManager.getProperty("path.page.addProduct");
        }


        return page;
    }

    private Activity getActivityFromRequest(HttpServletRequest request) {
        Activity activity = new Activity(
                request.getParameter(PARAM_NAME_NAME),
                Integer.valueOf(request.getParameter(PARAM_NAME_CALORIES))
        );
        return activity;
    }
}

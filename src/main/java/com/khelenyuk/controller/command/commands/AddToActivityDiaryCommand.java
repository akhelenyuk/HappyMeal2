package com.khelenyuk.controller.command.commands;

import com.khelenyuk.controller.command.ActionCommand;
import com.khelenyuk.controller.service.IActivityDiaryService;
import com.khelenyuk.controller.service.IPageService;
import com.khelenyuk.controller.service.factory.ServiceFactory;
import com.khelenyuk.model.ActivityDiary;
import com.khelenyuk.model.User;
import com.khelenyuk.utils.ConfigurationManager;
import com.khelenyuk.utils.MessageManager;
import com.khelenyuk.utils.UtilManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class AddToActivityDiaryCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AddToActivityDiaryCommand.class);

    private static final String SESSION_ATTR_NAME_USER = UtilManager.getProperty("session.user");
    private static final String REQUEST_PARAM_ACTIVITY_ID = UtilManager.getProperty("request.activityId");
    private static final String REQUEST_PARAM_TIME_SPENT = UtilManager.getProperty("request.timeSpent");
    private static final String SESSION_ATTR_NAME_DATE = UtilManager.getProperty("session.chosenDate");

    private IPageService pageService = ServiceFactory.getPageService();
    private IActivityDiaryService activityDiaryService = ServiceFactory.getActivityDiaryService();

    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = ConfigurationManager.getProperty("path.page.main");

        ActivityDiary activityEntry = new ActivityDiary(
                ((User) session.getAttribute(SESSION_ATTR_NAME_USER)).getId(),
                Integer.valueOf(request.getParameter(REQUEST_PARAM_ACTIVITY_ID)),
                Integer.valueOf(request.getParameter(REQUEST_PARAM_TIME_SPENT)),
                (LocalDate) session.getAttribute(SESSION_ATTR_NAME_DATE)
        );
        logger.info("Activity entry to add: " + activityEntry);


        if (activityDiaryService.addActivityToDiary(activityEntry)) {
            logger.info("Activity successfully added to diary!");
            pageService.updateMainPageData(session, ((User)session.getAttribute("user")).getId());
        } else {
            logger.info("Activity adding error!");
            session.setAttribute("errorAddActivityToDiaryMessage", MessageManager.getProperty("message.addactivityerror"));
        }

        return page;
    }

}

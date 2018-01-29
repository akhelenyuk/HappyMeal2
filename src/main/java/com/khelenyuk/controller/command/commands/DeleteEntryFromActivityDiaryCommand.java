package com.khelenyuk.controller.command.commands;

import com.khelenyuk.model.ActivityDiary;
import com.khelenyuk.model.User;
import com.khelenyuk.service.IActivityDiaryService;
import com.khelenyuk.service.IPageService;
import com.khelenyuk.controller.command.ActionCommand;
import com.khelenyuk.service.factory.ServiceFactory;
import com.khelenyuk.utils.ConfigurationManager;
import com.khelenyuk.utils.MessageManager;
import com.khelenyuk.utils.UtilManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;

public class DeleteEntryFromActivityDiaryCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(DeleteEntryFromActivityDiaryCommand.class);

    private static final String REQUEST_PARAM_ACTIVITY_ID = UtilManager.getProperty("request.activityId");

    private IPageService pageService = ServiceFactory.getPageService();
    private IActivityDiaryService activityDiaryService = ServiceFactory.getActivityDiaryService();

    /**
     * Delete Activity from Activity Diary.
     * @Return main page
     * if Activity was not deleted - sets 'error' attr to request
     */
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = ConfigurationManager.getProperty("path.page.main");

        int id = Integer.valueOf(request.getParameter(REQUEST_PARAM_ACTIVITY_ID));

        if (activityDiaryService.deleteFromActivityDiary(id)) {
            logger.info("Activity successfully deleted from diary!");
            pageService.updateMainPageData(session, ((User)session.getAttribute("user")).getId());
        } else {
            logger.info("Activity deleting error!");
            session.setAttribute("errorDeleteActivityFromDiaryMessage", MessageManager.getProperty("message.deleteactivityerror"));
        }

        return page;
    }

}

package com.khelenyuk.controller.command.commands;

import com.khelenyuk.controller.command.ActionCommand;
import com.khelenyuk.service.ILoginRegistrationService;
import com.khelenyuk.service.IPageService;
import com.khelenyuk.service.IUserService;
import com.khelenyuk.service.factory.ServiceFactory;
import com.khelenyuk.model.User;
import com.khelenyuk.utils.ConfigurationManager;
import com.khelenyuk.utils.MessageManager;
import com.khelenyuk.utils.UtilManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BlockUnblockUserCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(BlockUnblockUserCommand.class);

    private static final String PARAM_NAME_USER_ID = "userId";

    private IUserService userService = ServiceFactory.getUserService();
    private IPageService pageService = ServiceFactory.getPageService();

    /**
     * Changes User status to opposite.
     *
     * @Return admin page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.admin");

        int userId = Integer.valueOf(request.getParameter(PARAM_NAME_USER_ID));
        User userBlocked = userService.getUser(userId);

        int statusBlocked = Integer.valueOf(UtilManager.getProperty("status.blocked"));
        userBlocked.setStatusId((userBlocked.getStatusId() == statusBlocked) ? Integer.valueOf(UtilManager.getProperty("status.unblocked")) : Integer.valueOf(UtilManager.getProperty("status.blocked")));

        if (userService.blockUnblockUser(userBlocked)) {
            pageService.updateAdminPageData(request);
        } else {
            request.setAttribute("errorBlockUnblockUserMessage", MessageManager.getProperty("message.userblockerror"));
        }

        return page;
    }


}

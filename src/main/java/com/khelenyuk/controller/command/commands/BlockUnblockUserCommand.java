package com.khelenyuk.controller.command.commands;

import com.khelenyuk.controller.command.ActionCommand;
import com.khelenyuk.controller.service.ILoginRegistrationService;
import com.khelenyuk.controller.service.IPageService;
import com.khelenyuk.controller.service.IUserService;
import com.khelenyuk.controller.service.factory.ServiceFactory;
import com.khelenyuk.model.User;
import com.khelenyuk.utils.ConfigurationManager;
import com.khelenyuk.utils.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BlockUnblockUserCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(BlockUnblockUserCommand.class);


    private static final String PARAM_NAME_USER_ID = "userId";


    private IUserService userService = ServiceFactory.getUserService();
    private IPageService pageService = ServiceFactory.getPageService();

    private HttpSession session;

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.admin");
        session = request.getSession();


        int userId = Integer.valueOf(request.getParameter(PARAM_NAME_USER_ID));
        logger.debug("User id=" + userId);

        User userBlocked = userService.getUser(userId);
        logger.debug("User status id =" + userBlocked.getStatusId());


        userBlocked.setStatusId((userBlocked.getStatusId() == 1) ? 2 : 1);
        logger.debug("New user status id:" + userBlocked.getStatusId());


        if (userService.blockUnblockUser(userBlocked)) {
            request.setAttribute("errorBlockUnblockUserMessage", MessageManager.getProperty("message.userblockerror"));

            pageService.updateAdminPageData(session);
        } else {
        }

        return page;
    }


}

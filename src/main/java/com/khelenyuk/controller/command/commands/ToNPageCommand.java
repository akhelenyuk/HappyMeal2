package com.khelenyuk.controller.command.commands;

import com.khelenyuk.controller.command.ActionCommand;
import com.khelenyuk.service.IPageService;
import com.khelenyuk.service.factory.ServiceFactory;
import com.khelenyuk.utils.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ToNPageCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(ToNPageCommand.class);

    private IPageService pageService = ServiceFactory.getPageService();

    /**
     * Is not used. Should be a common command for all
     * 'to X page' requests
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.admin");

        pageService.updateAdminPageData(request);
        return page;
    }
}

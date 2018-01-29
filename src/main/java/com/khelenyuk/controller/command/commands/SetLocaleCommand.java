package com.khelenyuk.controller.command.commands;

import com.khelenyuk.service.IPageService;
import com.khelenyuk.controller.command.ActionCommand;
import com.khelenyuk.service.factory.ServiceFactory;
import com.khelenyuk.utils.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SetLocaleCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(SetLocaleCommand.class);

    /**
     * Gets chosen locale from HttpServletRequest
     * @Return login page
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = (ConfigurationManager.getProperty("path.page.login"));
        HttpSession session = request.getSession();

        switch (request.getParameter("locale")) {
            case "EN":
                session.setAttribute("locale", "en_US");
                session.setAttribute("selectedLocale", "EN");
                break;
            case "UA":
                default:
                session.setAttribute("locale", "uk_UA");
                session.setAttribute("selectedLocale", "UA");
                break;
        }
        logger.info("Locale set to:" + session.getAttribute("locale"));
        return page;
    }
}

package com.khelenyuk.service.command.commands;

import com.khelenyuk.service.command.ActionCommand;
import com.khelenyuk.controller.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SetLocaleCommand implements ActionCommand {
    /**
     *
     */
    @Override
    public String execute(HttpServletRequest request) {
        // тут нужна ссылка на ту же страницу, откуда пришел запрос
        String page = ConfigurationManager.getProperty("path.page.login");

        HttpSession session = request.getSession();
        switch (request.getParameter("locale")) {
            case "EN":
                session.setAttribute("locale", "en_US");
                session.setAttribute("selectedLocale", "EN");
                break;
            case "RU":
                session.setAttribute("locale", "ru_RU");
                session.setAttribute("selectedLocale", "RU");
                break;
            case "UA":
                default:
                session.setAttribute("locale", "uk_UA");
                session.setAttribute("selectedLocale", "UA");
                break;
        }
        return page;
    }
}

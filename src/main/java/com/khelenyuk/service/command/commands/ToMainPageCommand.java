package com.khelenyuk.service.command.commands;

import com.khelenyuk.service.command.ActionCommand;
import com.khelenyuk.utils.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;


public class ToMainPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.main");
//        request.getSession().setAttribute("title", ConfigurationManager.getProperty(page));

        return page;
    }
}

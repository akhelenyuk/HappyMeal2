package com.khelenyuk.command.commands;

import com.khelenyuk.command.ActionCommand;
import com.khelenyuk.controller.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

public class ToAddProductPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.addproduct");
        return page;
    }
}

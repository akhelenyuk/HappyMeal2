package com.khelenyuk.controller.command;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {

    /**
     * main method for commands
     * @param request
     * @return page
     */
    String execute(HttpServletRequest request);
}

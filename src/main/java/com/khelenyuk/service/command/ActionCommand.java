package com.khelenyuk.service.command;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {

    /**
     * main method for commands
     * default langu
     * @param request
     * @return page
     */
    String execute(HttpServletRequest request);
}

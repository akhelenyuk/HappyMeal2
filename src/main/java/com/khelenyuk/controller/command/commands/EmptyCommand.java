package com.khelenyuk.controller.command.commands;

import com.khelenyuk.controller.command.ActionCommand;
import com.khelenyuk.controller.MyServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class EmptyCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(MyServlet.class);


    @Override
    public String execute(HttpServletRequest request) {
        logger.error("Empty command");
        return null;
    }
}

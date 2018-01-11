package com.khelenyuk.command.commands;

import com.khelenyuk.command.ActionCommand;
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

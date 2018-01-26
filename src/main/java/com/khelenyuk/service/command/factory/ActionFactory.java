package com.khelenyuk.service.command.factory;

import com.khelenyuk.service.command.ActionCommand;
import com.khelenyuk.service.command.client.CommandEnum;
import com.khelenyuk.service.command.commands.EmptyCommand;
import com.khelenyuk.utils.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    private static final Logger logger = LogManager.getLogger(ActionFactory.class);

    public ActionCommand defineCommand(HttpServletRequest request){
        ActionCommand current =  new EmptyCommand();
        String action = request.getParameter("command");
        logger.info("Request attribute 'command'='" + action+"'");
        if(action == null || action.isEmpty()){
            logger.info("Command is null or empty.");
            return current;
        }

        try{
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch(IllegalArgumentException e){
            request.setAttribute("wrongAction", action + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}

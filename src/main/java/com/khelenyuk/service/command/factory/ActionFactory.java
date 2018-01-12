package com.khelenyuk.service.command.factory;

import com.khelenyuk.service.command.ActionCommand;
import com.khelenyuk.service.command.client.CommandEnum;
import com.khelenyuk.service.command.commands.EmptyCommand;
import com.khelenyuk.controller.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {
    public ActionCommand defineCommand(HttpServletRequest request){
        ActionCommand current =  new EmptyCommand();
        String action = request.getParameter("command");
        if(action == null || action.isEmpty()){
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

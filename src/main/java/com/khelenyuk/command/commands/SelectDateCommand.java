package com.khelenyuk.command.commands;

import com.khelenyuk.command.ActionCommand;
import com.khelenyuk.entity.Meal;
import com.khelenyuk.service.MenuLogic;
import com.khelenyuk.service.PageLogic;
import com.khelenyuk.servlet.ConfigurationManager;
import com.khelenyuk.servlet.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SelectDateCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(SelectDateCommand.class);

    private static final String PARAM_NAME_SELECTED_DATE = "chosenDate";


    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String page = ConfigurationManager.getProperty("path.page.main");

        try {
            Date selectedDate = new SimpleDateFormat("yyyy-MM-dd").parse(
                    request.getParameter(PARAM_NAME_SELECTED_DATE).toString());
            logger.debug("Selected date is: " + selectedDate.toString());
        } catch (ParseException e) {
            logger.error("Parse selected date exception", e.getCause());
        }


//        if (MenuLogic.addMeal(meal)) {
//            PageLogic.updatePageData(session, meal.getUserId());
//        } else {
//            session.setAttribute("errorAddMealMessage", MessageManager.getProperty("message.addmealerror"));
//        }

        return page;
    }

}

package com.khelenyuk.service;


import com.khelenyuk.entity.MealFull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;

public class PageLogic {
    private static final Logger logger = LogManager.getLogger(LoginRegistrationLogic.class);




    public static void updatePageData(HttpSession session, int userId){
        /**
         * writes updated user(with updated info) into session
         */
        session.setAttribute("user", UserLogic.getUser(userId));

        /**
         * writes updated list of products into session
         */
        session.setAttribute("products", ProductLogic.getAllProducts());

        /**
         * writes chosenDate to session. If attribute is not set then
         * setting it to now() value.
         */
        LocalDate chosenDate = (LocalDate)session.getAttribute("chosenDateSession");
        if(chosenDate == null){
            chosenDate = LocalDate.now();
            session.setAttribute("chosenDateSession", chosenDate);
            logger.debug("Settting chosenDate to session: " + session.getAttribute("chosenDateSession"));
        }

        /**
         * writes updated menu,
         * totals(menu weight, calories, proteins, fats, carbs)
         * into session
         */
        List<MealFull> menu = MenuLogic.getUserMenu(userId, chosenDate);
        session.setAttribute("menu", menu);
        session.setAttribute("userTotalWeight", MenuLogic.getTotalWeight(menu));
        session.setAttribute("userTotalCalories", MenuLogic.getTotalCalories(menu));
        session.setAttribute("userTotalProteins", MenuLogic.getTotalProteins(menu));
        session.setAttribute("userTotalFat", MenuLogic.getTotalFat(menu));
        session.setAttribute("userTotalCarbs", MenuLogic.getTotalCarbs(menu));



    }


}

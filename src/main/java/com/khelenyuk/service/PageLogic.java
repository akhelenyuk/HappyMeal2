package com.khelenyuk.service;


import com.khelenyuk.entity.MealFull;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PageLogic {



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
         * writes updated menu,
         * totals(menu weight, calories, proteins, fats, carbs)
         * into session
         */
        List<MealFull> menu = MenuLogic.getUserMenu(userId);
        session.setAttribute("menu", menu);
        session.setAttribute("userTotalWeight", MenuLogic.getTotalWeight(menu));
        session.setAttribute("userTotalCalories", MenuLogic.getTotalCalories(menu));
        session.setAttribute("userTotalProteins", MenuLogic.getTotalProteins(menu));
        session.setAttribute("userTotalFat", MenuLogic.getTotalFat(menu));
        session.setAttribute("userTotalCarbs", MenuLogic.getTotalCarbs(menu));


    }


}

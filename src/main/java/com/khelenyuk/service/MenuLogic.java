package com.khelenyuk.service;


import com.khelenyuk.dao.mysql.MealDAO;
import com.khelenyuk.entity.Meal;
import com.khelenyuk.entity.MealFull;

import java.text.DecimalFormat;
import java.util.List;

public class MenuLogic {
    private static final MealDAO mealDAO = new MealDAO();

    public static boolean addMeal(Meal meal) {
        return mealDAO.add(meal);
    }

    public static List<MealFull> getUserMenu(int id) {
        return mealDAO.getMenu(id);
    }

//    public static void updateMenu(HttpSession session, int userId){
//        List<MealFull> menu = MenuLogic.getUserMenu(userId);
//        session.setAttribute("menu", menu);
//        session.setAttribute("userTotalWeight", MenuLogic.getTotalWeight(menu));
//        session.setAttribute("userTotalCalories", MenuLogic.getTotalCalories(menu));
//        session.setAttribute("userTotalProteins", MenuLogic.getTotalProteins(menu));
//        session.setAttribute("userTotalFat", MenuLogic.getTotalFat(menu));
//        session.setAttribute("userTotalCarbs", MenuLogic.getTotalCarbs(menu));
//    }


    public static int getTotalWeight(List<MealFull> menu) {
        int total = 0;
        for (MealFull meal: menu
             ) {
            total += meal.getWeight();
        }
        return total;
    }
    public static String getTotalCalories(List<MealFull> menu) {
        float total = 0.0f;
        for (MealFull meal: menu
                ) {
            total += meal.getCalories();
        }
        return new DecimalFormat(".##").format(total);
    }
    public static String getTotalProteins(List<MealFull> menu) {
        float total = 0.0f;
        for (MealFull meal: menu
                ) {
            total += meal.getProtein();
        }
        return new DecimalFormat(".##").format(total);
    }
    public static String getTotalFat(List<MealFull> menu) {
        float total = 0.0f;
        for (MealFull meal: menu
                ) {
            total += meal.getFat();
        }
        return new DecimalFormat(".##").format(total);
    }
    public static String getTotalCarbs(List<MealFull> menu) {
        float total = 0.0f;
        for (MealFull meal: menu
                ) {
            total += meal.getCarbs();
        }
        return new DecimalFormat(".##").format(total);
    }
}

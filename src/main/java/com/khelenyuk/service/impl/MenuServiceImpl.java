package com.khelenyuk.service.impl;


import com.khelenyuk.dao.mysql.MealDAO;
import com.khelenyuk.entity.Meal;
import com.khelenyuk.entity.MealFull;
import com.khelenyuk.service.IMenuService;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

public class MenuServiceImpl implements IMenuService {
    private static final MealDAO mealDAO = new MealDAO();
    private static MenuServiceImpl instance = new MenuServiceImpl();

    private MenuServiceImpl() {
    }

    public static MenuServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean addMeal(Meal meal) {
        return mealDAO.add(meal);
    }

    @Override
    public List<MealFull> getUserMenu(int id, LocalDate chosenDate) {
        return mealDAO.getMenu(id, chosenDate);
    }

    @Override

    public int getTotalWeight(List<MealFull> menu) {
        int total = 0;
        for (MealFull meal : menu
                ) {
            total += meal.getWeight();
        }
        return total;
    }

    @Override

    public String getTotalCalories(List<MealFull> menu) {
        float total = 0.0f;
        for (MealFull meal : menu
                ) {
            total += meal.getCalories();
        }
        return new DecimalFormat(".##").format(total);
    }

    @Override

    public String getTotalProteins(List<MealFull> menu) {
        float total = 0.0f;
        for (MealFull meal : menu
                ) {
            total += meal.getProtein();
        }
        return new DecimalFormat(".##").format(total);
    }

    @Override

    public String getTotalFat(List<MealFull> menu) {
        float total = 0.0f;
        for (MealFull meal : menu
                ) {
            total += meal.getFat();
        }
        return new DecimalFormat(".##").format(total);
    }

    @Override

    public String getTotalCarbs(List<MealFull> menu) {
        float total = 0.0f;
        for (MealFull meal : menu
                ) {
            total += meal.getCarbs();
        }
        return new DecimalFormat(".##").format(total);
    }
}

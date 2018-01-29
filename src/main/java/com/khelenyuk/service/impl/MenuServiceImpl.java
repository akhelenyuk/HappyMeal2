package com.khelenyuk.service.impl;


import com.khelenyuk.dao.impl.MealDiaryDaoImpl;
import com.khelenyuk.model.Meal;
import com.khelenyuk.model.MealToDisplay;
import com.khelenyuk.service.IMenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;

public class MenuServiceImpl implements IMenuService {
    private static final Logger logger = LogManager.getLogger(MenuServiceImpl.class);

    //todo rewrite to Interface
    private static MealDiaryDaoImpl mealDiaryDAO = new MealDiaryDaoImpl();
    private static MenuServiceImpl instance = new MenuServiceImpl();

    private MenuServiceImpl() {
    }

    public static MenuServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean addMeal(Meal meal) {
        return mealDiaryDAO.add(meal);
    }

    @Override
    public List<MealToDisplay> getUserMenu(int id, LocalDate chosenDate) {
        return mealDiaryDAO.getMenu(id, chosenDate);
    }

    @Override
    public int getTotalWeight(List<MealToDisplay> menu) {
        int total = 0;
        for (MealToDisplay meal : menu
                ) {
            total += meal.getWeight();
        }
        return total;
    }

    @Override
    public String getTotalCalories(List<MealToDisplay> menu) {
        float total = 0f;
        for (MealToDisplay meal : menu
                ) {
            total += meal.getCalories();
        }
        return new DecimalFormat("###").format(total);
    }

    @Override
    public String getTotalProteins(List<MealToDisplay> menu) {
        float total = 0.0f;
        for (MealToDisplay meal : menu
                ) {
            total += meal.getProtein();
        }
        return new DecimalFormat(".#").format(total);
    }

    @Override
    public String getTotalFat(List<MealToDisplay> menu) {
        float total = 0.0f;
        for (MealToDisplay meal : menu
                ) {
            total += meal.getFat();
        }
        return new DecimalFormat(".#").format(total);
    }

    @Override
    public String getTotalCarbs(List<MealToDisplay> menu) {
        float total = 0.0f;
        for (MealToDisplay meal : menu
                ) {
            total += meal.getCarbs();
        }
        return new DecimalFormat(".#").format(total);
    }

    @Override
    public MealToDisplay getTotalsByMealType(Integer userId, LocalDate date, Integer mealTypeId) {
        return mealDiaryDAO.getTotalsByMealType(userId, date, mealTypeId);
    }

    @Override
    public MealToDisplay getUserFoodTotal(Integer userId, LocalDate date) {
        return mealDiaryDAO.getTotals(userId, date);
    }

    @Override
    public boolean deleteFromFoodDiary(int id) {
        return mealDiaryDAO.delete(id);
    }

}

package com.khelenyuk.service;

import com.khelenyuk.model.Meal;
import com.khelenyuk.model.MealToDisplay;

import java.time.LocalDate;
import java.util.List;

public interface IMenuService {

    /**
     * Adds Meal to FoodDiary
     * @param meal
     * @return true -if success
     * false - if not added
     */
    boolean addMeal(Meal meal);

    /**
     * Gets FoodDiary of User for chosen Date
     * @param id
     * @param chosenDate
     * @return List of FoodDiary entries in display format
     */
    List<MealToDisplay> getUserMenu(int id, LocalDate chosenDate);

    /**
     * Gets total weight of Products in menu
     * @param menu
     * @return total weight
     */
    int getTotalWeight(List<MealToDisplay> menu);

    /**
     * Gets total calories of Products in menu
     * @param menu
     * @return total calories
     */

    String getTotalCalories(List<MealToDisplay> menu);

    /**
     * Gets total proteins of Products in menu
     * @param menu
     * @return total proteins
     */

    String getTotalProteins(List<MealToDisplay> menu);
    /**
     * Gets total fat of Products in menu
     * @param menu
     * @return total fat
     */

    String getTotalFat(List<MealToDisplay> menu);
    /**
     * Gets total carbs of Products in menu
     * @param menu
     * @return total carbs
     */
    String getTotalCarbs(List<MealToDisplay> menu);

    /**
     * Gets FoodDiary totals of User for chosen Date by MealType
     * @param userId
     * @param date
     * @param mealTypeId
     * @return MealToDisplay (totals by MealType) in display format
     */
    MealToDisplay getTotalsByMealType(Integer userId, LocalDate date, Integer mealTypeId);

    /**
     * Gets FoodDiary totals of User for chosen Date
     * @param userId
     * @param date
     * @return MealToDisplay (totals) in display format
     */
    MealToDisplay getUserFoodTotal(Integer userId, LocalDate date);

    /**
     * Deletes entry from FoodDiary
     * @param id
     * @return true - if success, false if fail
     */
    boolean deleteFromFoodDiary(int id);
}

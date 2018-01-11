package com.khelenyuk.service;

import com.khelenyuk.entity.Meal;
import com.khelenyuk.entity.MealFull;

import java.time.LocalDate;
import java.util.List;

public interface IMenuService {
    public boolean addMeal(Meal meal);

    public List<MealFull> getUserMenu(int id, LocalDate chosenDate);

    public int getTotalWeight(List<MealFull> menu);

    public String getTotalCalories(List<MealFull> menu);

    public String getTotalProteins(List<MealFull> menu);

    public String getTotalFat(List<MealFull> menu);

    public String getTotalCarbs(List<MealFull> menu);
}

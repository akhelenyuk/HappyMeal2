package com.khelenyuk.service;

import com.khelenyuk.model.Meal;
import com.khelenyuk.model.MealFull;

import java.time.LocalDate;
import java.util.List;

public interface IMenuService {
     boolean addMeal(Meal meal);

     List<MealFull> getUserMenu(int id, LocalDate chosenDate);

     int getTotalWeight(List<MealFull> menu);

     String getTotalCalories(List<MealFull> menu);

     String getTotalProteins(List<MealFull> menu);

     String getTotalFat(List<MealFull> menu);

     String getTotalCarbs(List<MealFull> menu);
}

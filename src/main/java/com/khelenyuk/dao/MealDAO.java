package com.khelenyuk.dao;

import com.khelenyuk.model.Meal;
import com.khelenyuk.model.MealToDisplay;

import java.time.LocalDate;
import java.util.List;

public interface MealDAO extends CrudDAO<Meal> {
    MealToDisplay getTotalsByMealType(Integer userId, LocalDate date, Integer mealTypeId);
}

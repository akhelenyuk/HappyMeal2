package com.khelenyuk.model;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class is used to show meal entries and meal TOTAL values to user.
 * It contains:
 * mealType name and product name instead of their id;
 * calories, protein, fat and carbs of the product according to its weight (not for 100 grams)
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MealToDisplay implements Serializable {
    private Integer id;
    private String mealType;
    private String product;
    private Float weight;
    private Integer calories;
    private Float protein;
    private Float fat;
    private Float carbs;

    // constructor for displaying totals
    public MealToDisplay(Float weight, Integer calories, Float protein, Float fat, Float carbs) {
        this.weight = weight;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

}

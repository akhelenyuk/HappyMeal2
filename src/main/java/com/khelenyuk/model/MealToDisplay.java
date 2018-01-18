package com.khelenyuk.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class is used to show meal entries and meal TOTAL values to user.
 * It contains:
 * mealType name and product name instead of their id;
 * calories, protein, fat and carbs of the product according to its weight (not for 100 grams)
 */
public class MealToDisplay implements Serializable {
    private Integer id;
    private String mealType;
    private String product;
    private Integer weight;
    private Float calories;
    private Float protein;
    private Float fat;
    private Float carbs;

    public MealToDisplay() {
    }

    // constructor for displaying totals
    public MealToDisplay(Integer weight, Float calories, Float protein, Float fat, Float carbs) {
        this.weight = weight;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    public MealToDisplay(String mealNumber, String product, Integer weight, Float calories, Float protein, Float fat, Float carbs) {
        this.mealType = mealNumber;
        this.product = product;
        this.weight = weight;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    public String getMealType() {
        return mealType;
    }

    public String getProduct() {
        return product;
    }

    public Integer getWeight() {
        return weight;
    }

    public Float getCalories() {
        return calories;
    }

    public Float getProtein() {
        return protein;
    }

    public Float getFat() {
        return fat;
    }

    public Float getCarbs() {
        return carbs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealToDisplay mealToDisplay = (MealToDisplay) o;
        return Objects.equals(id, mealToDisplay.id) &&
                Objects.equals(mealType, mealToDisplay.mealType) &&
                Objects.equals(product, mealToDisplay.product) &&
                Objects.equals(weight, mealToDisplay.weight) &&
                Objects.equals(calories, mealToDisplay.calories) &&
                Objects.equals(protein, mealToDisplay.protein) &&
                Objects.equals(fat, mealToDisplay.fat) &&
                Objects.equals(carbs, mealToDisplay.carbs);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, mealType, product, weight, calories, protein, fat, carbs);
    }

    @Override
    public String toString() {
        return "MealToDisplay{" +
                "id=" + id +
                ", mealType='" + mealType + '\'' +
                ", product='" + product + '\'' +
                ", weight=" + weight +
                ", calories=" + calories +
                ", protein=" + protein +
                ", fat=" + fat +
                ", carbs=" + carbs +
                '}';
    }
}

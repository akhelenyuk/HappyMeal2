package com.khelenyuk.model;

import java.io.Serializable;
import java.util.Objects;

public class MealFull implements Serializable {
    private int id;
    private String mealNumber;
    private String product;
    private int weight;
    private float calories;
    private float protein;
    private float fat;
    private float carbs;

    public MealFull() {
    }

    // constructor for displaying totals
    public MealFull(int weight, float calories, float protein, float fat, float carbs) {
        this.weight = weight;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    public MealFull(String mealNumber, String product, int weight, float calories, float protein, float fat, float carbs) {
        this.mealNumber = mealNumber;
        this.product = product;
        this.weight = weight;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    public String getMealNumber() {
        return mealNumber;
    }

    public String getProduct() {
        return product;
    }

    public int getWeight() {
        return weight;
    }

    public float getCalories() {
        return calories;
    }

    public float getProtein() {
        return protein;
    }

    public float getFat() {
        return fat;
    }

    public float getCarbs() {
        return carbs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealFull mealFull = (MealFull) o;
        return id == mealFull.id &&
                weight == mealFull.weight &&
                Float.compare(mealFull.calories, calories) == 0 &&
                Float.compare(mealFull.protein, protein) == 0 &&
                Float.compare(mealFull.fat, fat) == 0 &&
                Float.compare(mealFull.carbs, carbs) == 0 &&
                Objects.equals(mealNumber, mealFull.mealNumber) &&
                Objects.equals(product, mealFull.product);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, mealNumber, product, weight, calories, protein, fat, carbs);
    }

    @Override
    public String toString() {
        return "MealFull{" +
                "id=" + id +
                ", mealNumber='" + mealNumber + '\'' +
                ", product='" + product + '\'' +
                ", weight=" + weight +
                ", calories=" + calories +
                ", protein=" + protein +
                ", fat=" + fat +
                ", carbs=" + carbs +
                '}';
    }
}

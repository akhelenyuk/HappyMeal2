package com.khelenyuk.entity;

import java.io.Serializable;
import java.util.Objects;

public class Meal implements Serializable{
    private int id;
    private int userId;
//    private Date date;
    private int productId;
    private int weight;
    private MealNumber mealNumber;

    public Meal(int userId, int productId, int weight, int mealNumber) {
        this.userId = userId;
        this.productId = productId;
        this.weight = weight;
        this.mealNumber = new MealNumber(mealNumber);
    }

    public int getUserId() {
        return userId;
    }

    public int getProductId() {
        return productId;
    }

    public int getWeight() {
        return weight;
    }

    public MealNumber getMealNumber() {
        return mealNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return id == meal.id &&
                userId == meal.userId &&
                productId == meal.productId &&
                weight == meal.weight &&
                Objects.equals(mealNumber, meal.mealNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, productId, weight, mealNumber);
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", userId=" + userId +
                ", productId=" + productId +
                ", weight=" + weight +
                ", mealNumber=" + mealNumber +
                '}';
    }
}

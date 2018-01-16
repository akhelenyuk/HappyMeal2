package com.khelenyuk.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

public class Meal implements Serializable {
    private Integer id;
    private Integer userId;
    private LocalDate date;
    private Integer productId;
    private Integer weight;
    private MealNumber mealNumber;


    public Meal() {
    }

    public Meal(Integer userId, LocalDate date, Integer productId, Integer weight, Integer mealNumber) {
        this.userId = userId;
        this.date = date;
        this.productId = productId;
        this.weight = weight;
        this.mealNumber = new MealNumber(mealNumber);
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public Integer getWeight() {
        return weight;
    }

    public MealNumber getMealNumber() {
        return mealNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return Objects.equals(id, meal.id) &&
                Objects.equals(userId, meal.userId) &&
                Objects.equals(date, meal.date) &&
                Objects.equals(productId, meal.productId) &&
                Objects.equals(weight, meal.weight) &&
                Objects.equals(mealNumber, meal.mealNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, date, productId, weight, mealNumber);
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", userId=" + userId +
                ", date=" + date +
                ", productId=" + productId +
                ", weight=" + weight +
                ", mealNumber=" + mealNumber +
                '}';
    }
}

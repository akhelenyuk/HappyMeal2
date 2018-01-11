package com.khelenyuk.model;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {
    private int id;
    private String name;
    private Float calories;
    private Float protein;
    private Float fat;
    private Float carbs;

    public Product() {
    }

    public Product(String name, Float calories, Float protein, Float fat, Float carbs) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }

    public Product(int id, String name, Float calories, Float protein, Float fat, Float carbs) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }


    public String getName() {
        return name;
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(Float calories) {
        this.calories = calories;
    }

    public void setProtein(Float protein) {
        this.protein = protein;
    }

    public void setFat(Float fat) {
        this.fat = fat;
    }

    public void setCarbs(Float carbs) {
        this.carbs = carbs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                Objects.equals(name, product.name) &&
                Objects.equals(calories, product.calories) &&
                Objects.equals(protein, product.protein) &&
                Objects.equals(fat, product.fat) &&
                Objects.equals(carbs, product.carbs);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, calories, protein, fat, carbs);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", calories=" + calories +
                ", protein=" + protein +
                ", fat=" + fat +
                ", carbs=" + carbs +
                '}';
    }
}

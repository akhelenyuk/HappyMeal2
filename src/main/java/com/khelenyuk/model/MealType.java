package com.khelenyuk.model;

import java.io.Serializable;
import java.util.Objects;

public class MealType implements Serializable {
    private int id;
    private String name;


    public MealType() {
    }

    public MealType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public MealType(Integer mealTypeId) {

    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MealType that = (MealType) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "MealType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

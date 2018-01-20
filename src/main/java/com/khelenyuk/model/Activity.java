package com.khelenyuk.model;

import java.io.Serializable;
import java.util.Objects;

public class Activity implements Serializable {
    private Integer id;
    private String name;
    private Integer calories;

    public Activity() {
    }

    public Activity(String name, Integer calories) {
        this.name = name;
        this.calories = calories;
    }

    public Activity(Integer id, String name, Integer calories) {
        this.id = id;
        this.name = name;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Objects.equals(id, activity.id) &&
                Objects.equals(name, activity.name) &&
                Objects.equals(calories, activity.calories);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, calories);
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", calories=" + calories +
                '}';
    }
}

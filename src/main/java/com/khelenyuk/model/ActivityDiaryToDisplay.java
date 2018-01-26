package com.khelenyuk.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class ActivityDiaryToDisplay implements Serializable {
    private Integer id;
    private String activity;
    private Integer timeSpent;
    private Integer calories;

    public ActivityDiaryToDisplay() {
    }

    public ActivityDiaryToDisplay(Integer id, String activity, Integer timeSpent, Integer calories) {
        this.id = id;
        this.activity = activity;
        this.timeSpent = timeSpent;
        this.calories = calories;
    }

    public String getActivity() {
        return activity;
    }

    public Integer getTimeSpent() {
        return timeSpent;
    }

    public Integer getCalories() {
        return calories;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityDiaryToDisplay that = (ActivityDiaryToDisplay) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(activity, that.activity) &&
                Objects.equals(timeSpent, that.timeSpent) &&
                Objects.equals(calories, that.calories);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, activity, timeSpent, calories);
    }

    @Override
    public String toString() {
        return "ActivityDiaryToDisplay{" +
                "id=" + id +
                ", activity='" + activity + '\'' +
                ", timeSpent=" + timeSpent +
                ", calories=" + calories +
                '}';
    }
}

package com.khelenyuk.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class ActivityDiary implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer activityId;
    private Integer timeSpent;
    private LocalDate date;

    public ActivityDiary() {
    }

    public ActivityDiary(Integer id, Integer userId, Integer activityId, Integer timeSpent, LocalDate date) {
        this.id = id;
        this.userId = userId;
        this.activityId = activityId;
        this.timeSpent = timeSpent;
        this.date = date;
    }

    public ActivityDiary(Integer userId, Integer activityId, Integer timeSpent, LocalDate date) {
        this.userId = userId;
        this.activityId = activityId;
        this.timeSpent = timeSpent;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Integer timeSpent) {
        this.timeSpent = timeSpent;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityDiary that = (ActivityDiary) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(activityId, that.activityId) &&
                Objects.equals(timeSpent, that.timeSpent) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, activityId, timeSpent, date);
    }

    @Override
    public String toString() {
        return "ActivityDiary{" +
                "id=" + id +
                ", userId=" + userId +
                ", activityId=" + activityId +
                ", timeSpent=" + timeSpent +
                ", date=" + date +
                '}';
    }
}

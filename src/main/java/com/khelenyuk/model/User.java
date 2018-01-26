package com.khelenyuk.model;

import com.khelenyuk.utils.UtilManager;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class User implements Serializable {
    private Integer id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthday;
    private Integer genderId;
    private Integer weight;
    private Integer goalWeight;
    private Integer height;
    private Integer lifestyleId;

    //    TODO need method to calculate this value
    private Integer calorieNorm = 2345;
    private Integer roleId;
    private Integer statusId;


    public User() {
    }

    // constructor for inserting new User to database
    public User(String login, String password, String firstName, String lastName, String email, LocalDate birthday, Integer genderId, Integer weight, Integer goalWeight, Integer height, Integer lifestyleId) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.genderId = genderId;
        this.weight = weight;
        this.goalWeight = goalWeight;
        this.height = height;
        this.lifestyleId = lifestyleId;
    }

    // constructor for getting User from database
    public User(Integer id, String login, String password, String firstName, String lastName, String email, LocalDate birthday, Integer genderId, Integer weight, Integer goalWeight, Integer height, Integer lifestyleId, Integer calorieNorm, Integer roleId, Integer statusId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthday = birthday;
        this.genderId = genderId;
        this.weight = weight;
        this.goalWeight = goalWeight;
        this.height = height;
        this.lifestyleId = lifestyleId;
        this.calorieNorm = calorieNorm;
        this.roleId = roleId;
        this.statusId = statusId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Integer getGenderId() {
        return genderId;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public Integer getLifestyleId() {
        return lifestyleId;
    }

    public Integer getCalorieNorm() {
        return calorieNorm;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getGoalWeight() {
        return goalWeight;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public void setGoalWeight(Integer goalWeight) {
        this.goalWeight = goalWeight;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public void setLifestyleId(Integer lifestyleId) {
        this.lifestyleId = lifestyleId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public boolean isAdmin() {
        return UtilManager.getProperty("role.admin").equalsIgnoreCase(String.valueOf(roleId));
    }


    public Integer getId() {
        return id;
    }

    public void setCalorieNorm(int calorieNorm) {
        this.calorieNorm = calorieNorm;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(genderId, user.genderId) &&
                Objects.equals(weight, user.weight) &&
                Objects.equals(goalWeight, user.goalWeight) &&
                Objects.equals(height, user.height) &&
                Objects.equals(lifestyleId, user.lifestyleId) &&
                Objects.equals(calorieNorm, user.calorieNorm) &&
                Objects.equals(roleId, user.roleId) &&
                Objects.equals(statusId, user.statusId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, login, password, firstName, lastName, email, birthday, genderId, weight, goalWeight, height, lifestyleId, calorieNorm, roleId, statusId);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", genderId=" + genderId +
                ", weight=" + weight +
                ", goalWeight=" + goalWeight +
                ", height=" + height +
                ", lifestyleId=" + lifestyleId +
                ", calorieNorm=" + calorieNorm +
                ", roleId=" + roleId +
                ", statusId=" + statusId +
                '}';
    }
}



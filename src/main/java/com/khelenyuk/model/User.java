package com.khelenyuk.model;

import com.khelenyuk.utils.UtilManager;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;
    private Date birthday;
    private int sexId;
    private int weight;
    private int height;
    private int lifestyleId;
    private int calorieNorm;
    // Переписать под class Role
    private int roleId;

    // ---------------------------Test methods -- to delete---------------------------
    public User getUser() {
        return new com.khelenyuk.dao.mysql.impl.UserDAOImpl().get(9);
    }

    public List<User> getUsers() {
        return new com.khelenyuk.dao.mysql.impl.UserDAOImpl().getAll();
    }
    // ---------------------------------------------------------


    public User() {
    }

    public User(String firstName, String lastName, String login, String password, String email, Date birthday, int sexId, int weight, int height, int lifestyleId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.sexId = sexId;
        this.weight = weight;
        this.height = height;
        this.lifestyleId = lifestyleId;
    }

    public User(int id, String login, String password, String first_name, String last_name, String email, Date birthday, int sex, int weight, int height, int lifestyleId, int calorie_norm, int roleId) {

        this.id = id;
        this.login = login;
        this.password = password;
        firstName = first_name;
        lastName = last_name;
        this.email = email;
        this.birthday = birthday;
        this.sexId = sex;
        this.weight = weight;
        this.height = height;
        this.lifestyleId = lifestyleId;
        calorieNorm = calorie_norm;
        this.roleId = roleId;
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

    public Date getBirthday() {
        return birthday;
    }

    public int getSexId() {
        return sexId;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public int getRoleId() {
        return roleId;
    }

    public int getLifestyleId() {
        return lifestyleId;
    }

    public int getCalorieNorm() {
        return calorieNorm;
    }

    // Переписать под class Role
    public boolean isAdmin() {
        return UtilManager.getProperty("role.admin").equalsIgnoreCase(String.valueOf(roleId));
    }


    public int getId() {
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
        return id == user.id &&
                sexId == user.sexId &&
                weight == user.weight &&
                height == user.height &&
                lifestyleId == user.lifestyleId &&
                calorieNorm == user.calorieNorm &&
                roleId == user.roleId &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(email, user.email) &&
                Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, lastName, login, password, email, birthday, sexId, weight, height, lifestyleId, calorieNorm, roleId);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", sexId=" + sexId +
                ", weight=" + weight +
                ", height=" + height +
                ", lifestyleId=" + lifestyleId +
                ", calorieNorm=" + calorieNorm +
                ", roleId=" + roleId +
                '}';
    }
}



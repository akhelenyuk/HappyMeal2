package com.khelenyuk.model;

import com.khelenyuk.utils.UtilManager;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
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
    private Integer calorieNorm;
    private Integer roleId;
    private Integer statusId;


    /**
     *     constructor for inserting new User to database
     */
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

    /**
     *     used on jsp page to check whether to show 'admin' button on main page
     */
    public boolean isAdmin() {
        return UtilManager.getProperty("role.admin").equalsIgnoreCase(String.valueOf(roleId));
    }

}



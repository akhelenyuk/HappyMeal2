package com.khelenyuk.model;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Activity implements Serializable {
    private Integer id;
    private String name;
    private Integer calories;


    /**
     *    Constructor is needed to create Activity on jsp page.
     *    'Id' field will be assigned by db.
     */
    public Activity(String name, Integer calories) {
        this.name = name;
        this.calories = calories;
    }



}

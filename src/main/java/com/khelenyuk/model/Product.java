package com.khelenyuk.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class Product implements Serializable {
    private int id;
    private String name;
    private Integer calories;
    private Float protein;
    private Float fat;
    private Float carbs;

    /**
     *    Constructor is needed to create Product from jsp.
     *    'Id' field will be assigned by db.
     */
    public Product(String name, Integer calories, Float protein, Float fat, Float carbs) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
    }
}

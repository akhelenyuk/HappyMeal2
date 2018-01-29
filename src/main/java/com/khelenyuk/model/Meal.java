package com.khelenyuk.model;

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

public class Meal implements Serializable {
    private Integer id;
    private Integer userId;
    private LocalDate date;
    private Integer productId;
    private Integer weight;
    private Integer mealTypeId;

    /**
     *    Constructor is needed to create 'entry' of 'Food diary' on jsp page.
     *    'Id' field will be assigned by db.
     */
    public Meal(Integer userId, LocalDate date, Integer productId, Integer weight, Integer mealTypeId) {
        this.userId = userId;
        this.date = date;
        this.productId = productId;
        this.weight = weight;
        this.mealTypeId = mealTypeId;
    }

}

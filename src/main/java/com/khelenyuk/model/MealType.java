package com.khelenyuk.model;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;


/**
 *    MealTypes: breakfast, lunch, dinner etc.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class MealType implements Serializable {
    private int id;
    private String name;
}

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

public class Lifestyle implements Serializable{
    private int id;
    private String name;

}

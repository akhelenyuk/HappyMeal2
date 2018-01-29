package com.khelenyuk.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString

public class Gender implements Serializable {
    private int id;
    private String name;
}

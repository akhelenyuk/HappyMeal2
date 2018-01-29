package com.khelenyuk.model;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


/**
 *    Class is used to show 'entries' and 'totals'
 *    of ActivityDiary on jsp page.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class ActivityDiaryToDisplay implements Serializable {
    private Integer id;
    private String activity;
    private Integer timeSpent;
    private Integer calories;
}

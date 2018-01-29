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

public class ActivityDiary implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer activityId;
    private Integer timeSpent;
    private LocalDate date;

    /**
     *    Constructor is needed to create Entry of Activity diary on jsp page.
     *    'Id' field will be assigned by db.
     */
    public ActivityDiary(Integer userId, Integer activityId, Integer timeSpent, LocalDate date) {
        this.userId = userId;
        this.activityId = activityId;
        this.timeSpent = timeSpent;
        this.date = date;
    }
}

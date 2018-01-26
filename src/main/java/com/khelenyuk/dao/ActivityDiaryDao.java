package com.khelenyuk.dao;

import com.khelenyuk.model.ActivityDiary;
import com.khelenyuk.model.ActivityDiaryToDisplay;

import java.time.LocalDate;
import java.util.List;

public interface ActivityDiaryDao extends CrudDao<ActivityDiary> {
    List<ActivityDiaryToDisplay> getAll(Integer userId, LocalDate chosenDate);

    ActivityDiaryToDisplay getTotals(Integer userId, LocalDate chosenDate);
}

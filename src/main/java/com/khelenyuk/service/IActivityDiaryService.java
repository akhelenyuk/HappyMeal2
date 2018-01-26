package com.khelenyuk.service;

import com.khelenyuk.model.ActivityDiary;
import com.khelenyuk.model.ActivityDiaryToDisplay;

import java.time.LocalDate;
import java.util.List;

public interface IActivityDiaryService {
    boolean addActivityToDiary (ActivityDiary activityEntry);

    List<ActivityDiaryToDisplay> getUserActivityDiary(Integer userId, LocalDate chosenDate);

    ActivityDiaryToDisplay  getUserActivityDiaryTotals(Integer userId, LocalDate chosenDate);
}

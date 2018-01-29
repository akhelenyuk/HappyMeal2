package com.khelenyuk.service;

import com.khelenyuk.model.ActivityDiary;
import com.khelenyuk.model.ActivityDiaryToDisplay;

import java.time.LocalDate;
import java.util.List;

public interface IActivityDiaryService {
    /**
     * Adds Activity to Diary
     * @param activityEntry
     * @return true -if success
     * false - if not added
     */
    boolean addActivityToDiary (ActivityDiary activityEntry);

    /**
     * Gets Activity Diary of User for chosen Date
     * @param userId
     * @param chosenDate
     * @return List of ActivityDiary entries in display format
     */
    List<ActivityDiaryToDisplay> getUserActivityDiary(Integer userId, LocalDate chosenDate);

    /**
     *  Gets Activity Diary totals of User for chosen Date
     * @param userId
     * @param chosenDate
     * @return ActivityDiary (totals) in display format
     */
    ActivityDiaryToDisplay getUserActivityDiaryTotals(Integer userId, LocalDate chosenDate);


    /**
     * Deletes entry from Activity Diary
     * @param id
     * @return true - if success, false if fail
     */
    boolean deleteFromActivityDiary(int id);
}

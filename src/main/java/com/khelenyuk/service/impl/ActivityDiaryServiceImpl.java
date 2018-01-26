package com.khelenyuk.service.impl;


import com.khelenyuk.service.IActivityDiaryService;
import com.khelenyuk.dao.ActivityDiaryDao;
import com.khelenyuk.dao.factory.DaoFactory;
import com.khelenyuk.model.ActivityDiary;
import com.khelenyuk.model.ActivityDiaryToDisplay;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;


public class ActivityDiaryServiceImpl implements IActivityDiaryService {
    private static final Logger logger = LogManager.getLogger(ActivityDiaryServiceImpl.class);
    private static IActivityDiaryService instance = new ActivityDiaryServiceImpl();

    private ActivityDiaryDao activityDiaryDao = DaoFactory.getActivityDiaryMethods();


    private ActivityDiaryServiceImpl() {
    }

    public static IActivityDiaryService getInstance() {
        return instance;
    }


    @Override
    public boolean addActivityToDiary(ActivityDiary activityEntry) {
        return activityDiaryDao.add(activityEntry);
    }

    @Override
    public List<ActivityDiaryToDisplay> getUserActivityDiary(Integer userId, LocalDate chosenDate) {
        return activityDiaryDao.getAll(userId, chosenDate);
    }

    @Override
    public ActivityDiaryToDisplay getUserActivityDiaryTotals(Integer userId, LocalDate chosenDate) {
        return activityDiaryDao.getTotals(userId, chosenDate);
    }

    @Override
    public boolean deleteFromActivityDiary(int id) {
        return activityDiaryDao.delete(id);
    }
}

package com.khelenyuk.controller.service.impl;


import com.khelenyuk.controller.service.IActivityDiaryService;
import com.khelenyuk.dao.ActivityDiaryDAO;
import com.khelenyuk.dao.mysql.factory.DAOFactory;
import com.khelenyuk.model.ActivityDiary;
import com.khelenyuk.model.ActivityDiaryToDisplay;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.List;


public class ActivityDiaryServiceImpl implements IActivityDiaryService {
    private static final Logger logger = LogManager.getLogger(ActivityDiaryServiceImpl.class);
    private static IActivityDiaryService instance = new ActivityDiaryServiceImpl();

    private ActivityDiaryDAO activityDiaryDAO = DAOFactory.getActivityDiaryMethods();


    private ActivityDiaryServiceImpl() {
    }

    public static IActivityDiaryService getInstance() {
        return instance;
    }


    @Override
    public boolean addActivityToDiary(ActivityDiary activityEntry) {
        return activityDiaryDAO.add(activityEntry);
    }

    @Override
    public List<ActivityDiaryToDisplay> getUserActivityDiary(Integer userId, LocalDate chosenDate) {
        return activityDiaryDAO.getAll(userId, chosenDate);
    }

    @Override
    public ActivityDiaryToDisplay getUserActivityDiaryTotals(Integer userId, LocalDate chosenDate) {
        return activityDiaryDAO.getTotals(userId, chosenDate);
    }
}

package com.khelenyuk.service.impl;

import com.khelenyuk.service.IActivityService;
import com.khelenyuk.dao.ActivityDao;
import com.khelenyuk.dao.factory.DaoFactory;
import com.khelenyuk.model.Activity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ActivityServiceImpl implements IActivityService {
    private static final Logger logger = LogManager.getLogger(ActivityServiceImpl.class);
    private static ActivityServiceImpl instance = new ActivityServiceImpl();

    private static ActivityDao activityDao = DaoFactory.getActivityMethods();



    private ActivityServiceImpl() {
    }

    public static ActivityServiceImpl getInstance() {
        return instance;
    }


    @Override
    public List<Activity> getAll() {
        return activityDao.getAll();
    }

    @Override
    public boolean checkActivityExist(String name) {
        logger.info("Check if activity with such name already exists");
        return activityDao.get(name) != null;
    }

    @Override
    public boolean addActivity(Activity newActivity) {
        return activityDao.add(newActivity);
    }
}

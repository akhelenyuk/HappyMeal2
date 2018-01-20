package com.khelenyuk.controller.service.impl;

import com.khelenyuk.controller.service.IActivityService;
import com.khelenyuk.dao.ActivityDAO;
import com.khelenyuk.dao.mysql.factory.DAOFactory;
import com.khelenyuk.model.Activity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ActivityServiceImpl implements IActivityService {
    private static final Logger logger = LogManager.getLogger(ActivityServiceImpl.class);
    private static ActivityServiceImpl instance = new ActivityServiceImpl();

    private static ActivityDAO activityDAO = DAOFactory.getActivityMethods();



    private ActivityServiceImpl() {
    }

    public static ActivityServiceImpl getInstance() {
        return instance;
    }


    @Override
    public List<Activity> getAll() {
        return activityDAO.getAll();
    }
}

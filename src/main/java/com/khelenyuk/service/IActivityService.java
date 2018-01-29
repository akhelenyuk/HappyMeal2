package com.khelenyuk.service;

import com.khelenyuk.model.Activity;

import java.util.List;

public interface IActivityService {
    /**
     * Gets all activities from db
     * @return List of Activity
     */
    List<Activity> getAll();

    /**
     * Checks if Activity exist
     * @param name
     * @return true if name exist
     * false if name not exist
     */
    boolean checkActivityExist(String name);

    /**
     * Adds new Activty to db
     * @param newActivity
     * @return true - if success, false - if not added
     */
    boolean addActivity(Activity newActivity);
}

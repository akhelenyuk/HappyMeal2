package com.khelenyuk.service;

import com.khelenyuk.model.Activity;

import java.util.List;

public interface IActivityService {
    List<Activity> getAll();

    boolean checkActivityExist(String name);

    boolean addActivity(Activity newActivity);
}

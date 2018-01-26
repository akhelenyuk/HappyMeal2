package com.khelenyuk.dao;

import com.khelenyuk.model.Activity;

public interface ActivityDao extends CrudDao<Activity> {
    Activity get(String name);
}

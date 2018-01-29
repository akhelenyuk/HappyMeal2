package com.khelenyuk.service;

import com.khelenyuk.model.MealType;

import java.util.List;

public interface IMealTypeService {
    /**
     * Gets all MealTypes from db
     * @return List of MealTypes
     */
    List<MealType> getAll();

}

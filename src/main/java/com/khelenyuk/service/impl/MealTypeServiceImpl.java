package com.khelenyuk.service.impl;

import com.khelenyuk.service.IMealTypeService;
import com.khelenyuk.dao.MealTypeDao;
import com.khelenyuk.dao.factory.DaoFactory;
import com.khelenyuk.model.MealType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;

public class MealTypeServiceImpl implements IMealTypeService {
    private static final Logger logger = LogManager.getLogger(MealTypeServiceImpl.class);
    private static MealTypeServiceImpl instance = new MealTypeServiceImpl();

    private static MealTypeDao mealTypeDAO = DaoFactory.getMealTypeMethods();



    private MealTypeServiceImpl() {
    }

    public static MealTypeServiceImpl getInstance() {
        return instance;
    }


    @Override
    public List<MealType> getAll() {
        return mealTypeDAO.getAll();
    }
}

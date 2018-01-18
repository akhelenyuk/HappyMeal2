package com.khelenyuk.controller.service.impl;

import com.khelenyuk.controller.service.IMealTypeService;
import com.khelenyuk.dao.MealTypeDAO;
import com.khelenyuk.dao.mysql.factory.DAOFactory;
import com.khelenyuk.model.MealType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;

public class MealTypeServiceImpl implements IMealTypeService {
    private static final Logger logger = LogManager.getLogger(MealTypeServiceImpl.class);
    private static MealTypeServiceImpl instance = new MealTypeServiceImpl();

    private static MealTypeDAO mealTypeDAO = DAOFactory.getMealTypeMethods();



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

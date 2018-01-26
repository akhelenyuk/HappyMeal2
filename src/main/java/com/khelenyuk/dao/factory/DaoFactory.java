package com.khelenyuk.dao.factory;

import com.khelenyuk.dao.*;
import com.khelenyuk.dao.impl.*;
import com.khelenyuk.dao.impl.ProductDaoImpl;
import com.khelenyuk.dao.impl.UserDaoImpl;

public class DaoFactory {
    public static LifestyleDao getLifestyleMethods() {
        return new LifestyleDaoImpl();
    }

    public static UserDao getUserMethods() {
        return new UserDaoImpl();
    }

    public static ProductDao getProductMethods() {
        return new ProductDaoImpl();
    }

    public static MealDao getMealMethods() {
        return new MealDiaryDaoImpl();
    }

    public static GenderDao getGenderMethods() {
        return new GenderDaoImpl();
    }

    public static MealTypeDao getMealTypeMethods() {
        return new MealTypeDaoImpl();
    }

    public static ActivityDao getActivityMethods() {
        return new ActivityDaoImpl();
    }

    public static ActivityDiaryDao getActivityDiaryMethods() {
        return new ActivityDiaryDaoImpl();
    }
}

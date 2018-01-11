package com.khelenyuk.dao.mysql.factory;

import com.khelenyuk.dao.*;
import com.khelenyuk.dao.mysql.impl.*;

public class DAOFactory {
    public static LifestyleDAO getLifestyleMethods() {
        return new LifestyleDAOImpl();
    }

    public static UserDAO getUserMethods() {
        return new UserDAOImpl();
    }

    public static ProductDAO getProductMethods() {
        return new ProductDAOImpl();
    }

    public static MealDAO getMealMethods(){
        return new MealDAOImpl();
    }

    public static SexDAO getSexMethods(){
        return new SexDAOImpl();
    }
}

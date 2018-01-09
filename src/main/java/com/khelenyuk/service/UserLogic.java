package com.khelenyuk.service;


import com.khelenyuk.dao.EntityDAO;
import com.khelenyuk.dao.mysql.LifestyleDAOImpl;
import com.khelenyuk.dao.mysql.SexDAOImpl;
import com.khelenyuk.dao.mysql.UserDAOImpl;
import com.khelenyuk.entity.Lifestyle;
import com.khelenyuk.entity.Sex;
import com.khelenyuk.entity.User;

import java.util.List;

public class UserLogic {
    private static EntityDAO<User> userDAO = new UserDAOImpl();
    private static EntityDAO<Sex> sexDAO = new SexDAOImpl();
    private static EntityDAO<Lifestyle> lifestyleDAO = new LifestyleDAOImpl();

    public static User getUser(String login) {
        return userDAO.get(login);
    }

    public static User getUser(int id) {
        return userDAO.get(id);
    }

    public static List<Sex> getSex(){
        return sexDAO.getAll();
    }

    public static List<Lifestyle> getLifestyles() {
        return lifestyleDAO.getAll();
    }

    public static boolean addUser(User user) {
        return userDAO.add(user);
    }
}

package com.khelenyuk.service.impl;


import com.khelenyuk.dao.CrudDAO;
import com.khelenyuk.dao.mysql.LifestyleDAOImpl;
import com.khelenyuk.dao.mysql.SexDAOImpl;
import com.khelenyuk.dao.mysql.UserDAOImpl;
import com.khelenyuk.entity.Lifestyle;
import com.khelenyuk.entity.Sex;
import com.khelenyuk.entity.User;
import com.khelenyuk.service.IUserService;

import java.util.List;

public class UserServiceImpl implements IUserService {
    private static CrudDAO<User> userDAO = new UserDAOImpl();
    private static CrudDAO<Sex> sexDAO = new SexDAOImpl();
    private static CrudDAO<Lifestyle> lifestyleDAO = new LifestyleDAOImpl();
    private static UserServiceImpl instance = new UserServiceImpl();

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }

    @Override
    public User getUser(String login) {
        return userDAO.get(login);
    }

    @Override
    public User getUser(int id) {
        return userDAO.get(id);
    }

    @Override
    public List<Sex> getSex() {
        return sexDAO.getAll();
    }

    @Override
    public List<Lifestyle> getLifestyles() {
        return lifestyleDAO.getAll();
    }

    @Override
    public boolean addUser(User user) {
        return userDAO.add(user);
    }
}

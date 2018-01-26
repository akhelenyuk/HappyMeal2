package com.khelenyuk.service.impl;


import com.khelenyuk.dao.LifestyleDao;
import com.khelenyuk.dao.GenderDao;
import com.khelenyuk.dao.UserDao;
import com.khelenyuk.dao.factory.DaoFactory;
import com.khelenyuk.model.Lifestyle;
import com.khelenyuk.model.Gender;
import com.khelenyuk.model.User;
import com.khelenyuk.service.IUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements IUserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private static UserDao userDAO = DaoFactory.getUserMethods();
    private static GenderDao genderDAO = DaoFactory.getGenderMethods();
    private static LifestyleDao lifestyleDAO = DaoFactory.getLifestyleMethods();

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
    public List<User> getAll(){
        return userDAO.getAll();
    }

    @Override
    public User getUser(int id) {
        return userDAO.get(id);
    }

    @Override
    public List<Gender> getGenders() {
        return genderDAO.getAll();
    }

    @Override
    public List<Lifestyle> getLifestyles() {
        return lifestyleDAO.getAll();
    }

    @Override
    public boolean addUser(User user) {
        return userDAO.add(user);
    }

    @Override
    public boolean updateUser(User newUser) {
        return userDAO.update(newUser.getId(), newUser);
    }

    @Override
    public boolean blockUnblockUser(User user) {
        return userDAO.updateStatus(user);
    }

    @Override
    public List<User> getUsers(int limit, int offset) {
        return userDAO.getAll(limit, offset);
    }

    @Override
    public int getUsersCount() {
        return userDAO.getUsersCount();
    }
}

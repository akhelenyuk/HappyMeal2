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

import java.time.LocalDate;
import java.time.Period;
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
    public List<User> getAll() {
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
    public Gender getGender(Integer genderId) {
        return genderDAO.get(genderId);
    }

    @Override
    public Lifestyle getLifestyle(Integer lifestyleId) {
        return lifestyleDAO.get(lifestyleId);
    }

    @Override
    public List<Lifestyle> getLifestyles() {
        return lifestyleDAO.getAll();
    }

    @Override
    public boolean addUser(User user) {
        user.setCalorieNorm(this.calculateCalorieNorm(user));
        return userDAO.add(user);
    }

    @Override
    public boolean updateUser(User newUser) {
        newUser.setCalorieNorm(calculateCalorieNorm(newUser));
        return userDAO.update(newUser);
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

    /**
     * claculates Calorie norm based on given formula and parameters
      * @param user
     * @return calorie norm
     */
    public int calculateCalorieNorm(User user) {
        if (user == null) {
            return 0;
        }
        int calorieNorm = 0;
        Gender gender = this.getGender(user.getGenderId());
        Lifestyle lifestyle = this.getLifestyle(user.getLifestyleId());

        int age = Period.between(user.getBirthday(), LocalDate.now()).getYears();

        switch (gender.getName()) {
            case "male": {
                calorieNorm = 10 * user.getWeight() + (int) (6.25d * user.getHeight()) - 5 * age + 5 - 300;
                break;
            }
            case "female": {
                calorieNorm = 10 * user.getWeight() + (int) (6.25d * user.getHeight()) - 5 * age - 161 - 200;
                break;
            }
            default: {
                logger.error("No gender found.");
            }
        }

        switch (lifestyle.getName()) {
            case "active": {
                calorieNorm = (int) (calorieNorm * 1.4625d);
                break;
            }
            case "average": {
                calorieNorm = (int) (calorieNorm * 1.375d);
                break;
            }
            case "lazy": {
                calorieNorm = (int) (calorieNorm * 1.2d);
                break;
            }
            default: {
                logger.error("No lifestyle found.");
            }
        }
        return calorieNorm;

    }
}

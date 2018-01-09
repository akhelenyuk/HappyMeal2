package com.khelenyuk.service;

import com.khelenyuk.dao.EntityDAO;
import com.khelenyuk.dao.mysql.UserDAOImpl;
import com.khelenyuk.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginRegistrationLogic {
    private static final Logger logger = LogManager.getLogger(LoginRegistrationLogic.class);

    private static EntityDAO<User> userDAO = new UserDAOImpl();

    /**
     * checks login and password
     */
    public static boolean checkLogin(String login, String pass) {
        logger.debug("Get user with login=" + login);
        User user = userDAO.get(login);
        if (user == null) {
            logger.info("User was not found");
           return false;
        }
        logger.debug("check user's password");
        return user.getPassword().equals(pass);
    }

    /**
     * @param password - password from user
     * @param password_confrim - password confirmation from user
     * @return true if parameters are equal, else returns false
     */
    public static boolean confirmPassword(String password, String password_confrim) {
        logger.debug("Check confirmation password");
        return password.equals(password_confrim);
    }

    /**
     * check if login exists
     * @return true if login already exists, otherwise - false
     */
    public static boolean checkLoginExist(String login) {
        logger.debug("check if login exists");
        return userDAO.get(login) != null;
    }
}

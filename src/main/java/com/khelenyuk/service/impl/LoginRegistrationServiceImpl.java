package com.khelenyuk.service.impl;

import com.khelenyuk.dao.UserDao;
import com.khelenyuk.dao.factory.DaoFactory;
import com.khelenyuk.model.User;
import com.khelenyuk.service.ILoginRegistrationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginRegistrationServiceImpl implements ILoginRegistrationService {
    private static final Logger logger = LogManager.getLogger(LoginRegistrationServiceImpl.class);
    private static UserDao userDAO = DaoFactory.getUserMethods();
    private static LoginRegistrationServiceImpl instance = new LoginRegistrationServiceImpl();

    private LoginRegistrationServiceImpl() {
    }

    public static LoginRegistrationServiceImpl getInstance() {
        return instance;
    }

    /**
     * checks login and password
     */
    @Override
    public boolean checkLogin(String login, String pass) {
        User user = userDAO.get(login);
        if (user == null) {
            logger.info("User is null");
            return false;
        }
        return user.getPassword().equals(pass);
    }

    /**
     * @param password         - password from user
     * @param password_confrim - password confirmation from user
     * @return true if parameters are equal, else returns false
     */
    @Override
    public boolean confirmPassword(String password, String password_confrim) {
        logger.info("Check confirmation password");
        return password.equals(password_confrim);
    }

    /**
     * check if login exists in database
     * @return true if login already exists, otherwise - false
     */
    @Override
    public boolean checkLoginExist(String login) {
        logger.info("check if login exists");
        return userDAO.get(login) != null;
    }
}

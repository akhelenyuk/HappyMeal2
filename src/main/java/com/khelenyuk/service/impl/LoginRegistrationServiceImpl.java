package com.khelenyuk.service.impl;

import com.khelenyuk.dao.CrudDAO;
import com.khelenyuk.dao.UserDAO;
import com.khelenyuk.dao.mysql.factory.DAOFactory;
import com.khelenyuk.dao.mysql.impl.UserDAOImpl;
import com.khelenyuk.model.User;
import com.khelenyuk.service.ILoginRegistrationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginRegistrationServiceImpl implements ILoginRegistrationService {
    private static final Logger logger = LogManager.getLogger(LoginRegistrationServiceImpl.class);
    private static UserDAO userDAO = DAOFactory.getUserMethods();
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
     * @param password         - password from user
     * @param password_confrim - password confirmation from user
     * @return true if parameters are equal, else returns false
     */
    @Override
    public boolean confirmPassword(String password, String password_confrim) {
        logger.debug("Check confirmation password");
        return password.equals(password_confrim);
    }

    /**
     * check if login exists in database
     *
     * @return true if login already exists, otherwise - false
     */
    @Override
    public boolean checkLoginExist(String login) {
        logger.debug("check if login exists");
        return userDAO.get(login) != null;
    }
}

package service;

import dao.EntityDAO;
import dao.mysql.UserDAOImpl;
import entity.User;

import java.sql.SQLException;
import java.util.logging.Logger;

public class LoginRegistrationLogic {
    private static EntityDAO<User> userDAO = new UserDAOImpl();

    /**
     * checks login and password
     * @param login
     * @param pass
     * @return
     */
    public static boolean checkLogin(String login, String pass) {
        User user = userDAO.get(login);
        if (user == null) {
           return false;
        }
        return user.getPassword().equals(pass);
    }

    /**
     * @param password - password from user
     * @param password_confrim - password confirmation from user
     * @return true if parameters are equal, else returns false
     */
    public static boolean confirmPassword(String password, String password_confrim) {
        return password.equals(password_confrim);
    }

    /**
     * check if login exists
     * @returns true if login already exists, otherwise - false
     */
    public static boolean checkLoginExist(String login) {
        return userDAO.get(login) != null;
    }
}

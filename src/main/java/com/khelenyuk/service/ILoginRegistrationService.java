package com.khelenyuk.service;

public interface ILoginRegistrationService {
    /**
     * Checks login and password.
     * @param login
     * @param pass
     * @return true - if success, false - if one or both are not correct
     */
     boolean checkLogin(String login, String pass);

    /**
     * Checks if password and password_confrim equals
     * @param password
     * @param password_confrim
     * @return true - if equels, false - if not
     */
     boolean confirmPassword(String password, String password_confrim);

     /**
     * Checks login exist
     * @param login
     * @return true if login exist
     * false if login not exist
     */
     boolean checkLoginExist(String login);
}

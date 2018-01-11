package com.khelenyuk.service;

public interface ILoginRegistrationService {
     boolean checkLogin(String login, String pass);

     boolean confirmPassword(String password, String password_confrim);

     boolean checkLoginExist(String login);
}

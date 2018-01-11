package com.khelenyuk.service;

import com.khelenyuk.entity.User;

public interface ILoginRegistrationService {
    public boolean checkLogin(String login, String pass);

    public boolean confirmPassword(String password, String password_confrim);

    public boolean checkLoginExist(String login);
}

package com.khelenyuk.service;

import com.khelenyuk.model.Lifestyle;
import com.khelenyuk.model.Sex;
import com.khelenyuk.model.User;

import java.util.List;

public interface IUserService {
    User getUser(String login);

    User getUser(int id);

    List<Sex> getSex();

    List<Lifestyle> getLifestyles();

    boolean addUser(User user);
}

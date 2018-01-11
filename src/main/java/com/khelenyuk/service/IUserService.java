package com.khelenyuk.service;

import com.khelenyuk.entity.Lifestyle;
import com.khelenyuk.entity.Sex;
import com.khelenyuk.entity.User;

import java.util.List;

public interface IUserService {
    User getUser(String login);

    User getUser(int id);

    List<Sex> getSex();

    List<Lifestyle> getLifestyles();

    boolean addUser(User user);
}

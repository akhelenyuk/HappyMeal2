package com.khelenyuk.controller.service;

import com.khelenyuk.model.Lifestyle;
import com.khelenyuk.model.Gender;
import com.khelenyuk.model.User;

import java.util.List;

public interface IUserService {
    User getUser(String login);

    User getUser(int id);

    List<User> getAll();

    List<Gender> getGenders();

    List<Lifestyle> getLifestyles();

    boolean addUser(User user);

    boolean updateUser(User newUser);

    boolean blockUnblockUser(User user);
}

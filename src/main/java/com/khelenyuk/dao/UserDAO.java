package com.khelenyuk.dao;

import com.khelenyuk.model.User;

import java.util.List;

public interface UserDAO extends CrudDAO<User> {
    User get(String login);
}

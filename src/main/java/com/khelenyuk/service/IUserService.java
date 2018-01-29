package com.khelenyuk.service;

import com.khelenyuk.model.Lifestyle;
import com.khelenyuk.model.Gender;
import com.khelenyuk.model.User;

import java.util.List;

public interface IUserService {
    /**
     * Gets User from db by login
     * @param login
     * @return User
     */
    User getUser(String login);

    /**
     * Gets User from db by id
     * @param id
     * @return
     */
    User getUser(int id);

    /**
     * Gets all Users from db
     * @return List of Users
     */
    List<User> getAll();

    /**
     * Gets all Genders from db
     * @return List of Genders
     */
    List<Gender> getGenders();

    /**
     * Gets list of Lifestyles from db
     * @return List of Lifestyles
     */
    List<Lifestyle> getLifestyles();

    /**
     * Adds User to db
     * @param user
     * @return true - if User was added, false if not
     */
    boolean addUser(User user);

    /**
     * updates User in db
     * @param newUser
     * @return true - if updated, false if not
     */
    boolean updateUser(User newUser);

    /**
     * Blocks/unblocks user - change statusId
     * @param user
     * @return true - if status was changed, false- if not
     */
    boolean blockUnblockUser(User user);

    /**
     * Gets limit number of Users starting from offset value
     * @param limit
     * @param offset
     * @return List of Users
     */
    List<User> getUsers(int limit, int offset);

    /**
     * Gets number of users in db
     * @return number of Users
     */
    int getUsersCount();

    /**
     * Gets Gender by its id
     * @param genderId
     * @return Gender
     */
    Gender getGender(Integer genderId);

    /**
     * Gets Lifestyle by its id
     * @param lifestyleId
     * @return Lifestyle
     */
    Lifestyle getLifestyle(Integer lifestyleId);

}

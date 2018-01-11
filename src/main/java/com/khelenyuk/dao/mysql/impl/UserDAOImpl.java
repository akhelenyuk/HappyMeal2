package com.khelenyuk.dao.mysql.impl;

import com.khelenyuk.connection.ConnectionPool;
import com.khelenyuk.dao.CrudDAO;
import com.khelenyuk.dao.UserDAO;
import com.khelenyuk.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAOImpl extends CrudDaoImpl<User> implements UserDAO {
    private static final Logger logger = LogManager.getLogger(ProductDAOImpl.class);

    private final String TABLE = "users";
    private String selectAll = "SELECT * FROM " + TABLE;
    private String selectById = "SELECT * FROM " + TABLE + " WHERE id=?";
    private String selectByLogin = "SELECT * FROM " + TABLE + " WHERE login=?";
    private String insert = "INSERT INTO " + TABLE + "(`login`, `password`, `first_name`, `last_name`, `email`, `birthday`, `sex_id`, `weight`, `height`, `lifestyle_id`,  `calorie_norm`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private String updateById = "UPDATE " + TABLE + "" + " SET `login`, `password`, `first_name`=?, `last_name`=?, `email`=?, `birthday`=?, `sex_id`=?, `weight`=?, `height`=?, `lifestyle_id`=? WHERE id=?";
    private String deleteById = "DELETE FROM " + TABLE + " WHERE id=?";


    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAll);
             ResultSet resultSet = statement.executeQuery()
        ) {
            logger.debug("Query: " + statement.toString());
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getDate("birthday"),
                        resultSet.getInt("sex_id"),
                        resultSet.getInt("weight"),
                        resultSet.getInt("height"),
                        resultSet.getInt("lifestyle_id"),
                        resultSet.getInt("calorie_norm"),
                        resultSet.getInt("role_id")
                ));
            }
        } catch (SQLException e) {
            logger.error("Error in obtaining 'all users'" + e.getCause());
        }

        return users;
    }

    @Override
    public User get(int id) {
        User user = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectById)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                logger.debug("Query: " + statement.toString());
                if (resultSet.next()) {
                    user = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("email"),
                            resultSet.getDate("birthday"),
                            resultSet.getInt("sex_id"),
                            resultSet.getInt("weight"),
                            resultSet.getInt("height"),
                            resultSet.getInt("lifestyle_id"),
                            resultSet.getInt("calorie_norm"),
                            resultSet.getInt("role_id"));
                } else {
                    logger.info("No user with id=" + id + " found");
                }
            }
        } catch (SQLException e) {
            logger.error("Error in getting user with id=" + id, e.getCause());
        }
        return user;
    }

    @Override
    public User get(String login) {
        User user = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectByLogin)) {

            statement.setString(1, login);

            try (ResultSet resultSet = statement.executeQuery()) {
                logger.debug("Query: " + statement.toString());
                if (resultSet.next()) {
                    user = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("email"),
                            resultSet.getDate("birthday"),
                            resultSet.getInt("sex_id"),
                            resultSet.getInt("weight"),
                            resultSet.getInt("height"),
                            resultSet.getInt("lifestyle_id"),
                            resultSet.getInt("calorie_norm"),
                            resultSet.getInt("role_id"));
                } else {
                    logger.info("No user with login=" + login + " found");
                }
            }
        } catch (SQLException e) {
            logger.error("Error in getting user with login=" + login, e.getCause());
        }
        return user;
    }

    @Override
    public boolean add(User newEntity) {
        int resultInsert = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(insert)
        ) {
            statement.setString(1, newEntity.getLogin());
            statement.setString(2, newEntity.getPassword());
            statement.setString(3, newEntity.getFirstName());
            statement.setString(4, newEntity.getLastName());
            statement.setString(5, newEntity.getEmail());
            statement.setDate(6, new Date(newEntity.getBirthday().getTime()));
            statement.setInt(7, newEntity.getSexId());
            statement.setInt(8, newEntity.getWeight());
            statement.setInt(9, newEntity.getHeight());
            statement.setInt(10, newEntity.getLifestyleId());
            statement.setInt(11, newEntity.getCalorieNorm());

            logger.debug("Query: " + statement.toString());
            resultInsert = statement.executeUpdate();
            if (resultInsert < 1) {
                logger.info("User was not added.");
            }
        } catch (SQLException e) {
            logger.error("Error in adding user", e.getCause());
        }
        return resultInsert > 0;
    }

    @Override
    public boolean update(int oldId, User newEntity) {
        int resultUpdate = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateById)
        ) {
            statement.setString(1, newEntity.getLogin());
            statement.setString(2, newEntity.getPassword());
            statement.setString(3, newEntity.getFirstName());
            statement.setString(4, newEntity.getLastName());
            statement.setString(5, newEntity.getEmail());
            statement.setDate(6, new Date(newEntity.getBirthday().getTime()));
            statement.setInt(7, newEntity.getSexId());
            statement.setInt(8, newEntity.getWeight());
            statement.setInt(9, newEntity.getHeight());
            statement.setInt(10, newEntity.getLifestyleId());
            statement.setInt(11, oldId);

            logger.debug("Query: " + statement.toString());
            resultUpdate = statement.executeUpdate();
            if (resultUpdate < 1) {
                logger.info("User was not updated.");
            }
        } catch (SQLException e) {
            logger.error("Error in updating user", e.getCause());
        }
        return resultUpdate > 0;
    }

    @Override
    public boolean delete(int id) {
        int resultDelete = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteById)
        ) {
            statement.setInt(1, id);

            logger.debug("Query: " + statement.toString());
            resultDelete = statement.executeUpdate();
            if (resultDelete < 1) {
                logger.info("User was not deleted.");
            }
        } catch (SQLException e) {
            logger.error("Error in deleting user with id=" + id, e.getCause());
        }

        return resultDelete > 0;
    }
}

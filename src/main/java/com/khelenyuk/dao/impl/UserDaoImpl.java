package com.khelenyuk.dao.impl;

import com.khelenyuk.connection.ConnectionPool;
import com.khelenyuk.dao.UserDao;
import com.khelenyuk.model.User;
import com.khelenyuk.utils.QueryManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoImpl extends CrudDaoImpl<User> implements UserDao {
    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);


    private static final String SELECT_ALL = QueryManager.getProperty("userSelectAll");
    private static final String SELECT_ALL_LIMIT_OFFSET = QueryManager.getProperty("userSelectAllLimitOffset");
    private static final String SELECT_BY_ID = QueryManager.getProperty("userSelectById");
    private static final String SELECT_BY_LOGIN = QueryManager.getProperty("userSelectByLogin");
    private static final String INSERT = QueryManager.getProperty("userInsert");
    private static final String UPDATE = QueryManager.getProperty("userUpdate");
    private static final String UPDATE_STATUS_ID = QueryManager.getProperty("userUpdateStatusId");
    private static final String SELECT_COUNT = QueryManager.getProperty("userSelectCount");
    private static final String DELETE = QueryManager.getProperty("userDeleteById");


    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()
        ) {
            logger.info("Query: " + statement.toString());
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getDate("birthday").toLocalDate(),
                        resultSet.getInt("gender_id"),
                        resultSet.getInt("weight"),
                        resultSet.getInt("goal_weight"),
                        resultSet.getInt("height"),
                        resultSet.getInt("lifestyle_id"),
                        resultSet.getInt("calorie_norm"),
                        resultSet.getInt("role_id"),
                        resultSet.getInt("status_id"))
                );
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
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                logger.info("Query: " + statement.toString());
                if (resultSet.next()) {
                    user = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("email"),
                            resultSet.getDate("birthday").toLocalDate(),
                            resultSet.getInt("gender_id"),
                            resultSet.getInt("weight"),
                            resultSet.getInt("goal_weight"),
                            resultSet.getInt("height"),
                            resultSet.getInt("lifestyle_id"),
                            resultSet.getInt("calorie_norm"),
                            resultSet.getInt("role_id"),
                            resultSet.getInt("status_id"));
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
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_LOGIN)) {

            statement.setString(1, login);

            try (ResultSet resultSet = statement.executeQuery()) {
                logger.info("Query: " + statement.toString());
                if (resultSet.next()) {
                    user = new User(
                            resultSet.getInt("id"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("email"),
                            resultSet.getDate("birthday").toLocalDate(),
                            resultSet.getInt("gender_id"),
                            resultSet.getInt("weight"),
                            resultSet.getInt("goal_weight"),
                            resultSet.getInt("height"),
                            resultSet.getInt("lifestyle_id"),
                            resultSet.getInt("calorie_norm"),
                            resultSet.getInt("role_id"),
                            resultSet.getInt("status_id"));
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
             PreparedStatement statement = connection.prepareStatement(INSERT)
        ) {
            statement.setString(1, newEntity.getLogin());
            statement.setString(2, newEntity.getPassword());
            statement.setString(3, newEntity.getFirstName());
            statement.setString(4, newEntity.getLastName());
            statement.setString(5, newEntity.getEmail());
            statement.setDate(6,  Date.valueOf(newEntity.getBirthday()));
            statement.setInt(7, newEntity.getGenderId());
            statement.setInt(8, newEntity.getWeight());
            statement.setInt(9, newEntity.getGoalWeight());
            statement.setInt(10, newEntity.getHeight());
            statement.setInt(11, newEntity.getLifestyleId());
            statement.setInt(12, newEntity.getCalorieNorm());

            logger.info("Query: " + statement.toString());
            resultInsert = statement.executeUpdate();
            logger.info((resultInsert < 1) ? "User was not added." : resultInsert + " user was successfully added.");

        } catch (SQLException e) {
            logger.error("Error in adding user", e.getCause());
        }
        return resultInsert > 0;
    }

    @Override
    public boolean update(User user) {
        int resultUpdate = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)
        ) {
            statement.setDate(1, Date.valueOf(user.getBirthday()));
            statement.setInt(2, user.getGenderId());
            statement.setInt(3, user.getWeight());
            statement.setInt(4, user.getGoalWeight());
            statement.setInt(5, user.getHeight());
            statement.setInt(6, user.getLifestyleId());
            statement.setInt(7, user.getCalorieNorm());
            statement.setInt(8, user.getId());

            logger.info("Query: " + statement.toString());
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
    public boolean updateStatus(User user) {
        int resultUpdate = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS_ID)
        ) {
            statement.setInt(1, user.getStatusId());
            statement.setInt(2, user.getId());


            logger.info("Query: " + statement.toString());
            resultUpdate = statement.executeUpdate();
            if (resultUpdate < 1) {
                logger.info("User statusId was not updated.");
            }
        } catch (SQLException e) {
            logger.error("Error in updating user statusId", e.getCause());
        }
        return resultUpdate > 0;
    }

    @Override
    public List<User> getAll(int limit, int offset) {
        List<User> users = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_LIMIT_OFFSET);
        ) {
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            try (ResultSet resultSet = statement.executeQuery()) {
                logger.info("Query: " + statement.toString());
                while (resultSet.next()) {
                    users.add(new User(
                            resultSet.getInt("id"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("email"),
                            resultSet.getDate("birthday").toLocalDate(),
                            resultSet.getInt("gender_id"),
                            resultSet.getInt("weight"),
                            resultSet.getInt("goal_weight"),
                            resultSet.getInt("height"),
                            resultSet.getInt("lifestyle_id"),
                            resultSet.getInt("calorie_norm"),
                            resultSet.getInt("role_id"),
                            resultSet.getInt("status_id"))
                    );
                }
            }
        } catch (SQLException e) {
            logger.error("Error in obtaining 'users' limit:" + limit + " offsetting:" + offset + " \ncause: " + e.getCause());
        }

        return users;
    }

    @Override
    public int getUsersCount() {
        int result = 0;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_COUNT);
             ResultSet resultSet = statement.executeQuery()
        ) {
            logger.info("Query: " + statement.toString());
            if (resultSet.next()) {
                result = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            logger.error("Error in obtaining 'number of users'" + e.getCause());
        }
        logger.info("Number of users is :" + result);
        return result;
    }

    @Override
    public boolean delete(int id) {
        int resultDelete = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)
        ) {
            statement.setInt(1, id);

            logger.info("Query: " + statement.toString());
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

package com.khelenyuk.dao.mysql.impl;

import com.khelenyuk.connection.ConnectionPool;
import com.khelenyuk.dao.CrudDAO;
import com.khelenyuk.dao.UserDAO;
import com.khelenyuk.model.User;
import com.khelenyuk.utils.QueryManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAOImpl extends CrudDaoImpl<User> implements UserDAO {
    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);


    private String selectAll = QueryManager.getProperty("userSelectAll");
    private String selectAllLimitOffset = QueryManager.getProperty("userSelectAllLimitOffset");
    private String selectById = QueryManager.getProperty("userSelectById");
    private String selectByLogin = QueryManager.getProperty("userSelectByLogin");
    private String insert = QueryManager.getProperty("userInsert");
    private String updateById = QueryManager.getProperty("userUpdate");
    private String updateStatusIdById = QueryManager.getProperty("userUpdateStatusId");
    private String selectCount = QueryManager.getProperty("userSelectCount");
    private String deleteById = QueryManager.getProperty("userDeleteById");


    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAll);
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
                        resultSet.getDate("birthday"),
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
             PreparedStatement statement = connection.prepareStatement(selectById)) {

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
                            resultSet.getDate("birthday"),
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
             PreparedStatement statement = connection.prepareStatement(selectByLogin)) {

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
                            resultSet.getDate("birthday"),
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
             PreparedStatement statement = connection.prepareStatement(insert)
        ) {
            statement.setString(1, newEntity.getLogin());
            statement.setString(2, newEntity.getPassword());
            statement.setString(3, newEntity.getFirstName());
            statement.setString(4, newEntity.getLastName());
            statement.setString(5, newEntity.getEmail());
            statement.setDate(6, new Date(newEntity.getBirthday().getTime()));
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
    public boolean update(int userId, User user) {
        int resultUpdate = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateById)
        ) {
            statement.setDate(1, new Date(user.getBirthday().getTime()));
            statement.setInt(2, user.getGenderId());
            statement.setInt(3, user.getWeight());
            statement.setInt(4, user.getGoalWeight());
            statement.setInt(5, user.getHeight());
            statement.setInt(6, user.getLifestyleId());
            statement.setInt(7, userId);

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
             PreparedStatement statement = connection.prepareStatement(updateStatusIdById)
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
             PreparedStatement statement = connection.prepareStatement(selectAllLimitOffset);
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
                            resultSet.getDate("birthday"),
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
             PreparedStatement statement = connection.prepareStatement(selectCount);
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
             PreparedStatement statement = connection.prepareStatement(deleteById)
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

package dao.mysql;

import connection.ConnectionPool;
import dao.EntityDAO;
import entity.Lifestyle;
import entity.Role;
import entity.Sex;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Level.*;

public class UserDAOImpl implements EntityDAO<User> {
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class.getName());
    private final String TABLE = "users";
    private String selectAll = "SELECT * FROM " + TABLE;
    private String selectById = "SELECT * FROM " + TABLE + " WHERE id=?";
    private String selectByLogin = "SELECT * FROM " + TABLE + " WHERE login=?";
    private String insert = "INSERT INTO " + TABLE + "(`login`, `password`, `first_name`, `last_name`, `email`, `birthday`, `sex_id`, `weight`, `height`, `lifestyle_id`,  `calorie_norm`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private String updateById = "UPDATE " + TABLE + "" + " SET `login`, `password`, `first_name`=?, `last_name`=?, `email`=?, `birthday`=?, `sex_id`=?, `weight`=?, `height`=?, `lifestyle_id`=? WHERE id=?";

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectAll)
        ) {
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
            e.printStackTrace();
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
                    logger.log(INFO, "No user with id=" + id + " found");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
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
                    logger.log(INFO, "No user with login=" + login + " found");
                }
            }
        } catch (SQLException e) {
            logger.log(INFO, e.getCause().toString());
        }
        return user;
    }

    @Override
    public boolean add(User newObject) {
        int resultInsert = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(insert)
        ) {
            statement.setString(1, newObject.getLogin());
            statement.setString(2, newObject.getPassword());
            statement.setString(3, newObject.getFirstName());
            statement.setString(4, newObject.getLastName());
            statement.setString(5, newObject.getEmail());
            statement.setDate(6, new Date(newObject.getBirthday().getTime()));
            statement.setInt(7, newObject.getSexId());
            statement.setInt(8, newObject.getWeight());
            statement.setInt(9, newObject.getHeight());
            statement.setInt(10, newObject.getLifestyleId());
            statement.setInt(11, newObject.getCalorieNorm());

            resultInsert = statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(SEVERE, e.getCause().toString());
            e.printStackTrace();
        }
        return resultInsert > 0;
    }

    @Override
    public boolean update(int oldId, User newObject) {
        int resultUpdate = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateById)
        ) {
            statement.setString(1, newObject.getLogin());
            statement.setString(2, newObject.getPassword());
            statement.setString(3, newObject.getFirstName());
            statement.setString(4, newObject.getLastName());
            statement.setString(5, newObject.getEmail());
            statement.setDate(6, new Date(newObject.getBirthday().getTime()));
            statement.setInt(7, newObject.getSexId());
            statement.setInt(8, newObject.getWeight());
            statement.setInt(9, newObject.getHeight());
            statement.setInt(10, newObject.getLifestyleId());
            statement.setInt(11, oldId);

            resultUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(SEVERE, e.getCause().toString());
            e.printStackTrace();
        }
        return resultUpdate>0;
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException();
    }
}

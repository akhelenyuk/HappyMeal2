package com.khelenyuk.dao.mysql;

import com.khelenyuk.connection.ConnectionPool;
import com.khelenyuk.dao.CrudDAO;
import com.khelenyuk.entity.Sex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SexDAOImpl implements CrudDAO<Sex> {
    private static final Logger logger = LogManager.getLogger(SexDAOImpl.class);
    private final String TABLE = "sex";
    private String selectAll = "SELECT * FROM " + TABLE;


    @Override
    public List<Sex> getAll() {
        List<Sex> sex = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAll);
             ResultSet resultSet = statement.executeQuery()
        ) {
            logger.debug("Query: " + statement.toString());
            while (resultSet.next()) {
                sex.add(new Sex(
                        resultSet.getInt("id"),
                        resultSet.getString("name")));
            }
        } catch (SQLException e) {
            logger.error("Error in 'get all Sex' method", e.getCause());
        }
        return sex;
    }

    @Override
    public Sex get(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Sex get(String login) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(Sex newObject) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(int oldId, Sex newObject) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException();
    }
}

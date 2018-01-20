package com.khelenyuk.dao.mysql.impl;

import com.khelenyuk.connection.ConnectionPool;
import com.khelenyuk.dao.ActivityDAO;
import com.khelenyuk.model.Activity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityDAOImpl extends CrudDaoImpl<Activity> implements ActivityDAO {
    private static final Logger logger = LogManager.getLogger(ActivityDAOImpl.class);

    private final String TABLE = "activity";
    private String selectAll = "SELECT * FROM " + TABLE + " ORDER BY id ASC";

    @Override
    public List<Activity> getAll() {
        List<Activity> activities = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAll);
             ResultSet resultSet = statement.executeQuery()
        ) {
            logger.info("Query: " + statement.toString());
            while (resultSet.next()) {
                activities.add(new Activity(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("calories")));
            }
        } catch (SQLException e) {
            logger.error("Error in 'get all Activities' method", e.getCause());
        }
        return activities;
    }


}

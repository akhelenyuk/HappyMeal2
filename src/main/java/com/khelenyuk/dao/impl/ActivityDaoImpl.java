package com.khelenyuk.dao.impl;

import com.khelenyuk.connection.ConnectionPool;
import com.khelenyuk.dao.ActivityDao;
import com.khelenyuk.model.Activity;
import com.khelenyuk.utils.QueryManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActivityDaoImpl extends CrudDaoImpl<Activity> implements ActivityDao {
    private static final Logger logger = LogManager.getLogger(ActivityDaoImpl.class);

    private String selectAll = QueryManager.getProperty("activitySelectAll");
    private String selectByName = QueryManager.getProperty("activitySelectByName");
    private String insert = QueryManager.getProperty("activityInsert");

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

    @Override
    public Activity get(String name) {
        Activity activity = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectByName)) {

            statement.setString(1, name);

            try (ResultSet resultSet = statement.executeQuery()) {
                logger.info("Query: " + statement.toString());
                if (resultSet.next()) {
                    activity = new Activity(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("calories")
                    );
                } else {
                    logger.info("No activity with name='" + name + "' found");
                }
            }
        } catch (SQLException e) {
            logger.error("Error in getting activity with name=" + name, e.getCause());
        }
        return activity;
    }

    @Override
    public boolean add(Activity activity) {
        int resultInsert = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(insert)
        ) {
            statement.setString(1, activity.getName());
            statement.setInt(2, activity.getCalories());

            logger.info("Query: " + statement.toString());
            resultInsert = statement.executeUpdate();
            if (resultInsert > 1) {
                logger.info("Activity was not added.");
            }
        } catch (SQLException e) {
            logger.error("Error in adding activity", e.getCause());
        }
        return resultInsert > 0;
    }
}

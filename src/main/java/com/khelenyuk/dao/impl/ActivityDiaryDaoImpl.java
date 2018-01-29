package com.khelenyuk.dao.impl;

import com.khelenyuk.connection.ConnectionPool;
import com.khelenyuk.dao.ActivityDiaryDao;
import com.khelenyuk.model.ActivityDiary;
import com.khelenyuk.model.ActivityDiaryToDisplay;
import com.khelenyuk.utils.QueryManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ActivityDiaryDaoImpl extends CrudDaoImpl<ActivityDiary> implements ActivityDiaryDao {
    private static final Logger logger = LogManager.getLogger(ActivityDiaryDaoImpl.class);

    private static final String INSERT = QueryManager.getProperty("activityDiaryInsert");
    private static final String SELECT_ALL_BY_ID = QueryManager.getProperty("activityDiarySelectAllById");
    private static final String SELECT_TOTALS = QueryManager.getProperty("activityDiarySelectTotals");
    private static final String DELETE = QueryManager.getProperty("activityDiaryDelete");


    @Override
    public boolean add(ActivityDiary activityDiaryEntry) {
        int resultInsert = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)
        ) {
            statement.setInt(1, activityDiaryEntry.getUserId());
            statement.setInt(2, activityDiaryEntry.getActivityId());
            statement.setInt(3, activityDiaryEntry.getTimeSpent());
            statement.setDate(4, Date.valueOf(activityDiaryEntry.getDate()));

            resultInsert = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error in adding new 'ActivityDiary entry to DB", e.getCause());
        }
        return resultInsert > 0;
    }

    @Override
    public List<ActivityDiaryToDisplay> getAll(Integer userId, LocalDate date) {
        List<ActivityDiaryToDisplay> listOfActivities = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BY_ID)) {
            statement.setInt(1, userId);
            statement.setDate(2, Date.valueOf(date));

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    listOfActivities.add(new ActivityDiaryToDisplay(
                            resultSet.getInt("id"),
                            resultSet.getString("activity"),
                            resultSet.getInt("time_spent"),
                            resultSet.getInt("calories")
                    ));
                }
            }
        } catch (SQLException e) {
            logger.error("Error in getting 'list of activities' from database", e.getCause());
        }
        return listOfActivities;
    }

    @Override
    public ActivityDiaryToDisplay getTotals(Integer userId, LocalDate date) {
        ActivityDiaryToDisplay totals = new ActivityDiaryToDisplay();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_TOTALS)) {
            statement.setInt(1, userId);
            statement.setDate(2, Date.valueOf(date));

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    totals = new ActivityDiaryToDisplay(
                            null,
                            null,
                            resultSet.getInt("time_spent"),
                            resultSet.getInt("calories")
                    );
                }
            }
        } catch (SQLException e) {
            logger.error("Error in getting 'totals of activities' from database", e.getCause());
        }
        return totals;
    }

    @Override
    public boolean delete(int id){
        int resultDelete = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)
        ) {
            statement.setInt(1, id);
            resultDelete = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error in deleting 'ActivityDiary entry' from DB", e.getCause());
        }
        return resultDelete > 0;
    }
}

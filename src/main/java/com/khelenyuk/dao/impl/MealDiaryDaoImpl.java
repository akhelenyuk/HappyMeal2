package com.khelenyuk.dao.impl;

import com.khelenyuk.connection.ConnectionPool;
import com.khelenyuk.dao.MealDao;
import com.khelenyuk.model.MealToDisplay;
import com.khelenyuk.model.Meal;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.khelenyuk.utils.QueryManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MealDiaryDaoImpl extends CrudDaoImpl<Meal> implements MealDao {
    private static final Logger logger = LogManager.getLogger(MealDiaryDaoImpl.class);

    private static final String SELECT_BY_USER_ID = QueryManager.getProperty("mealDiarySelectByUserId");
    private static final String SELECT_DAY_TOTALS = QueryManager.getProperty("mealDiarySelectDayTotals");
    private static final String SELECT_TOTALS_BY_MEAL_TYPE = QueryManager.getProperty("mealDiarySelectTotalsByMealType");
    private static final String INSERT = QueryManager.getProperty("mealDiaryInsert");
    private static final String DELETE = QueryManager.getProperty("mealDiaryDelete");


    @Override
    public List<MealToDisplay> getMenu(int userId, LocalDate chosenDate) {
        List<MealToDisplay> menu = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_USER_ID)) {
            statement.setInt(1, userId);
            statement.setDate(2, Date.valueOf(chosenDate));

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    menu.add(new MealToDisplay(
                            resultSet.getInt("id"),
                            resultSet.getString("meal"),
                            resultSet.getString("prod"),
                            resultSet.getFloat("weight"),
                            resultSet.getInt("calories"),
                            resultSet.getFloat("protein"),
                            resultSet.getFloat("fat"),
                            resultSet.getFloat("carbs")
                    ));
                }
            }
        } catch (SQLException e) {
            logger.error("Error in getting menu from database", e.getCause());
        }
        return menu;
    }

    @Override
    public boolean add(Meal newMeal) {
        int resultInsert = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT)
        ) {
            statement.setInt(1, newMeal.getUserId());
            statement.setInt(2, newMeal.getProductId());
            statement.setInt(3, newMeal.getWeight());
            statement.setInt(4, newMeal.getMealTypeId());
            statement.setDate(5, Date.valueOf(newMeal.getDate()));

            resultInsert = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error in adding new meal to DB", e.getCause());
        }
        return resultInsert > 0;
    }

    @Override
    public MealToDisplay getTotalsByMealType(Integer userId, LocalDate date, Integer mealTypeId) {
        MealToDisplay mealToDisplayTotals = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_TOTALS_BY_MEAL_TYPE)) {

            statement.setInt(1, userId);
            statement.setDate(2, Date.valueOf(date));
            statement.setInt(3, mealTypeId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    mealToDisplayTotals = new MealToDisplay(
                            resultSet.getFloat("weight"),
                            resultSet.getInt("calories"),
                            resultSet.getFloat("protein"),
                            resultSet.getFloat("fat"),
                            resultSet.getFloat("carbs")
                    );
                }
            }
        } catch (SQLException e) {
            logger.error("Error in getting 'total' value by 'meal type' from database", e.getCause());
        }
        return mealToDisplayTotals;
    }

    @Override
    public MealToDisplay getTotals(Integer userId, LocalDate date) {
        MealToDisplay mealToDisplayTotals = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_DAY_TOTALS)) {

            statement.setInt(1, userId);
            statement.setDate(2, Date.valueOf(date));

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    mealToDisplayTotals = new MealToDisplay(
                            resultSet.getFloat("weight"),
                            resultSet.getInt("calories"),
                            resultSet.getFloat("protein"),
                            resultSet.getFloat("fat"),
                            resultSet.getFloat("carbs")
                    );
                }
            }
        } catch (SQLException e) {
            logger.error("Error in getting 'Day totals' values from database", e.getCause());
        }
        return mealToDisplayTotals;
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
            logger.error("Error in deleting 'MealDiary entry' from DB", e.getCause());
        }
        return resultDelete > 0;
    }
}

package com.khelenyuk.dao.mysql;

import com.khelenyuk.connection.ConnectionPool;
import com.khelenyuk.entity.MealFull;
import com.khelenyuk.entity.Meal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MealDAO {
    private static final Logger logger = LogManager.getLogger(MealDAO.class);

    private final String TABLE = "meal";
    private final String selectByUserId = "SELECT meal_number.name as meal, products.name as prod, weight, (SELECT products.calories*weight/100 FROM products WHERE products.id = product_id) as calories, (SELECT products.protein*weight/100 FROM products WHERE products.id = product_id) as protein, (SELECT products.fat*weight/100 FROM products WHERE products.id = product_id) as fat, (SELECT products.carbs*weight/100 FROM products WHERE products.id = product_id) as carbs FROM ((happy_meal.meal INNER JOIN happy_meal.products ON happy_meal.meal.product_id = happy_meal.products.id) INNER JOIN happy_meal.meal_number ON happy_meal.meal.meal_number_id = happy_meal.meal_number.id) WHERE meal.user_id=? ORDER BY meal_number.id;";
    private final String insert = "INSERT INTO " + TABLE + "(`user_id`, `product_id`, `weight`, `meal_number_id`) VALUES (?, ?, ?, ?)";


    public List<MealFull> getMenu(int userId) {
        List<MealFull> menu = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectByUserId)) {
            statement.setInt(1, userId);

            logger.info("Executing statement: " + statement.toString());

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    menu.add(new MealFull(
                            resultSet.getString("meal"),
                            resultSet.getString("prod"),
                            resultSet.getInt("weight"),
                            resultSet.getFloat("calories"),
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

    public boolean add(Meal newMeal) {
        int resultInsert = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(insert)
        ) {
            statement.setInt(1, newMeal.getUserId());
            statement.setInt(2, newMeal.getProductId());
            statement.setInt(3, newMeal.getWeight());
            statement.setInt(4, newMeal.getMealNumber().getId());

            logger.info("Executing request: " + statement.toString());
            resultInsert = statement.executeUpdate();
            logger.info("Result set of adding = " + resultInsert);
        } catch (SQLException e) {
            logger.error("Error in adding new meal to DB", e.getCause());
        }
        return resultInsert > 0;
    }



}

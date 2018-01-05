package dao.mysql;

import connection.ConnectionPool;
import entity.MealFull;
import entity.Meal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

public class MealDAO {
    private static final Logger logger = Logger.getLogger(MealDAO.class.getName());
    private final String TABLE = "meal";
    private final String selectByUserId = "SELECT meal_number.name as meal, products.name as prod, weight, (SELECT products.calories*weight/100 FROM products WHERE products.id = product_id) as calories, (SELECT products.protein*weight/100 FROM products WHERE products.id = product_id) as protein, (SELECT products.fat*weight/100 FROM products WHERE products.id = product_id) as fat, (SELECT products.carbs*weight/100 FROM products WHERE products.id = product_id) as carbs FROM ((happy_meal.meal INNER JOIN happy_meal.products ON happy_meal.meal.product_id = happy_meal.products.id) INNER JOIN happy_meal.meal_number ON happy_meal.meal.meal_number_id = happy_meal.meal_number.id) WHERE meal.user_id=? ORDER BY meal_number.id;";
    private final String insert = "INSERT INTO " + TABLE + "(`user_id`, `product_id`, `weight`, `meal_number_id`) VALUES (?, ?, ?, ?)";

//    private final String selectTotalCaloriesByUserId = "SELECT products.name as name, weight, products.calories as calories, calories*weight/100 as total FROM meal INNER JOIN products ON meal.product_id = products.id where user_id=?;";


    public List<MealFull> getMenu(int userId) {
        List<MealFull> menu = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectByUserId)) {
            statement.setInt(1, userId);

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
            e.printStackTrace();
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

            resultInsert = statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(SEVERE, e.getCause().toString());
            e.printStackTrace();
        }
        return resultInsert > 0;
    }

//    public int getUserTotalCalories(int userId) {
//        int totalCalories = 0;
//        try (Connection connection = ConnectionPool.getConnection();
//             PreparedStatement statement = connection.prepareStatement(selectTotalCaloriesByUserId)) {
//            statement.setInt(1, userId);
//
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//                    totalCalories += resultSet.getInt("");
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return totalCalories;
//
//    }


}

package com.khelenyuk.dao.mysql.impl;

import com.khelenyuk.connection.ConnectionPool;
import com.khelenyuk.dao.MealTypeDAO;
import com.khelenyuk.model.MealType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MealTypeDAOImpl extends CrudDaoImpl<MealType> implements MealTypeDAO {
    private static final Logger logger = LogManager.getLogger(MealTypeDAOImpl.class);
    private final String TABLE = "meal_type";
    private String selectAll = "SELECT * FROM " + TABLE;

    @Override
    public List<MealType> getAll() {
        List<MealType> mealTypes = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAll);
             ResultSet resultSet = statement.executeQuery()
        ) {
            logger.debug("Query: " + statement.toString());
            while (resultSet.next()) {
                mealTypes.add(new MealType(
                        resultSet.getInt("id"),
                        resultSet.getString("name")));
            }
        } catch (SQLException e) {
            logger.error("Error in 'get all meal types' method", e.getCause());
        }
        return mealTypes;
    }


}

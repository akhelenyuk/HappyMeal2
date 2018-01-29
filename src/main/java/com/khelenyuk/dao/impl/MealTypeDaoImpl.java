package com.khelenyuk.dao.impl;

import com.khelenyuk.connection.ConnectionPool;
import com.khelenyuk.dao.MealTypeDao;
import com.khelenyuk.model.MealType;
import com.khelenyuk.utils.QueryManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MealTypeDaoImpl extends CrudDaoImpl<MealType> implements MealTypeDao {
    private static final Logger logger = LogManager.getLogger(MealTypeDaoImpl.class);

    private static final String SELECT_ALL = QueryManager.getProperty("mealTypesSelectAll");

    @Override
    public List<MealType> getAll() {
        List<MealType> mealTypes = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()
        ) {
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

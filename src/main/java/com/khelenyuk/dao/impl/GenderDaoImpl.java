package com.khelenyuk.dao.impl;

import com.khelenyuk.connection.ConnectionPool;
import com.khelenyuk.dao.GenderDao;
import com.khelenyuk.model.Gender;
import com.khelenyuk.utils.QueryManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class GenderDaoImpl extends CrudDaoImpl<Gender> implements GenderDao {
    private static final Logger logger = LogManager.getLogger(GenderDaoImpl.class);

    private String selectAll = QueryManager.getProperty("genderSelectAll");


    @Override
    public List<Gender> getAll() {
        List<Gender> genders = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAll);
             ResultSet resultSet = statement.executeQuery()
        ) {
            logger.info("Query: " + statement.toString());
            while (resultSet.next()) {
                genders.add(new Gender(
                        resultSet.getInt("id"),
                        resultSet.getString("name")));
            }
        } catch (SQLException e) {
            logger.error("Error in 'get all Genders' method", e.getCause());
        }
        return genders;
    }


}

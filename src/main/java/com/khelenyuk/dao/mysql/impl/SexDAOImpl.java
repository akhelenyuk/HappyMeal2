package com.khelenyuk.dao.mysql.impl;

import com.khelenyuk.connection.ConnectionPool;
import com.khelenyuk.dao.CrudDAO;
import com.khelenyuk.dao.SexDAO;
import com.khelenyuk.model.Sex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SexDAOImpl extends CrudDaoImpl<Sex> implements SexDAO {
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


}

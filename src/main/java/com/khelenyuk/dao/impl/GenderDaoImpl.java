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

    private static final String SELECT_ALL = QueryManager.getProperty("genderSelectAll");
    private static final String SELECT = QueryManager.getProperty("genderSelect");


    @Override
    public List<Gender> getAll() {
        List<Gender> genders = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()
        ) {
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

    @Override
    public Gender get(int id) {
        Gender gender = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT);
        ) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    gender = new Gender(
                            resultSet.getInt("id"),
                            resultSet.getString("name"));
                }else {
                    logger.info("No gender with id=" + id + " found");
                }
            }
        } catch (SQLException e) {
            logger.error("Error in 'get Gender' method", e.getCause());
        }
        return gender;
    }

}

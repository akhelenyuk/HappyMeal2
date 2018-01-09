package com.khelenyuk.dao.mysql;

import com.khelenyuk.connection.ConnectionPool;
import com.khelenyuk.dao.EntityDAO;
import com.khelenyuk.entity.Lifestyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LifestyleDAOImpl implements EntityDAO<Lifestyle> {
    private static final Logger logger = LogManager.getLogger(LifestyleDAOImpl.class);
    private final String TABLE = "lifestyle";
    private String selectAll = "SELECT * FROM " + TABLE;


    @Override
    public List<Lifestyle> getAll() {
        List<Lifestyle> sex = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAll);
             ResultSet resultSet = statement.executeQuery()
        ) {
            logger.debug("Query: " + statement.toString());
            while (resultSet.next()) {
                sex.add(new Lifestyle(
                        resultSet.getInt("id"),
                        resultSet.getString("name")));
            }
        } catch (SQLException e) {
            logger.error("Error in 'get all Lifestyles' method", e.getCause());
        }

        return sex;
    }

    @Override
    public Lifestyle get(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Lifestyle get(String login) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(Lifestyle newObject) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(int oldId, Lifestyle newObject) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException();
    }
}

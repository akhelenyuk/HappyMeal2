package com.khelenyuk.dao.impl;

import com.khelenyuk.connection.ConnectionPool;
import com.khelenyuk.dao.LifestyleDao;
import com.khelenyuk.model.Lifestyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LifestyleDaoImpl extends CrudDaoImpl<Lifestyle> implements LifestyleDao {
    private static final Logger logger = LogManager.getLogger(LifestyleDaoImpl.class);
    private final String TABLE = "lifestyle";
    private String selectAll = "SELECT * FROM " + TABLE;




    @Override
    public Lifestyle get(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Lifestyle> getAll() {
        List<Lifestyle> lifestyles = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectAll);
             ResultSet resultSet = statement.executeQuery()
        ) {
            logger.info("Query: " + statement.toString());
            while (resultSet.next()) {
                lifestyles.add(new Lifestyle(
                        resultSet.getInt("id"),
                        resultSet.getString("name")));
            }
        } catch (SQLException e) {
            logger.error("Error in 'get all lifestyles' method", e.getCause());
        }
        return lifestyles;
    }

    @Override
    public boolean add(Lifestyle newEntity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(int oldId, Lifestyle newEntity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException();
    }
}

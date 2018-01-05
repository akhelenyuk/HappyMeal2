package dao.mysql;

import connection.ConnectionPool;
import dao.EntityDAO;
import entity.Sex;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SexDAOImpl implements EntityDAO<Sex> {
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class.getName());
    private final String TABLE = "sex";
    private String selectAll = "SELECT * FROM " + TABLE;


    @Override
    public List<Sex> getAll() {
        List<Sex> sex = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectAll)
        ) {
            while (resultSet.next()) {
                sex.add(new Sex(
                        resultSet.getInt("id"),
                        resultSet.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sex;
    }

    @Override
    public Sex get(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Sex get(String login) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(Sex newObject) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean update(int oldId, Sex newObject) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int delete(int id) {
        throw new UnsupportedOperationException();
    }
}

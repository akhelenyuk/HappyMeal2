package dao.mysql;

import connection.ConnectionPool;
import dao.EntityDAO;
import entity.Lifestyle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class LifestyleDAOImpl implements EntityDAO<Lifestyle> {
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class.getName());
    private final String TABLE = "lifestyle";
    private String selectAll = "SELECT * FROM " + TABLE;


    @Override
    public List<Lifestyle> getAll() {
        List<Lifestyle> sex = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectAll)
        ) {
            while (resultSet.next()) {
                sex.add(new Lifestyle(
                        resultSet.getInt("id"),
                        resultSet.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
    public int delete(int id) {
        throw new UnsupportedOperationException();
    }
}

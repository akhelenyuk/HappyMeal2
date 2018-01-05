package dao.mysql;

import connection.ConnectionPool;
import dao.EntityDAO;
import entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.logging.Level.*;

public class ProductDAOImpl implements EntityDAO<Product> {
    private static final Logger logger = Logger.getLogger(ProductDAOImpl.class.getName());
    private final String TABLE = "products";
    private String selectById = "SELECT * FROM " + TABLE + " WHERE id=?";
    private String selectByName = "SELECT * FROM " + TABLE + " WHERE name=?";
    private String selectAll = "SELECT * FROM " + TABLE + " ORDER BY name ASC";
    private String updateById = "UPDATE " + TABLE + "" + " SET" + " name=?," + " calories=?," + " protein=?," + " fat=?," + " carbs=?" + " WHERE id=?";
    private String deleteById = "DELETE FROM " + TABLE + " WHERE id=?";
    private String insert = "INSERT INTO " + TABLE + "(`name`, `calories`, `protein`, `fat`, `carbs`) VALUES (?, ?, ?, ?, ?)";


    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectAll)
        ) {
            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getFloat("calories"),
                        resultSet.getFloat("protein"),
                        resultSet.getFloat("fat"),
                        resultSet.getFloat("carbs")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public Product get(int productId) {
        Product product = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectById)) {

            statement.setInt(1, productId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    product = new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getFloat("calories"),
                            resultSet.getFloat("protein"),
                            resultSet.getFloat("fat"),
                            resultSet.getFloat("carbs"));
                } else {
                    logger.log(INFO, "No product with id=" + productId + " found");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public Product get(String productName) {
        Product product = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectByName)) {

            statement.setString(1, productName);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    product = new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getFloat("calories"),
                            resultSet.getFloat("protein"),
                            resultSet.getFloat("fat"),
                            resultSet.getFloat("carbs"));
                } else {
                    logger.log(SEVERE, "No product with name='" + productName + "' found");
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public boolean add(Product productNew) {
        int resultInsert = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(insert)
        ) {
            statement.setString(1, productNew.getName());
            statement.setFloat(2, productNew.getCalories());
            statement.setFloat(3, productNew.getProtein());
            statement.setFloat(4, productNew.getFat());
            statement.setFloat(5, productNew.getCarbs());

            resultInsert = statement.executeUpdate();
            if(resultInsert<1){
                logger.log(Level.INFO, "Product was not added.");
            }
        } catch (SQLException e) {
            logger.log(SEVERE, e.getCause().toString());
            e.printStackTrace();
        }
        return resultInsert > 0;
    }

    @Override
    public boolean update(int productOldId, Product productNew) {
        int resultUpdate = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateById)
        ) {
            statement.setString(1, productNew.getName());
            statement.setFloat(2, productNew.getCalories());
            statement.setFloat(3, productNew.getProtein());
            statement.setFloat(4, productNew.getFat());
            statement.setFloat(5, productNew.getCarbs());
            statement.setInt(6, productOldId);

            resultUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(SEVERE, e.getCause().toString());
            e.printStackTrace();
        }
        return resultUpdate>0;
    }

    @Override
    public int delete(int productId) {
        int resultDelete = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteById)
        ) {
            statement.setInt(1, productId);
            resultDelete = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultDelete;
    }
}

package com.khelenyuk.dao.mysql;

import com.khelenyuk.connection.ConnectionPool;
import com.khelenyuk.dao.EntityDAO;
import com.khelenyuk.entity.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDAOImpl implements EntityDAO<Product> {
    private static final Logger logger = LogManager.getLogger(ProductDAOImpl.class);

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
             PreparedStatement statement = connection.prepareStatement(selectAll);
             ResultSet resultSet = statement.executeQuery()
        ) {
            logger.debug("Query: " + statement.toString());
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
            logger.error("Error in obtaining 'all products'" + e.getCause());
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
                logger.debug("Query: " + statement.toString());
                if (resultSet.next()) {
                    product = new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getFloat("calories"),
                            resultSet.getFloat("protein"),
                            resultSet.getFloat("fat"),
                            resultSet.getFloat("carbs"));
                } else {
                    logger.info("No product with id=" + productId + " found");
                }
            }
        } catch (SQLException e) {
            logger.error("Error in getting product with id=" + productId, e.getCause());
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
                logger.debug("Query: " + statement.toString());
                if (resultSet.next()) {
                    product = new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getFloat("calories"),
                            resultSet.getFloat("protein"),
                            resultSet.getFloat("fat"),
                            resultSet.getFloat("carbs"));
                } else {
                    logger.info("No product with name='" + productName + "' found");
                }
            }
        } catch (SQLException e) {
            logger.error("Error in getting product with name=" + productName, e.getCause());
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

            logger.debug("Query: " + statement.toString());
            resultInsert = statement.executeUpdate();
            if (resultInsert < 1) {
                logger.info("Product was not added.");
            }
        } catch (SQLException e) {
            logger.error("Error in adding product", e.getCause());
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

            logger.debug("Query: " + statement.toString());
            resultUpdate = statement.executeUpdate();
            if (resultUpdate < 1) {
                logger.info("Product was not updated.");
            }
        } catch (SQLException e) {
            logger.error("Error in updating product", e.getCause());
        }
        return resultUpdate > 0;
    }

    @Override
    public boolean delete(int productId) {
        int resultDelete = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(deleteById)
        ) {
            statement.setInt(1, productId);

            logger.debug("Query: " + statement.toString());
            resultDelete = statement.executeUpdate();
            if (resultDelete < 1) {
                logger.info("Product was not deleted.");
            }
        } catch (SQLException e) {
            logger.error("Error in deleting product with id=" + productId, e.getCause());
        }

        return resultDelete > 0;
    }
}

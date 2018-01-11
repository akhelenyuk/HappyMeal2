package com.khelenyuk.dao.mysql.impl;

import com.khelenyuk.connection.ConnectionPool;
import com.khelenyuk.dao.ProductDAO;
import com.khelenyuk.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDAOImpl extends CrudDaoImpl<Product> implements ProductDAO {
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
    public Product get(int id) {
        Product product = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectById)) {

            statement.setInt(1, id);

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
                    logger.info("No product with id=" + id + " found");
                }
            }
        } catch (SQLException e) {
            logger.error("Error in getting product with id=" + id, e.getCause());
        }
        return product;
    }

    @Override
    public Product get(String name) {
        Product product = null;

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectByName)) {

            statement.setString(1, name);

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
                    logger.info("No product with name='" + name + "' found");
                }
            }
        } catch (SQLException e) {
            logger.error("Error in getting product with name=" + name, e.getCause());
        }
        return product;
    }

    @Override
    public boolean add(Product product) {
        int resultInsert = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(insert)
        ) {
            statement.setString(1, product.getName());
            statement.setFloat(2, product.getCalories());
            statement.setFloat(3, product.getProtein());
            statement.setFloat(4, product.getFat());
            statement.setFloat(5, product.getCarbs());

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
    public boolean update(int productOldId, Product newEntity) {
        int resultUpdate = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(updateById)
        ) {
            statement.setString(1, newEntity.getName());
            statement.setFloat(2, newEntity.getCalories());
            statement.setFloat(3, newEntity.getProtein());
            statement.setFloat(4, newEntity.getFat());
            statement.setFloat(5, newEntity.getCarbs());
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

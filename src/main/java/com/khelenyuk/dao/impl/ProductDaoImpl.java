package com.khelenyuk.dao.impl;

import com.khelenyuk.connection.ConnectionPool;
import com.khelenyuk.dao.ProductDao;
import com.khelenyuk.model.Product;
import com.khelenyuk.utils.QueryManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDaoImpl extends CrudDaoImpl<Product> implements ProductDao {
    private static final Logger logger = LogManager.getLogger(ProductDaoImpl.class);

    private static final String SELECT_BY_ID = QueryManager.getProperty("productSelectById");
    private static final String SELECT_BY_NAME = QueryManager.getProperty("productSelectByName");
    private static final String SELECT_ALL = QueryManager.getProperty("productSelectAll");
    private static final String DELETE_BY_ID = QueryManager.getProperty("productDeleteById");
    private static final String INSERT = QueryManager.getProperty("productInsert");


    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = statement.executeQuery()
        ) {
            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("calories"),
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
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    product = new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("calories"),
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
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_NAME)) {

            statement.setString(1, name);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    product = new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("calories"),
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
             PreparedStatement statement = connection.prepareStatement(INSERT)
        ) {
            statement.setString(1, product.getName());
            statement.setFloat(2, product.getCalories());
            statement.setFloat(3, product.getProtein());
            statement.setFloat(4, product.getFat());
            statement.setFloat(5, product.getCarbs());

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
    public boolean delete(int productId) {
        int resultDelete = 0;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)
        ) {
            statement.setInt(1, productId);

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

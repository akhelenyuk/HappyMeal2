package com.khelenyuk.service;

import com.khelenyuk.model.Product;

import java.util.List;

public interface IProductService {
    /**
     * Adds new Product to db
     * @param product
     * @return true - if success, false - if not added
     */
    boolean addProduct(Product product);

    /**
     * Gets all Product from db
     * @return List of Product
     */
    List<Product> getAllProducts();

    /**
     * Checks if Product exist
     * @param name
     * @return true if name exist
     * false if name not exist
     */
    boolean checkProductExist(String name);
}

package com.khelenyuk.service;


import com.khelenyuk.dao.EntityDAO;
import com.khelenyuk.dao.mysql.ProductDAOImpl;
import com.khelenyuk.entity.Product;

import java.util.List;

public class ProductLogic {
    private static EntityDAO<Product> productDAO = new ProductDAOImpl();

    public static boolean addProduct(Product product) {
        return productDAO.add(product);
    }


    public static List<Product> getAllProducts() {
        return productDAO.getAll();
    }
}

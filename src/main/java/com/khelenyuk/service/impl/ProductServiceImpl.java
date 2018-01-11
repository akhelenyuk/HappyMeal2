package com.khelenyuk.service.impl;


import com.khelenyuk.dao.CrudDAO;
import com.khelenyuk.dao.mysql.ProductDAOImpl;
import com.khelenyuk.entity.Product;
import com.khelenyuk.service.IProductService;

import java.util.List;

public class ProductServiceImpl implements IProductService{
    private static CrudDAO<Product> productDAO = new ProductDAOImpl();
    private static ProductServiceImpl instance = new ProductServiceImpl();

    private ProductServiceImpl() {
    }

    public static ProductServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean addProduct(Product product) {
        return productDAO.add(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDAO.getAll();
    }
}

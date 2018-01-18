package com.khelenyuk.controller.service.impl;


import com.khelenyuk.dao.CrudDAO;
import com.khelenyuk.dao.mysql.impl.ProductDAOImpl;
import com.khelenyuk.model.Product;
import com.khelenyuk.controller.service.IProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProductServiceImpl implements IProductService{
    private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);

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

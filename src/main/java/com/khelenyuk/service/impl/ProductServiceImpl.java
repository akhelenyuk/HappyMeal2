package com.khelenyuk.service.impl;


import com.khelenyuk.dao.ProductDao;
import com.khelenyuk.dao.impl.ProductDaoImpl;
import com.khelenyuk.model.Product;
import com.khelenyuk.service.IProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProductServiceImpl implements IProductService{
    private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);

    private static ProductDao productDAO = new ProductDaoImpl();
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

    @Override
    public boolean checkProductExist(String name) {
        return productDAO.get(name) != null;
    }
}

package com.khelenyuk.controller.service;

import com.khelenyuk.model.Product;

import java.util.List;

public interface IProductService {
    boolean addProduct(Product product);
    List<Product> getAllProducts();
}

package com.khelenyuk.dao;

import com.khelenyuk.model.Product;

import java.util.List;

public interface ProductDAO extends CrudDAO<Product> {
    Product get(String name);
}

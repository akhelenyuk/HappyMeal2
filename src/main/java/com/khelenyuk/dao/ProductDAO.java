package com.khelenyuk.dao;

import com.khelenyuk.model.Product;


public interface ProductDAO extends CrudDAO<Product> {
    Product get(String name);
}

package com.khelenyuk.dao;

import com.khelenyuk.model.Product;


public interface ProductDao extends CrudDao<Product> {
    Product get(String name);
}

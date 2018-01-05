package dao;

import entity.Product;

import java.util.List;

public interface ProductDao{
    List<Product> getAllProducts();
    Product getProduct(int productId);
    int addProduct(Product productNew);
    int updateProduct(int productOldId, Product productNew);
    int deleteProduct(int productId);


}

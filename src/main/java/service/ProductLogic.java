package service;


import dao.EntityDAO;
import dao.mysql.ProductDAOImpl;
import entity.Product;

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

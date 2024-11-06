package com.example.ecommerce.service;


import com.example.ecommerce.exceptionHandler.CustomExceptionService;
import com.example.ecommerce.models.Product;

import java.util.List;

public interface ProductService {

    public Product getProductById(long id, String logID) throws CustomExceptionService;

    public List<Product> getAllProducts(String logID);

    public Product createProduct(Product product, String logID);

    public Product deleteProductById(long id, String logID);

    public Product updateProduct(Product product, String logID);
}

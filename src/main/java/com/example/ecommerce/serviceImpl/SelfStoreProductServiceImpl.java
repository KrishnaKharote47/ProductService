package com.example.ecommerce.serviceImpl;

import com.example.ecommerce.database.helper.CategoryServiceDAO;
import com.example.ecommerce.database.helper.ProductServiceDAO;
import com.example.ecommerce.exceptionHandler.CustomExceptionService;
import com.example.ecommerce.models.Category;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

@Service
public class SelfStoreProductServiceImpl implements ProductService {
    private static final Logger LOG = LogManager.getFormatterLogger();


    private final ProductServiceDAO productServiceDAO;
    private final CategoryServiceDAO categoryServiceDAO;

    public SelfStoreProductServiceImpl(ProductServiceDAO productServiceDAO, CategoryServiceDAO categoryServiceDAO) {
        this.productServiceDAO = productServiceDAO;
        this.categoryServiceDAO = categoryServiceDAO;
    }

    @Override
    public Product getProductById(long id, String logID) throws CustomExceptionService {
        Product savedProduct = null;
        try {
            LOG.info(logID + " Getting product by Id from DB :: ");
            Optional<Product> productById = productServiceDAO.findById(id);

            if (productById.isPresent()) {
                savedProduct = productById.get();
            }


        } catch (Exception e) {
            throw new RuntimeException(" Error occur while getting product by ID ");
        }
        return savedProduct;
    }

    @Override
    public List<Product> getAllProducts(String logID) {
        return List.of();
    }

    @Override
    public Product createProduct(Product product, String logID) {
        Product productSaved;
        try {
            if (product.getCategory().getId() == 0) {
                Category save = categoryServiceDAO.save(product.getCategory());
                product.setCategory(save);
            }

            productSaved = productServiceDAO.save(product);

            return productSaved;
        } catch (Exception e) {
            throw new RuntimeException(" Error occur while savinf product");
        }
    }

    @Override
    public Product deleteProductById(long id, String logID) {
        Product delProduct = null;
        try {
            LOG.info(logID + " Deleteing product by Id from DB :: ");
            Optional<Product> productById = productServiceDAO.findById(id);

            if (productById.isPresent()) {
                delProduct = productById.get();
                productServiceDAO.deleteById(id);
            } else {
                LOG.info(logID + " Product is not present in DB :: ");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return delProduct;
    }

    @Override
    public Product updateProduct(Product product, String logID) {
        return null;
    }
}

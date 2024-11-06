package com.example.ecommerce.controller;

import com.example.ecommerce.exceptionHandler.CustomExceptionService;
import com.example.ecommerce.exceptionHandler.ExceptionLogger;
import com.example.ecommerce.models.CommonConstants;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.helper.LogIDGenerator;
import com.example.ecommerce.service.ProductService;
import com.example.ecommerce.serviceImpl.TokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final TokenService tokenService;

    private static final Logger LOG = LogManager.getFormatterLogger();

    public ProductController(ProductService productService, TokenService tokenService) {
        String logID = LogIDGenerator.logIdGenerator();
        this.productService = productService;
        this.tokenService = tokenService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws Exception {
        String logID = CommonConstants.BLANK_STRING;
        try {
            logID = LogIDGenerator.logIdGenerator();
//            if (tokenService.validatToken(tokenValue)) {
                return new ResponseEntity<>(productService.getProductById(id, logID), HttpStatus.OK);
//            }
//            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        } catch (Exception e) {
            ExceptionLogger.printException(e, logID);
            throw new CustomExceptionService(CommonConstants.EXC_PROD);
        }

    }

    @GetMapping("/allProducts")
    public List<Product> getAllProducts() throws Exception {
        String logID = CommonConstants.BLANK_STRING;
        try {
            logID = LogIDGenerator.logIdGenerator();
            return productService.getAllProducts(logID);
        } catch (Exception e) {
            ExceptionLogger.printException(e, logID);
            throw new CustomExceptionService(CommonConstants.EXC_ALL_PROD);
        }
    }

    @PostMapping("/saveProduct")
    public Product saveProducts(@RequestBody Product product) throws CustomExceptionService {
        String logID = CommonConstants.BLANK_STRING;

        try {
            logID = LogIDGenerator.logIdGenerator();
            return productService.createProduct(product, logID);
        } catch (Exception e) {
            ExceptionLogger.printException(e, logID);
            throw new CustomExceptionService(CommonConstants.EXC_ALL_PROD);
        }
    }

    @PatchMapping("/updateProduct")
    public Product updateProducts(@RequestBody Product product) throws CustomExceptionService {
        String logID = CommonConstants.BLANK_STRING;
        try {


            logID = LogIDGenerator.logIdGenerator();
            return productService.updateProduct(product, logID);
        } catch (Exception e) {
            ExceptionLogger.printException(e, logID);
            throw new CustomExceptionService(CommonConstants.EXC_UPD_PROD);
        }
    }

    @DeleteMapping("/{id}")
    public Product deleteProduct(@PathVariable("id") Long id) throws CustomExceptionService {
        String logID = CommonConstants.BLANK_STRING;

        try {
            logID = LogIDGenerator.logIdGenerator();
            return productService.deleteProductById(id, logID);
        } catch (Exception e) {
            ExceptionLogger.printException(e, logID);
            throw new CustomExceptionService(CommonConstants.EXC_UPD_PROD);
        }

    }
}
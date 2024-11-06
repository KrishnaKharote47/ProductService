package com.example.ecommerce.database.helper;

import com.example.ecommerce.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ProductServiceDAO extends JpaRepository<Product, Long> {
}

package com.example.ecommerce.database.helper;

import com.example.ecommerce.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryServiceDAO extends JpaRepository<Category, Long> {
}

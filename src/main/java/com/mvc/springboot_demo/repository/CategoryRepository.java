package com.mvc.springboot_demo.repository;

import com.mvc.springboot_demo.model.Category;
import com.mvc.springboot_demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Page<Category> findAll(Pageable pageable);
}

package com.mvc.springboot_demo.repository;

import com.mvc.springboot_demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepsitory extends JpaRepository<Product,Long> {
//    Page<Product>findProductByNameContaining(Pageable pageable,String name);
    List<Product>findProductByNameContaining(String name);
    Page<Product> findAllByPriceBetween(Pageable pageable,double price1 ,double price2);
    Page<Product> findByOrderByPrice(Pageable pageable);
    Page<Product>findAll(Pageable pageable);
    List<Product>findProductByCategory_Id(Long id);
}

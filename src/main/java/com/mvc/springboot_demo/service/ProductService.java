package com.mvc.springboot_demo.service;

import com.mvc.springboot_demo.model.Product;
import com.mvc.springboot_demo.repository.ProductRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
   @Autowired
    ProductRepsitory productRepsitory;
    public Page<Product> getAllPage(int page, int size){
        Pageable pageable=  PageRequest.of(page,size);
        return productRepsitory.findAll(pageable);
    }
    public Page<Product> getSoftpage(int page, int size){
        Pageable pageable=  PageRequest.of(page,size);
        return productRepsitory.findByOrderByPrice(pageable);
    }
//    public Page<Product> getSearchByName(int page, int size, String name){
//        Pageable pageable=  PageRequest.of(page,size);
//        return productRepsitory.findProductByNameContaining(pageable,name);
//    }
    public Page<Product> getSearchOrderByPrice(int page, int size, double start, double end){
        Pageable pageable=  PageRequest.of(page,size);
        return productRepsitory.findAllByPriceBetween(pageable,start,end);
    }
}

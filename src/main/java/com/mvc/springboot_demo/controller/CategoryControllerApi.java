package com.mvc.springboot_demo.controller;

import com.mvc.springboot_demo.model.Category;
import com.mvc.springboot_demo.model.Product;
import com.mvc.springboot_demo.repository.CategoryRepository;
import com.mvc.springboot_demo.repository.ProductRepsitory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")  //tất cả các hệ thống có thể truy cập được
@RequestMapping("api/categories")
public class CategoryControllerApi {
    @Autowired
    CategoryRepository categoryRepository;


    @GetMapping
    public ResponseEntity findAll() {
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Category category , BindingResult bindingResult ) {
        if (bindingResult.hasErrors()){
            String err="";
            for (ObjectError e:bindingResult.getAllErrors()){
                err +=e.getDefaultMessage()+"\n";
            }
            return new ResponseEntity<>(err, HttpStatus.OK);
        }
        return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.OK);
    }
    @PutMapping ("/{id}")
    public ResponseEntity save(@RequestBody Category category , @PathVariable Long id) {
        category.setId(id);
        return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        categoryRepository.deleteById(id);
        return new ResponseEntity<>("Delete done",HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        return new ResponseEntity<>(categoryRepository.findById(id),HttpStatus.OK);
    }

}

package com.mvc.springboot_demo.controller;

import com.mvc.springboot_demo.model.Product;
import com.mvc.springboot_demo.repository.ProductRepsitory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")  //tất cả các hệ thống có thể truy cập được
@RequestMapping("api/products")
public class ProductControllerApi {
    @Autowired
    ProductRepsitory productRepsitory;


    @GetMapping
    public ResponseEntity findAll() {
        return new ResponseEntity<>(productRepsitory.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity save(@Valid  @RequestBody Product product ,BindingResult bindingResult ) {
        if (bindingResult.hasErrors()){
            String err="";
            for (ObjectError e:bindingResult.getAllErrors()){
                err +=e.getDefaultMessage()+"\n";
            }
            return new ResponseEntity<>(err, HttpStatus.OK);
        }
        return new ResponseEntity<>(productRepsitory.save(product), HttpStatus.OK);
    }
    @PutMapping ("/{id}")
    public ResponseEntity save(@RequestBody Product product , @PathVariable Long id) {
        product.setId(id);
        return new ResponseEntity<>(productRepsitory.save(product), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        productRepsitory.deleteById(id);
        return new ResponseEntity<>("Delete done",HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        return new ResponseEntity<>(productRepsitory.findById(id),HttpStatus.OK);
    }
    @GetMapping("search/{name}")
    public ResponseEntity search( @PathVariable String name) {
        return new ResponseEntity<>(productRepsitory.findProductByNameContaining(name), HttpStatus.OK);
    }
    @GetMapping("searchCate/{id}")
    public ResponseEntity searchCate( @PathVariable Long id) {
        return new ResponseEntity<>(productRepsitory.findProductByCategory_Id(id), HttpStatus.OK);
    }
}

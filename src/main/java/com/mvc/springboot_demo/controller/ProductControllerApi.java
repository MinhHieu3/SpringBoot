package com.mvc.springboot_demo.controller;

import com.mvc.springboot_demo.model.Product;
import com.mvc.springboot_demo.repository.ProductRepsitory;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@RestController
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
}

package com.mvc.springboot_demo.controller;


import com.mvc.springboot_demo.model.Product;
import com.mvc.springboot_demo.repository.CategoryRepository;
import com.mvc.springboot_demo.repository.ProductRepsitory;
import com.mvc.springboot_demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductRepsitory productRepsitory;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductService productService;

    @GetMapping
    public String showList(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Page<Product> productPage = productService.getAllPage(page,size);
        model.addAttribute("page", productPage);
        return "product/list";
    }
    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("product/create");
        modelAndView.addObject("listCate", categoryRepository.findAll());
        modelAndView.addObject("product", new Product());

        return modelAndView;
    }

    @PostMapping("/save")
    public String save(Product product) {
        productRepsitory.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("product/edit");
        modelAndView.addObject("edit", productRepsitory.findById(id).get());
        modelAndView.addObject("listCate", categoryRepository.findAll());
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productRepsitory.delete(productRepsitory.findById(id).get());
        return "redirect:/products";
    }

    @GetMapping("/search")
    public ModelAndView search(@RequestParam String search,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5")int size) {
        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("page",productService.getSearchByName(page,size,search));
        return modelAndView;
    }
    @GetMapping("/search2")
    public ModelAndView searchPriceBetween(@RequestParam double search1,@RequestParam double search2,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        ModelAndView modelAndView =new ModelAndView("product/list");

        modelAndView.addObject("page",productService.getSearchOrderByPrice(page,size,search1,search2));
        return modelAndView;
    }
    @GetMapping("/soft")
    public ModelAndView softPrice(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){
        ModelAndView modelAndView =new ModelAndView("product/list");

        modelAndView.addObject("page",productService.getSoftpage(page,size));
        return modelAndView;
    }

}

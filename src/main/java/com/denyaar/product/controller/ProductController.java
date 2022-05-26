package com.denyaar.product.controller;


import com.denyaar.product.Services.ServiceImplimentation.ProductService;
import com.denyaar.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping("/products/")
    List<Product> all() {
        return productService.allProducts();
    }
    
    
    @PostMapping("/addProduct")
    public ResponseEntity<Product> createTutorial(@RequestBody Product product) {
        try {
            productService.saveProduct(product);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        productService.deleteProductById(id);
    }
    
    
    
}




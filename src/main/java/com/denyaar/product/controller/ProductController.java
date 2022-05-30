package com.denyaar.product.controller;


import com.denyaar.product.Services.ServiceImplimentation.ProductService;
import com.denyaar.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;


    @GetMapping("/productList")
    public List<Product> list() {
        return productService.allProducts();
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<Product> get(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/productSave")
    public void add(@RequestBody Product product) {
        productService.saveProduct(product);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Product product, @PathVariable Long id) {
        try {
            Product existProduct = productService.getProductById(id);
            product.setId(id);
            productService.saveProduct(product);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        productService.deleteProductById(id);
    }



}




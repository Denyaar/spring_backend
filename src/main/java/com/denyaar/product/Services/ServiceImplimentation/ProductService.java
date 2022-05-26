package com.denyaar.product.Services.ServiceImplimentation;

import com.denyaar.product.Repositories.ProductRepository;
import com.denyaar.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product){
        productRepository.save(product);
        return product;
    }

    public List<Product> allProducts(){
        return (List<Product>) productRepository.findAll();
    }


    public Product getProductById(Long id){
        Optional<Product> optional=productRepository.findById(id);
        Product product = null;
        if(optional.isPresent()){
            product=optional.get();
        }else {
            throw new RuntimeException("Product not available" + id);
        }
        return product;
    }
    public void deleteProductById(long id){
        this.productRepository.deleteById(id);
    }


}

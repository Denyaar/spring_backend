package com.denyaar.product.Services.ServiceImplimentation;

import com.denyaar.product.Repositories.ProductRepository;
import com.denyaar.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void  saveProduct(Product product){
        productRepository.save(product);
    }

    public List<Product> allProducts(){
        return (List<Product>) productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }


    public void deleteProductById(long id){
        this.productRepository.deleteById(id);
    }


}

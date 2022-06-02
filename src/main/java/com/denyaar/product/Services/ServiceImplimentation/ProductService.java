package com.denyaar.product.Services.ServiceImplimentation;

import com.denyaar.product.Repositories.ProductRepository;
import com.denyaar.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    Page<Product> getRequestFilters(int page, int limit, String productName, Sort.Direction sortType) {
        Page<Product> productPage = null;
        if (productName == null && sortType ==null){
            productPage = getProductList(page,limit);
        }
        if (productName != null && sortType ==null){
            productPage = findProductByName(page,limit,productName);
        }
        if (productName == null && sortType !=null){
            productPage = getProductOrderByPrice(page,limit, String.valueOf(sortType));
        }
        if (productName != null && sortType !=null){
            productPage = getProductOrderByPriceAndOrderByPrice(page,limit,productName,sortType);
        }
        return productPage;
    }

    private Page<Product> getProductOrderByPriceAndOrderByPrice(int page, int limit, String productName, Sort.Direction sortType) {
        Sort sort  =Sort.by("price");
        Pageable pageable = PageRequest.of(page,limit, sort);
        return productRepository.findByNameContainingIgnoreCase(productName,pageable);
    }

    private Page<Product> getProductOrderByPrice(int page, int limit,
                                                 String productName) {
        Sort sort  =Sort.by("price");
        Pageable pageable = PageRequest.of(page,limit, sort);
        return productRepository.findAll(pageable);

    }


    private Page<Product> getProductList (int page,int limit){
        Pageable pageable = PageRequest.of(page,limit);
        return productRepository.findAll(pageable);
    }

    private Page<Product> findProductByName(int page, int limit, String productName) {
        Pageable pageable = PageRequest.of(page,limit);
        return productRepository.findByNameContainingIgnoreCase(productName, pageable);
    }

}

package com.squareshift.ecommerce.service;

import java.util.List;

import com.squareshift.ecommerce.dto.ProductDto;
import com.squareshift.ecommerce.model.Item;

public interface ProductService {

    ProductDto getProductById(Long id) throws Exception;
    
    List<ProductDto> getAllProducts() throws Exception;
    
    int saveProduct(Item item) throws Exception;
    
    int removeAllProducts(String action) throws Exception;
}

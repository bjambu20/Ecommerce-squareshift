package com.squareshift.ecommerce.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squareshift.ecommerce.dto.ProductDto;
import com.squareshift.ecommerce.dto.ProductResponseDto;
import com.squareshift.ecommerce.model.Item;
import com.squareshift.ecommerce.proxy.Proxy;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    Proxy proxy;

    public ProductDto getProductById(Long id) throws Exception{
        ProductResponseDto productById = proxy.getProductById(id);
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(productById.getProductDto(), productDto);
        return productDto;
    }
    
	public int saveProduct(Item item) throws Exception {
		int value = proxy.saveProduct(item);
		return value;
	}

	public int removeAllProducts(String action) throws Exception {
		int value = proxy.removeAllProducts(action);
		return value;
	}

	@Override
	public List<ProductDto> getAllProducts() throws Exception {
		List<ProductResponseDto> list = proxy.getAllProducts();
		List<ProductDto> listOfProductDTO = new ArrayList<>();
		for (Iterator<ProductResponseDto> iterator = list.iterator(); iterator.hasNext();) {
			ProductResponseDto productResponseDto = (ProductResponseDto) iterator.next();
			ProductDto productDto = new ProductDto();
			BeanUtils.copyProperties(productResponseDto.getProductDto(), productDto);
			listOfProductDTO.add(productDto);
		}
		return listOfProductDTO;
	} 
}

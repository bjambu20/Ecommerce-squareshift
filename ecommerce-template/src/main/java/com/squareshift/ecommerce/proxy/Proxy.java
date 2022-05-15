package com.squareshift.ecommerce.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.squareshift.ecommerce.dto.ProductResponseDto;
import com.squareshift.ecommerce.dto.WarehouseResponseDto;
import com.squareshift.ecommerce.model.Item;

@Service
@FeignClient(value = "client", url = "${external.system-service.url}")
public interface Proxy {

    @GetMapping(value = "/product/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponseDto getProductById(@PathVariable Long id) throws Exception;
    
    @GetMapping(value = "/cart/items", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductResponseDto> getAllProducts() throws Exception;
    
    @PostMapping(value = "/cart/item", consumes = MediaType.APPLICATION_JSON_VALUE)
    public int saveProduct(@RequestBody Item item) throws Exception;
    
    @PostMapping(value = "/cart/items", consumes = MediaType.APPLICATION_JSON_VALUE)
    public int removeAllProducts(@RequestBody String action) throws Exception;

    @GetMapping(value = "/warehouse/distance", consumes = MediaType.APPLICATION_JSON_VALUE)
    public WarehouseResponseDto getWareHouseDistanceByPostalCode(@RequestParam Long postal_code) throws Exception;

}

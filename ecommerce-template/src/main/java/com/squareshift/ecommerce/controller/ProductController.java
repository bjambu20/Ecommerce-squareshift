package com.squareshift.ecommerce.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.squareshift.ecommerce.constants.AmountInDollars;
import com.squareshift.ecommerce.constants.CartWeight;
import com.squareshift.ecommerce.constants.WarehouseDistance;
import com.squareshift.ecommerce.dto.ProductDto;
import com.squareshift.ecommerce.dto.WarehouseResponseDto;
import com.squareshift.ecommerce.model.Item;
import com.squareshift.ecommerce.service.ProductService;
import com.squareshift.ecommerce.service.WareHouseService;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;
    
    @Autowired
    WareHouseService whService;
    
    @GetMapping(value = "/v1/product/{id}")
    public ProductDto getProductById(@PathVariable Long id) throws Exception{
        return productService.getProductById(id);
    }
    
    @GetMapping(value = "/v1/cart/items")
    public List<ProductDto> getAllProducts() throws Exception{
        return productService.getAllProducts();
    }
    
    @PostMapping(value = "/v1/cart/item")
    public int saveProduct(@RequestBody Item item) throws Exception{
        return productService.saveProduct(item);
    }
    
    @PostMapping(value = "/v1/cart/items")
    public int removeAllProducts(@RequestBody String action) throws Exception{
    	int result = 0;
    	if(action.equalsIgnoreCase("empty_cart")) {
    		result = productService.removeAllProducts(action);
    	}
        return result;
    }
    
    //calculateToltalValue of cart
    @GetMapping(value = "/v1/cart/checkout-value")
    public int calculateTotalItemsValueInCart(@RequestParam Long shipping_postal_code) throws Exception{
    	//get distance 
    	WarehouseResponseDto warehouseResponseDto = whService.getWareHouseDistanceByPostalCode(shipping_postal_code);
    	Long distance = warehouseResponseDto.getDistance_in_kilometers();
    	//getAllProducts
    	List<ProductDto> productDtos= productService.getAllProducts();
    	//calculate total weight
    	Long totalWeightInGram = productDtos.stream().collect(Collectors.summingLong(ProductDto::getWeight_in_grams));
    	Long kilograms = totalWeightInGram / 1000;
    	int amountInDollars = 0;
    	//compare total and distance
    	//WEIGHT<2KG
		if (compareLongValue(kilograms, CartWeight.TWO.getNumVal()) < 0
				&& compareLongValue(distance, WarehouseDistance.FIVE.getNumVal()) < 0) {
			amountInDollars = AmountInDollars.TWELVE.getNumVal();
		}else if (compareLongValue(kilograms, CartWeight.TWO.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.FIVE.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.TWENTY.getNumVal()) < 0) {
			amountInDollars = AmountInDollars.TWELVE.getNumVal();
		}else if (compareLongValue(kilograms, CartWeight.TWO.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.TWENTY.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.FIFTY.getNumVal()) < 0) {
			amountInDollars = AmountInDollars.TWELVE.getNumVal();
		}else if (compareLongValue(kilograms, CartWeight.TWO.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.FIFTY.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.FIVE_HUNDRED.getNumVal()) < 0) {
			amountInDollars = AmountInDollars.TWELVE.getNumVal();
		}else if (compareLongValue(kilograms, CartWeight.TWO.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.FIVE_HUNDRED.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.EIGHT_HUNDRED.getNumVal()) < 0) {
			amountInDollars = AmountInDollars.TWELVE.getNumVal();
		}else if (compareLongValue(kilograms, CartWeight.TWO.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.EIGHT_HUNDRED.getNumVal()) > 0) {
			amountInDollars = AmountInDollars.TWELVE.getNumVal();
		}
		//WEIGHT<2.01 TO 5 KG
		if (compareLongValue(kilograms, CartWeight.TWO.getNumVal()) < 0
				&& compareLongValue(kilograms, CartWeight.FIVE.getNumVal()) < 0
				&& compareLongValue(distance, WarehouseDistance.FIVE.getNumVal()) < 0) {
			amountInDollars = AmountInDollars.TWELVE.getNumVal();
		}else if (compareLongValue(kilograms, CartWeight.TWO.getNumVal()) > 0
				&& compareLongValue(kilograms, CartWeight.FIVE.getNumVal()) < 0
				&& compareLongValue(distance, WarehouseDistance.FIVE.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.TWENTY.getNumVal()) < 0) {
			amountInDollars = AmountInDollars.TWELVE.getNumVal();
		}else if (compareLongValue(kilograms, CartWeight.TWO.getNumVal()) > 0
				&& compareLongValue(kilograms, CartWeight.FIVE.getNumVal()) < 0
				&& compareLongValue(distance, WarehouseDistance.TWENTY.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.FIFTY.getNumVal()) < 0) {
			amountInDollars = AmountInDollars.TWELVE.getNumVal();
		}else if (compareLongValue(kilograms, CartWeight.TWO.getNumVal()) > 0
				&& compareLongValue(kilograms, CartWeight.FIVE.getNumVal()) < 0
				&& compareLongValue(distance, WarehouseDistance.FIFTY.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.FIVE_HUNDRED.getNumVal()) < 0) {
			amountInDollars = AmountInDollars.TWELVE.getNumVal();
		}else if (compareLongValue(kilograms, CartWeight.TWO.getNumVal()) > 0
				&& compareLongValue(kilograms, CartWeight.FIVE.getNumVal()) < 0
				&& compareLongValue(distance, WarehouseDistance.FIVE_HUNDRED.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.EIGHT_HUNDRED.getNumVal()) < 0) {
			amountInDollars = AmountInDollars.TWELVE.getNumVal();
		}else if (compareLongValue(kilograms, CartWeight.TWO.getNumVal()) > 0
				&& compareLongValue(kilograms, CartWeight.FIVE.getNumVal()) < 0
				&& compareLongValue(distance, WarehouseDistance.EIGHT_HUNDRED.getNumVal()) > 0) {
			amountInDollars = AmountInDollars.TWELVE.getNumVal();
		}
		//WEIGHT<5.01 TO 20 KG
		if (compareLongValue(kilograms, CartWeight.FIVE.getNumVal()) < 0
				&& compareLongValue(kilograms, CartWeight.TWENTY.getNumVal()) < 0
				&& compareLongValue(distance, WarehouseDistance.FIVE.getNumVal()) < 0) {
			amountInDollars = AmountInDollars.TWELVE.getNumVal();
		}else if (compareLongValue(kilograms, CartWeight.FIVE.getNumVal()) < 0
				&& compareLongValue(kilograms, CartWeight.TWENTY.getNumVal()) < 0
				&& compareLongValue(distance, WarehouseDistance.FIVE.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.TWENTY.getNumVal()) < 0) {
			amountInDollars = AmountInDollars.TWELVE.getNumVal();
		}else if (compareLongValue(kilograms, CartWeight.FIVE.getNumVal()) < 0
				&& compareLongValue(kilograms, CartWeight.TWENTY.getNumVal()) < 0
				&& compareLongValue(distance, WarehouseDistance.TWENTY.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.FIFTY.getNumVal()) < 0) {
			amountInDollars = AmountInDollars.TWELVE.getNumVal();
		}else if (compareLongValue(kilograms, CartWeight.FIVE.getNumVal()) < 0
				&& compareLongValue(kilograms, CartWeight.TWENTY.getNumVal()) < 0
				&& compareLongValue(distance, WarehouseDistance.FIFTY.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.FIVE_HUNDRED.getNumVal()) < 0) {
			amountInDollars = AmountInDollars.TWELVE.getNumVal();
		}else if (compareLongValue(kilograms, CartWeight.FIVE.getNumVal()) < 0
				&& compareLongValue(kilograms, CartWeight.TWENTY.getNumVal()) < 0
				&& compareLongValue(distance, WarehouseDistance.FIVE_HUNDRED.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.EIGHT_HUNDRED.getNumVal()) < 0) {
			amountInDollars = AmountInDollars.TWELVE.getNumVal();
		}else if (compareLongValue(kilograms, CartWeight.FIVE.getNumVal()) < 0
				&& compareLongValue(kilograms, CartWeight.TWENTY.getNumVal()) < 0
				&& compareLongValue(distance, WarehouseDistance.EIGHT_HUNDRED.getNumVal()) > 0) {
			amountInDollars = AmountInDollars.TWELVE.getNumVal();
		}
		//WEIGHT> 20 KG
		if (compareLongValue(kilograms, CartWeight.TWENTY.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.FIVE.getNumVal()) < 0) {
			amountInDollars = AmountInDollars.TWELVE.getNumVal();
		}else if (compareLongValue(kilograms, CartWeight.TWENTY.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.FIVE.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.TWENTY.getNumVal()) < 0) {
			amountInDollars = AmountInDollars.TWELVE.getNumVal();
		}else if (compareLongValue(kilograms, CartWeight.TWENTY.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.TWENTY.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.FIFTY.getNumVal()) < 0) {
			amountInDollars = AmountInDollars.TWELVE.getNumVal();
		}else if (compareLongValue(kilograms, CartWeight.TWENTY.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.FIFTY.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.FIVE_HUNDRED.getNumVal()) < 0) {
			amountInDollars = AmountInDollars.TWELVE.getNumVal();
		}else if (compareLongValue(kilograms, CartWeight.TWENTY.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.FIVE_HUNDRED.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.EIGHT_HUNDRED.getNumVal()) < 0) {
			amountInDollars = AmountInDollars.TWELVE.getNumVal();
		}else if (compareLongValue(kilograms, CartWeight.TWENTY.getNumVal()) > 0
				&& compareLongValue(distance, WarehouseDistance.EIGHT_HUNDRED.getNumVal()) > 0) {
			amountInDollars = AmountInDollars.TWELVE.getNumVal();
		}
		return amountInDollars;
    }
    
    private int compareLongValue(Long first, Long second) {
    	return Long.compare(first, second);
    }
}

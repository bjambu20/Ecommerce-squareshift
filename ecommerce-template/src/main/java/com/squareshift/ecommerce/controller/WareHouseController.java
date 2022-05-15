package com.squareshift.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.squareshift.ecommerce.dto.WarehouseResponseDto;
import com.squareshift.ecommerce.service.WareHouseService;

@RestController
public class WareHouseController {

    @Autowired
    WareHouseService wareHouseService;

    @GetMapping(value = "/v1/warehouse/distance")
    public WarehouseResponseDto getWarehouseDistance(@RequestParam Long postalCode) throws Exception{
        return wareHouseService.getWareHouseDistanceByPostalCode(postalCode);
    }
}

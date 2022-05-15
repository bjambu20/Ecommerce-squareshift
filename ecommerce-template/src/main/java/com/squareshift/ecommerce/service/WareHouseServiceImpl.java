package com.squareshift.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squareshift.ecommerce.dto.WarehouseResponseDto;
import com.squareshift.ecommerce.proxy.Proxy;

@Service
public class WareHouseServiceImpl implements WareHouseService{
    @Autowired
    private Proxy proxy;

    public WarehouseResponseDto getWareHouseDistanceByPostalCode(Long postalCode) throws Exception {
        return proxy.getWareHouseDistanceByPostalCode(postalCode);
    }
}
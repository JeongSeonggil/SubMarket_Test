package com.submarket.sellerservice.service;

import com.submarket.sellerservice.dto.SellerDTO;

public interface ISellerService {

    int createSeller(SellerDTO sellerDTO) throws Exception;
}

package com.submarket.sellerservice.service;

import com.submarket.sellerservice.dto.SellerDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ISellerService extends UserDetailsService {

    int createSeller(SellerDTO sellerDTO) throws Exception;

    SellerDTO getSellerInfo(int sellerSeq) throws Exception;
    SellerDTO getSellerDetailsByUserId(String sellerId);

}

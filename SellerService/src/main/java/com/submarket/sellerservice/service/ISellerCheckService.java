package com.submarket.sellerservice.service;

public interface ISellerCheckService {
    boolean checkSellerBySellerId(String sellerId) throws Exception;

    boolean checkSellerBySellerEmail(String sellerEmail) throws Exception;

    boolean checkSellerByBusinessId(String businessId) throws Exception;
}

package com.submarket.sellerservice.service.impl;

import com.submarket.sellerservice.dto.SellerDTO;
import com.submarket.sellerservice.jpa.SellerRepository;
import com.submarket.sellerservice.jpa.entity.SellerEntity;
import com.submarket.sellerservice.service.ISellerCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("SellerCheckService")
@RequiredArgsConstructor
public class SellerCheckService implements ISellerCheckService {
    private final SellerRepository sellerRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override // 아이디 중복 확인
    public boolean checkSellerBySellerId(String sellerId) throws Exception {

        SellerEntity sellerEntity = sellerRepository.findBySellerId(sellerId);

        if (sellerEntity == null) {
            // 중복 없음 실행 가능
            return true;
        }

        return false;
    }

    @Override
    public boolean checkSellerBySellerEmail(String sellerEmail) throws Exception {

        SellerEntity sellerEntity = sellerRepository.findBySellerEmail(sellerEmail);

        if (sellerEntity == null) {
            return true;
        }

        return false;
    }

    @Override
    public boolean checkSellerByBusinessId(String businessId) throws Exception {

        SellerEntity sellerEntity = sellerRepository.findByBusinessId(businessId);

        if (sellerEntity == null) {
            return true;
        }

        return false;
    }

    @Override
    public boolean checkSellerBySellerPassword(SellerDTO sellerDTO) throws Exception {
        String sellerPassword = sellerDTO.getSellerPassword();
        String sellerId = sellerDTO.getSellerId();

        SellerEntity sellerEntity = sellerRepository.findBySellerId(sellerId);

        // 비밀번호가 일치하면 True
        return passwordEncoder.matches(sellerPassword, sellerEntity.getSellerPassword());
    }
}

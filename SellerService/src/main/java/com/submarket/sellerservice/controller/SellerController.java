package com.submarket.sellerservice.controller;

import com.submarket.sellerservice.dto.SellerDTO;
import com.submarket.sellerservice.mapper.SellerMapper;
import com.submarket.sellerservice.service.impl.SellerService;
import com.submarket.sellerservice.vo.RequestSellerInfo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SellerController {
    private final SellerService sellerService;

    @PostMapping("/sellers")
    public ResponseEntity<String> createSeller(@RequestBody RequestSellerInfo sellerInfo) throws Exception{
        log.info(this.getClass().getName() + ".createSeller Start!");

        SellerDTO sellerDTO = SellerMapper.INSTANCE.requestSellerInfoToSellerDto(sellerInfo);
        log.info("sellerPassword : " + sellerDTO.getSellerPassword());

        int res = sellerService.createSeller(sellerDTO);

        if (res == 1) {
            // 회원가입 성공
            return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("회원가입 실패");
        }
    }
}


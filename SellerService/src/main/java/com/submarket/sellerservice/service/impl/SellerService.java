package com.submarket.sellerservice.service.impl;

import com.submarket.sellerservice.dto.SellerDTO;
import com.submarket.sellerservice.jpa.SellerRepository;
import com.submarket.sellerservice.jpa.entity.SellerEntity;
import com.submarket.sellerservice.mapper.SellerMapper;
import com.submarket.sellerservice.service.ISellerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "SellerService")
@AllArgsConstructor
@Slf4j
public class SellerService implements ISellerService {
    private final SellerRepository sellerRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /** 사업자 회원가입 */
    @Override
    public int createSeller(SellerDTO sellerDTO) throws Exception {
        log.info(this.getClass().getName() + ".createSeller Start!");


        if (false) {
            // TODO: 2022/05/11 아이디 중복 확인
        } else if (false) {
            // TODO: 2022/05/11 이메일 중복 학인
        } else if (false) {
            // TODO: 2022/05/11 사업자 번호 중복 확인
        }

        // 비밀번호 인코딩
        sellerDTO.setSellerEncPassword(passwordEncoder.encode(sellerDTO.getSellerPassword()));
        sellerDTO.setSellerStatus(1);

        SellerEntity sellerEntity = SellerMapper.INSTANCE.sellerDTOToSellerEntity(sellerDTO);

        sellerRepository.save(sellerEntity);





        log.info(this.getClass().getName() + ".createSeller End!");
        return 0;
    }
}

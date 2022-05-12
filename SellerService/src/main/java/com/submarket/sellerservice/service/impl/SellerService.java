package com.submarket.sellerservice.service.impl;

import com.submarket.sellerservice.dto.SellerDTO;
import com.submarket.sellerservice.jpa.SellerRepository;
import com.submarket.sellerservice.jpa.entity.SellerEntity;
import com.submarket.sellerservice.mapper.SellerMapper;
import com.submarket.sellerservice.service.ISellerCheckService;
import com.submarket.sellerservice.service.ISellerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "SellerService")
@AllArgsConstructor
@Slf4j
public class SellerService implements ISellerService {
    private final SellerRepository sellerRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final SellerCheckService sellerCheckService;

    /** 사업자 회원가입 */
    @Override
    public int createSeller(SellerDTO sellerDTO) throws Exception {
        log.info(this.getClass().getName() + ".createSeller Start!");
        String sellerId = sellerDTO.getSellerId();
        String sellerEmail = sellerDTO.getSellerEmail();

        if (sellerCheckService.checkSellerBySellerId(sellerId)) {
            if (sellerCheckService.checkSellerBySellerEmail(sellerEmail)) {
                // TODO: 2022/05/11 이메일 중복 학인

                if (true) {
                    // TODO: 2022/05/11 사업자 번호 중복 확인
                    // All pass, 회원가입 로직 실행
                    sellerDTO.setSellerEncPassword(passwordEncoder.encode(sellerDTO.getSellerPassword()));
                    sellerDTO.setSellerStatus(1);
                    SellerEntity sellerEntity = SellerMapper.INSTANCE.sellerDTOToSellerEntity(sellerDTO);
                    sellerRepository.save(sellerEntity);

                } else { // TODO: 2022/05/12 Exception 변경
                    throw new Exception("사업자 번호 중복");
                }
            } else {
                throw new Exception("이메일 중복");
            }
        } else {
            throw new Exception("아이디 중복");
        }

        log.info(this.getClass().getName() + ".createSeller End!");
        return 0;
    }
}

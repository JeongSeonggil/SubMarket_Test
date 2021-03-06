package com.submarket.sellerservice.service.impl;

import com.submarket.sellerservice.dto.SellerDTO;
import com.submarket.sellerservice.jpa.SellerRepository;
import com.submarket.sellerservice.jpa.entity.SellerEntity;
import com.submarket.sellerservice.mapper.SellerMapper;
import com.submarket.sellerservice.service.ISellerCheckService;
import com.submarket.sellerservice.service.ISellerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service(value = "SellerService")
@RequiredArgsConstructor
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
        String businessId = sellerDTO.getBusinessId();

        if (sellerCheckService.checkSellerBySellerId(sellerId)) {
            if (sellerCheckService.checkSellerBySellerEmail(sellerEmail)) {
                if (sellerCheckService.checkSellerByBusinessId(businessId)) {
                    // All pass, 회원가입 로직 실행
                    sellerDTO.setSellerEncPassword(passwordEncoder.encode(sellerDTO.getSellerPassword()));
                    sellerDTO.setSellerStatus(1);
                    SellerEntity sellerEntity = SellerMapper.INSTANCE.sellerDTOToSellerEntity(sellerDTO);
                    sellerRepository.save(sellerEntity);

// TODO: 2022/05/12 Exception 변경
                } else {
                    throw new RuntimeException("사업자 번호 중복");
                }
            } else {
                throw new RuntimeException("이메일 중복");
            }
        } else {
            throw new RuntimeException("아이디 중복");
        }
        log.info(this.getClass().getName() + ".createSeller End!");

        return 1;
    }

    @Override
    public SellerDTO getSellerInfo(int sellerSeq) throws Exception {
        log.info(this.getClass().getName() + ".getSellerInfo Start!");

        Optional<SellerEntity> sellerEntityOptional = sellerRepository.findById(sellerSeq);

        SellerEntity sellerEntity = sellerEntityOptional.get();

        if (sellerEntity == null) {
            throw new RuntimeException("정보를 찾을 수 없습니다");
        }

        SellerDTO sellerDTO = SellerMapper.INSTANCE.sellerEntityToSellerDto(sellerEntity);

        log.info(this.getClass().getName() + ".getSellerInfo End!");
        return sellerDTO;
    }

    //####################################### JWT Don't change #######################################//
    @Override
    public SellerDTO getSellerDetailsByUserId(String sellerId) {
        SellerEntity rEntity = sellerRepository.findBySellerId(sellerId);

        if (rEntity == null) {
            throw new UsernameNotFoundException(sellerId);
        }

        // Status 확인
        if (rEntity.getSellerStatus() == 0) {
            throw new UsernameNotFoundException("탈퇴한 회원");
        }
        SellerDTO rDTO = SellerMapper.INSTANCE.sellerEntityToSellerDto(rEntity);

        return rDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String sellerId) throws UsernameNotFoundException {
        log.info("sellerName : " + sellerId);
        SellerEntity rEntity = sellerRepository.findBySellerId(sellerId);

        if (rEntity == null) {
            throw new UsernameNotFoundException(sellerId);
        }

        return new User(rEntity.getSellerId(), rEntity.getSellerPassword(),
                true, true, true, true,
                new ArrayList<>());
    }

    @Override
    public int deleteSeller(SellerDTO sellerDTO) throws Exception {
        log.info(this.getClass().getName() + ".deleteSeller Start!");

        if (sellerCheckService.checkSellerBySellerPassword(sellerDTO)) {
            // 일치한다면 진행
            SellerEntity sellerEntity = sellerRepository.findBySellerId(sellerDTO.getSellerId());

            if (sellerEntity.getSellerStatus() == 1) {
                // 활성화 되어 있다면 탈퇴, Exception
                sellerRepository.changeSellerStatus(sellerEntity.getSellerSeq());
            } else {
                throw new RuntimeException("이미 탈퇴한 회원입니다");
            }
        } else {
            throw new UsernameNotFoundException("비밀번호 불일치");
        }


        log.info(this.getClass().getName() + ".deleteSeller End!");
        return 1;
    }
}

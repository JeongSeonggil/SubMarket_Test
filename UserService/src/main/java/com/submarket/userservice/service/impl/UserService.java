package com.submarket.userservice.service.impl;

import com.submarket.userservice.dto.UserDTO;
import com.submarket.userservice.jpa.UserRepository;
import com.submarket.userservice.jpa.entity.UserEntity;
import com.submarket.userservice.mapper.UserMapper;
import com.submarket.userservice.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("UserService")
@Slf4j
public class UserService implements IUserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //####################################### 회원가입 #######################################//
    @Override
    public int createUser(UserDTO pDTO) throws Exception {
        log.info("-------------->  " + this.getClass().getName() + ".createUser Start !");

        // 중복 확인
        int check = checkUserInfoByIdEmail(pDTO.getUserId(), pDTO.getUserEmail());

        if (check == 0) { // 회원 정보 중복
            return check;
        } else { // 중복 X 회원가입 실행
            pDTO.setUserEncPassword(passwordEncoder.encode(pDTO.getUserPassword()));
            UserEntity pEntity = UserMapper.INSTANCE.userDTOToEntity(pDTO);
            userRepository.save(pEntity);
        }
        log.info("-------------->  " + this.getClass().getName() + ".createUser End !");
        return check;
    }

    //####################################### 아이디, 이메일 중복 확인 #######################################//
    @Override
    public int checkUserInfoByIdEmail(String userId, String userEmail) {
        log.info("--------------> " + this.getClass().getName() + ".checkUserInfo Start !");

        UserEntity userEntity = userRepository.findByUserIdOrUserEmail(userId, userEmail);


        if (userEntity != null) {
            return 0;
        }

        log.info("--------------> " + this.getClass().getName() + ".checkUserInfo End!");

        return 1;
    }

    //####################################### 사용자 정보 조회 By User ID #######################################//
    @Override
    public UserDTO getUserDetailsByUserId(String userId) {
        UserEntity rEntity = userRepository.findByUserId(userId);

        if (rEntity == null) {
            throw new UsernameNotFoundException(userId);
        }

        UserDTO rDTO = new ModelMapper().map(rEntity, UserDTO.class);
        return rDTO;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        log.info("username : " + userId);
        UserEntity rEntity = userRepository.findByUserId(userId);

        if (rEntity == null) {
            throw new UsernameNotFoundException(userId);
        }

        return new User(rEntity.getUserId(), rEntity.getUserPassword(),
                true, true, true, true,
                new ArrayList<>());
    }
}

package com.submarket.userservice.service.impl;

import com.submarket.userservice.dto.UserDTO;
import com.submarket.userservice.jpa.UserRepository;
import com.submarket.userservice.jpa.entity.UserEntity;
import com.submarket.userservice.mapper.UserMapper;
import com.submarket.userservice.service.IUserCheckService;
import com.submarket.userservice.service.IUserService;
import com.submarket.userservice.vo.ResponseUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserCheckService implements IUserCheckService {
    private UserRepository userRepository;

    @Autowired
    public UserCheckService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    /**<-------------------------->0 이 넘어온다면 중복 발생</-------------------------->*/
    @Override
    public int checkUserByUserId(String userId) {
        int res = 0;
        UserEntity userEntity = userRepository.findByUserId(userId);

        if (userEntity == null) {
            res = 1;
        }
        return res;
    }

    @Override
    public int checkUserByUserEmail(String userEmail) {
        int res = 0;
        UserEntity userEntity = userRepository.findByUserEmail(userEmail);

        if (userEntity == null) {
            res = 1;
        }

        return res;
    }

    @Override
    public UserDTO findUserInfoByUserEmail(String userEmail) {
        UserEntity userEntity = userRepository.findByUserEmail(userEmail);

        UserDTO rDTO = UserMapper.INSTANCE.userEntityToDTO(userEntity);

        return rDTO;
    }
}

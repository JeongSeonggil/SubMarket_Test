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
    public UserDTO createUser(UserDTO pDTO) throws Exception {
        log.info("-------------->  " + this.getClass().getName() + ".createUser Start !");

        pDTO.setUserPassword(passwordEncoder.encode(pDTO.getUserPassword()));
        pDTO.setUserStatus(1);

        UserEntity pEntity = UserMapper.INSTANCE.userDTOToEntity(pDTO);
        userRepository.save(pEntity);

        UserDTO rDTO = UserMapper.INSTANCE.userEntityToDTO(pEntity);

        log.info("-------------->  " + this.getClass().getName() + ".createUser End !");
        return rDTO;
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

package com.submarket.userservice.service.impl;

import com.submarket.userservice.dto.UserDTO;
import com.submarket.userservice.jpa.UserRepository;
import com.submarket.userservice.jpa.entity.UserEntity;
import com.submarket.userservice.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
@Slf4j
public class UserService implements IUserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO pDTO) throws Exception {
        log.info(this.getClass().getName() + ".createUser Start");

        pDTO.setUserPassword("EncPassword");
        pDTO.setUserStatus(1);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);

        UserEntity pEntity = mapper.map(pDTO, UserEntity.class);

        userRepository.save(pEntity);

        return null;
    }
}

package com.submarket.userservice.service;

import com.submarket.userservice.dto.UserDTO;
import com.submarket.userservice.jpa.entity.UserEntity;

public interface IUserCheckService {

    int checkUserByUserId(String userId);

    int checkUserByUserEmail(String userEmail);

    UserDTO findUserInfoByUserEmail(String userEmail);
}

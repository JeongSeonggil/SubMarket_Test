package com.submarket.userservice.service;

import com.submarket.userservice.dto.UserDTO;
import com.submarket.userservice.jpa.entity.UserEntity;
import com.submarket.userservice.vo.RequestChangePassword;

public interface IUserCheckService {

    boolean checkUserByUserId(String userId);

    boolean checkUserByUserEmail(String userEmail);

    boolean isTruePassword(String userId, String userPassword) throws Exception;

}

package com.submarket.userservice.service;

import com.submarket.userservice.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService{
    int createUser(UserDTO pDTO) throws Exception;

    UserDTO getUserDetailsByUserId(String userId);

    int checkUserInfoByIdEmail(String userId, String userEmail);


}

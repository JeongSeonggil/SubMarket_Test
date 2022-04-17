package com.submarket.userservice.service;

import com.submarket.userservice.dto.UserDTO;
import com.submarket.userservice.vo.RequestChangePassword;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService{
    int createUser(UserDTO pDTO) throws Exception;

    UserDTO getUserInfoByUserEmail(String userEmail);

    UserDTO getUserDetailsByUserId(String userId);

    int changeUserPassword(UserDTO pDTO, String newPassword) throws Exception;


}

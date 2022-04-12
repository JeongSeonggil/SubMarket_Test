package com.submarket.userservice.service;

import com.submarket.userservice.dto.UserDTO;

public interface IUserService {
    UserDTO createUser(UserDTO pDTO) throws Exception;
}

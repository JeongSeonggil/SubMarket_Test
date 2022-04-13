package com.submarket.userservice.controller;

import com.submarket.userservice.dto.UserDTO;
import com.submarket.userservice.mapper.UserMapper;
import com.submarket.userservice.service.impl.UserService;
import com.submarket.userservice.vo.RequestUser;
import com.submarket.userservice.vo.ResponseUser;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/users")
    public ResponseUser createUser(@RequestBody RequestUser requestUser) throws Exception {
        log.info("-------------->  " + this.getClass().getName() + ".createUser Start!");
        ModelMapper mapper = new ModelMapper();
        UserDTO pDTO = mapper.map(requestUser, UserDTO.class);

        UserDTO rDTO = userService.createUser(pDTO);

        ResponseUser responseUser = UserMapper.INSTANCE.userDTOToResponseUser(rDTO);
        responseUser.setWelcome("Hello");
        log.info("-------------->  " + this.getClass().getName() + ".createUser End!");

        return responseUser;

    }
}

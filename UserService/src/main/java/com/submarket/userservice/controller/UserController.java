package com.submarket.userservice.controller;

import com.submarket.userservice.dto.UserDTO;
import com.submarket.userservice.mapper.UserMapper;
import com.submarket.userservice.service.impl.UserService;
import com.submarket.userservice.vo.RequestUser;
import com.submarket.userservice.vo.ResponseUser;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.EntityMode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    //####################################### 회원가입 #######################################//
    @PostMapping("/users")
    public ResponseEntity<Integer> createUser(@RequestBody RequestUser requestUser) throws Exception {
        log.info("-------------->  " + this.getClass().getName() + ".createUser Start!");

        UserDTO pDTO = UserMapper.INSTANCE.RequestUserToUserDTO(requestUser);

        int check = userService.createUser(pDTO);

        if (check == 0) { // 아이디 중복, 회원 가입 실패
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(check);
        }

        log.info("-------------->  " + this.getClass().getName() + ".createUser End!");

        return ResponseEntity.status(HttpStatus.CREATED).body(check);
    }
}

package com.submarket.userservice.controller;

import com.submarket.userservice.dto.SubDto;
import com.submarket.userservice.dto.UserDto;
import com.submarket.userservice.jpa.SubRepository;
import com.submarket.userservice.jpa.UserRepository;
import com.submarket.userservice.jpa.entity.SubEntity;
import com.submarket.userservice.jpa.entity.UserEntity;
import com.submarket.userservice.mapper.SubMapper;
import com.submarket.userservice.mapper.UserMapper;
import com.submarket.userservice.service.impl.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
public class MainController {
    private Environment env;
    private MailService mailService;
    private UserRepository userRepository;
    private SubRepository subRepository;

    @Autowired
    public MainController(Environment env, MailService mailService, UserRepository userRepository, SubRepository subRepository) {
        this.env = env;
        this.mailService = mailService;
        this.userRepository = userRepository;
        this.subRepository = subRepository;
    }


    @GetMapping("/health")
    public String health() {
        log.info("UserService On");
        return env.getProperty("spring.application.name")
                + ", port(local.server.port) : " + env.getProperty("local.server.port")
                + ", port(server.port) : " + env.getProperty("server.port")
                + ", token secret : " + env.getProperty("token.secret")
                + ", token expiration time : " + env.getProperty("token.expiration_time");
    }

    @GetMapping("/test")
    @Transactional
    public UserDto test() {
        Optional<UserEntity> userEntityOptional = userRepository.findById(38);
        UserDto userDto = UserMapper.INSTANCE.userEntityToUserDto(userEntityOptional.get());

        return userDto;
    }

    @GetMapping("/test2")
    @Transactional
    public ResponseEntity<List<SubEntity>> test2() {

        List<SubEntity> list = new ArrayList<>();
        Iterable<SubEntity> subEntityList = subRepository.findAll();
        subEntityList.forEach(e -> {
            list.add(e);

        });
        return ResponseEntity.ok(list);
    }
}

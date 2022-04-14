package com.submarket.userservice.controller;

import com.submarket.userservice.dto.UserDTO;
import com.submarket.userservice.mapper.UserMapper;
import com.submarket.userservice.service.impl.UserCheckService;
import com.submarket.userservice.service.impl.UserService;
import com.submarket.userservice.vo.RequestUser;
import com.submarket.userservice.vo.ResponseUser;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.EntityMode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController {
    private UserService userService;
    private UserCheckService userCheckService;


    @Autowired
    public UserController(UserService userService, UserCheckService userCheckService) {
        this.userService = userService;
        this.userCheckService = userCheckService;
    }

    /**<---------------------->회원가입</---------------------->*/
    @PostMapping("/users")
    public ResponseEntity<Integer> createUser(@RequestBody RequestUser requestUser) throws Exception {
        log.info("-------------->  " + this.getClass().getName() + ".createUser Start!");
        int res = 0;

        UserDTO pDTO = UserMapper.INSTANCE.RequestUserToUserDTO(requestUser);

        res = userService.createUser(pDTO);

        if (res == 0) { /** 아이디 중복 발생 */
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }

        log.info("-------------->  " + this.getClass().getName() + ".createUser End!");

        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    /**<------------------------>아이디 중복 확인</------------------------>*/
    @GetMapping("/check-id/{userId}")
    public ResponseEntity<Integer> checkUserById(@PathVariable String userId) throws Exception {
        log.info("-------------------- > " + this.getClass().getName() + "checkId Start!");
        int res = userCheckService.checkUserByUserId(userId);

        if (res == 0) {
            log.info("아이디 중복 발생");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
        log.info("-------------------- > " + this.getClass().getName() + "checkId End!");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    /**<------------------------>이메일 중복 확인</------------------------>*/
    @GetMapping("/check-email/{userEmail}")
    public ResponseEntity<Integer> checkUserByEmail(@PathVariable String userEmail) throws Exception {
        log.info("-------------------- > " + this.getClass().getSimpleName() + "checkEmail Start!");
        int res = userCheckService.checkUserByUserEmail(userEmail);

        if (res == 0) {
            log.info("이메일 중복 발생");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
        log.info("-------------------- > " + this.getClass().getName() + "checkEmail End!");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    /**<------------------------>아이디 찾기 with UserEmail </------------------------>
     * 만약 Email 이 같다면 아이디 정보 일부를 제공*/
    @GetMapping("/find-id/{userEmail}")
    public ResponseEntity<ResponseUser> findUserId(@PathVariable String userEmail) throws Exception {
        log.info("-------------------- > " + this.getClass().getName() + "findUserId Start!");
        ResponseUser responseUser = new ResponseUser();
        UserDTO rDTO = userCheckService.findUserInfoByUserEmail(userEmail);
        String userId;
        String userIdEnd;

        if (rDTO == null) { /** 유저 정보가 없을 경우 Not Found return */
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        /** 사용자 정보가 있을 경우 수정 후 전송 */
        userId = rDTO.getUserId().replaceAll("(?<=.{4}).", "*");



        responseUser.setUserId(userId);
        log.info("-------------------- > " + this.getClass().getName() + "findUserId End!");
        return ResponseEntity.status(HttpStatus.OK).body(responseUser);
    }

}

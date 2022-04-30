package com.submarket.userservice.controller;

import com.submarket.userservice.dto.UserDto;
import com.submarket.userservice.mapper.UserMapper;
import com.submarket.userservice.service.impl.UserCheckService;
import com.submarket.userservice.service.impl.UserService;
import com.submarket.userservice.util.TokenUtil;
import com.submarket.userservice.vo.RequestChangePassword;
import com.submarket.userservice.vo.RequestUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController {
    private UserService userService;
    private UserCheckService userCheckService;
    private Environment env;


    @Autowired
    public UserController(UserService userService, UserCheckService userCheckService,
                          Environment env) {
        this.userService = userService;
        this.userCheckService = userCheckService;
        this.env = env;
    }

    /**<---------------------->회원가입</---------------------->*/
    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody RequestUser requestUser) throws Exception {
        log.info("-------------->  " + this.getClass().getName() + ".createUser Start!");
        int res = 0;

        UserDto pDTO = UserMapper.INSTANCE.RequestUserToUserDto(requestUser);

        res = userService.createUser(pDTO);

        if (res == 0) { /** 아이디 중복 발생 */
            return ResponseEntity.status(HttpStatus.CONFLICT).body("아이디 또는 이메일을 확인해주세요."); // 충돌 발생
        }

        log.info("-------------->  " + this.getClass().getName() + ".createUser End!");

        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공");
    }

    /**<------------------------>아이디 중복 확인</------------------------>*/
    @GetMapping("/users/check-id/{userId}")
    public ResponseEntity<String> checkUserById(@PathVariable String userId) throws Exception {
        log.info("-------------------- > " + this.getClass().getName() + "checkId Start!");
        boolean checkId = userCheckService.checkUserByUserId(userId);

        if (checkId) {
            log.info("-------------------- > " + this.getClass().getName() + "checkId End!");
            return ResponseEntity.status(HttpStatus.OK).body("사용가능한 아이디 입니다");
        }
        log.info("아이디 중복 발생");
        return ResponseEntity.status(HttpStatus.CONFLICT).body("아이디 중복 입니다");
    }

    /**<------------------------>이메일 중복 확인</------------------------>*/
    @GetMapping("/users/check-email/{userEmail}")
    public ResponseEntity<String> checkUserByEmail(@PathVariable String userEmail) throws Exception {
        log.info("-------------------- > " + this.getClass().getSimpleName() + "checkEmail Start!");
        boolean checkEmail = userCheckService.checkUserByUserEmail(userEmail);

        if (checkEmail) {
            log.info("-------------------- > " + this.getClass().getName() + "checkEmail End!");
            return ResponseEntity.status(HttpStatus.OK).body("사용가능한 이메일 입니다.");

        }
        log.info("이메일 중복 발생");
        return ResponseEntity.status(HttpStatus.CONFLICT).body("이메일 중복 입니다");
    }

    /**<------------------------>아이디 찾기 with UserEmail </------------------------>
     * 만약 Email 이 같다면 아이디 정보 일부를 제공*/
    @GetMapping("/users/find-id/{userEmail}")
    public ResponseEntity<String> findUserId(@PathVariable String userEmail) throws Exception {
        log.info("-------------------- > " + this.getClass().getName() + "findUserId Start!");
        UserDto rDTO = userService.getUserInfoByUserEmail(userEmail);
        String userId;

        if (rDTO == null) { /** 유저 정보가 없을 경우 Not Found return */
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자를 찾을 수 없습니다.");
        }
        /** 사용자 정보가 있을 경우 수정 후 전송 */
        userId = rDTO.getUserId().replaceAll("(?<=.{4}).", "*");
        log.info("-------------------- > " + this.getClass().getName() + "findUserId End!");
        return ResponseEntity.status(HttpStatus.OK).body(userId);
    }
    /**<------------------------>비밀번호 변경</------------------------>*/
    @PostMapping("/users/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody RequestChangePassword request) throws Exception {

        UserDto pDTO = new UserDto();
        pDTO.setUserId(request.getUserId());
        pDTO.setUserPassword(request.getOldPassword());

        int check = userService.changeUserPassword(pDTO, request.getNewPassword());

        if (check == 1) { // 변경 성공
            return ResponseEntity.status(HttpStatus.OK).body("비밀번호 변경 성공");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이전 비밀번호를 확인해 주세요");

    }

    @GetMapping("/getToken")
    public ResponseEntity<String> getToken(@RequestHeader HttpHeaders header) {
        log.debug("getToken Start!");
        String userId = TokenUtil.getUserIdByToken(header, env.getProperty("token.secret"));


        return ResponseEntity.status(HttpStatus.OK).body(userId);
    }


}

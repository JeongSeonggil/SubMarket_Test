package com.submarket.userservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestUser {
    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;
    private String userAge;
    private String userPn;
    private String userAddress;
    private String userAddress2;
}

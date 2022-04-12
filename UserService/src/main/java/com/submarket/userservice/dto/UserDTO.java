package com.submarket.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;
    private String userAge;
    private String userPn;
    private int userStatus;
    private String userAddress;
    private String userAddress2;
}

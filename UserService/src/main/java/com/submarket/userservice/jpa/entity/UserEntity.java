package com.submarket.userservice.jpa.entity;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "userInfo")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userSeq;

    @Column(nullable = false, length = 40, unique = true)
    private String userId;

    @Column(nullable = false, length = 200, unique = true)
    private String userPassword;

    @Column(nullable = false, length = 40)
    private String userName;

    @Column(nullable = false, length = 60, unique = true)
    private String userEmail;

    @Column(nullable = false)
    private String userAge;

    @Column(nullable = false, length = 30)
    private String userPn;

    @Column(nullable = false)
    private int userStatus;

    @Column(length = 70)
    private String userAddress;

    @Column(length = 80)
    private String userAddress2;

    @OneToMany(mappedBy = "user")
    private List<SubEntity> subEntityList;

    @Builder
    private UserEntity(String userId, String userPassword, String userName, String userEmail,
                       String userAge, String userPn, int userStatus, String userAddress, String userAddress2) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userAge = userAge;
        this.userPn = userPn;
        this.userStatus = userStatus;
        this.userAddress = userAddress;
        this.userAddress2 = userAddress2;
    }
}

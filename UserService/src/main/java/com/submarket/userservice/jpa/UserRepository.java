package com.submarket.userservice.jpa;

import com.submarket.userservice.jpa.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    // 사용자 ID로 사용자 정보 조회
    UserEntity findByUserId(String userId);


}

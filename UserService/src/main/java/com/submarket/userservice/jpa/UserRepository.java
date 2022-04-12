package com.submarket.userservice.jpa;

import com.submarket.userservice.jpa.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {


}

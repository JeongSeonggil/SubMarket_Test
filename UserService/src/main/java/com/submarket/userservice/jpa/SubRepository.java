package com.submarket.userservice.jpa;

import com.submarket.userservice.jpa.entity.SubEntity;
import com.submarket.userservice.jpa.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SubRepository extends CrudRepository<SubEntity, Integer> {

    List<SubEntity> findByUser(UserEntity user);



}

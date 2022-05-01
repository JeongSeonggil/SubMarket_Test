package com.submarket.userservice.jpa;

import com.submarket.userservice.jpa.entity.SubEntity;
import com.submarket.userservice.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SubRepository extends CrudRepository<SubEntity, Integer> {


    // 구독 취소
    @Transactional
    @Modifying
    @Query(value = "UPDATE sub_info set sub_status = 0 where sub_seq = :subSeq", nativeQuery = true)
    void cancelSub(int subSeq);

}

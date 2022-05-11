package com.submarket.itemservice.jpa;

import com.submarket.itemservice.jpa.entity.RecItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface RecItemRepository extends CrudRepository<RecItemEntity, Integer> {
}

package com.submarket.itemservice.jpa;

import com.submarket.itemservice.jpa.entity.ItemReviewEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ItemReviewRepository extends CrudRepository<ItemReviewEntity, Integer> {
}

package com.submarket.itemservice.jpa;

import com.submarket.itemservice.jpa.entity.ItemEntity;
import com.submarket.itemservice.jpa.entity.ItemReviewEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface ItemReviewRepository extends CrudRepository<ItemReviewEntity, Integer> {

    @Override
    @Transactional
    Optional<ItemReviewEntity> findById(Integer integer);

    @Transactional
    ItemReviewEntity findByItem(ItemEntity item);
}

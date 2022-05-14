package com.submarket.itemservice.jpa;

import com.submarket.itemservice.dto.ItemDto;
import com.submarket.itemservice.jpa.entity.ItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface ItemRepository extends CrudRepository<ItemEntity, Integer> {
    @Override
    @Transactional
    Optional<ItemEntity> findById(Integer integer);
}

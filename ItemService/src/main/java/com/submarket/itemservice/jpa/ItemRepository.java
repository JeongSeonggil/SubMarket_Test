package com.submarket.itemservice.jpa;

import com.submarket.itemservice.dto.ItemDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ItemRepository extends CrudRepository<ItemDto, Integer> {

}

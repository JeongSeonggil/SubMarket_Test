package com.submarket.itemservice.jpa;

import com.submarket.itemservice.dto.ItemDto;
import com.submarket.itemservice.jpa.entity.ItemEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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

    @Modifying
    @Transactional
    @Query(value = "UPDATE item_info SET item_status = 0 WHERE item_seq = :itemSeq", nativeQuery = true)
    int offItemStatus(@Param("itemSeq") int itemSeq);

    @Modifying
    @Transactional
    @Query(value = "UPDATE item_info SET item_status = 1 WHERE item_seq = :itemSeq", nativeQuery = true)
    int onItemStatus(@Param("itemSeq") int itemSeq);
}

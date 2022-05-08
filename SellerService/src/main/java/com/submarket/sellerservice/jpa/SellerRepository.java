package com.submarket.sellerservice.jpa;

import com.submarket.sellerservice.jpa.entity.SellerEntity;
import org.springframework.data.repository.CrudRepository;

public interface SellerRepository extends CrudRepository<SellerEntity, Integer> {
}
package com.submarket.itemservice.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categoryInfo")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categorySeq;

    @Column
    private String categoryName;

    @OneToMany(mappedBy = "category")
    List<ItemEntity> items;




}

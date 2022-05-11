package com.submarket.itemservice.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "itemInfo")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ItemSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    private GroupEntity group;
}

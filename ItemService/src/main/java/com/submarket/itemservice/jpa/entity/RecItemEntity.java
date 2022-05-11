package com.submarket.itemservice.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "recItemInfo")
public class RecItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer recItemSeq;

    @Column(nullable = false)
    private int userSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private ItemEntity item;
}

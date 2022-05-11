package com.submarket.itemservice.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "itemReviewInfo")
public class ItemReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewSeq;

    @Column(nullable = false)
    private int userSeq;

    @Column(nullable = false)
    private int reviewStar;

    @Column(nullable = false)
    private String reviewContents;

    @Column(nullable = false)
    private String reviewDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private ItemEntity item;
}

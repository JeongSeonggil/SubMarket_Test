package com.submarket.itemservice.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(name = "itemInfo")
@JsonIgnoreProperties({"category", "group"})
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemSeq;

    @Column(nullable = false)
    private Integer sellerSeq;

    @Column(length = 300, nullable = false)
    private String itemTitle;

    @Column(nullable = false)
    private int itemPrice;

    @Column(length = 2000)
    private String itemContents;

    @Column(nullable = false)
    private int itemCount;

    @Column(nullable = false)
    private int itemStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private GroupEntity group;

    // TODO: 2022/05/15 M : N 연결
//    @OneToMany(mappedBy = "item")
//    private List<ItemReviewEntity> reviews = new ArrayList<ItemReviewEntity>();

    // TODO: 2022/05/11 Img 등록 추가
}

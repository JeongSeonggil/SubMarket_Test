package com.submarket.itemservice.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "groupInfo")
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int GroupSeq;

    @Column
    private String GroupName;

    @OneToMany(mappedBy = "group")
    private List<ItemEntity> items;
}

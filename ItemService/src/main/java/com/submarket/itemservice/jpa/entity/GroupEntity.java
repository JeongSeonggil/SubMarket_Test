package com.submarket.itemservice.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private int groupSeq;

    @Column(nullable = false, length = 300)

    private String groupName;

    @OneToMany(mappedBy = "group")
    @JsonIgnore
    private List<ItemEntity> items;
}

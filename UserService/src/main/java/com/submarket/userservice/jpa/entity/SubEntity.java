package com.submarket.userservice.jpa.entity;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "subInfo")
public class SubEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subSeq;

    @Column(nullable = false)
    private int itemSeq;

    @Column(nullable = false)
    private int subStatus;

    @Column
    private String subDate;

    @Column(nullable = false)
    private int subCount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private UserEntity user;

    @Builder
    public SubEntity(int subStatus, String subDate, int subCount, UserEntity user, int itemSeq) {
        this.subStatus = subStatus;
        this.subCount = subCount;
        this.subDate = subDate;
        this.user = user;
        this.itemSeq = itemSeq;
    }
}

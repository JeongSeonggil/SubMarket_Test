package com.submarket.userservice.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "subInfo")
@JsonIgnoreProperties({"user"})
public class SubEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer subSeq;

    @Column(nullable = false)
    private int itemSeq;

    @Column
    private String subDate;

    @Column(nullable = false)
    private int subCount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private UserEntity user;

    @Builder
    public SubEntity(String subDate, int subCount, UserEntity user, int itemSeq) {
        this.subCount = subCount;
        this.subDate = subDate;
        this.user = user;
        this.itemSeq = itemSeq;
    }
}

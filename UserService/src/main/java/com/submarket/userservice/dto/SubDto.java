package com.submarket.userservice.dto;

import com.submarket.userservice.jpa.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubDto {
    private Integer subSeq;
    private int itemSeq;
    private int subStatus;
    private Date subDate;
    private int subCount;
    private UserEntity user;
}

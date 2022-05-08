package com.submarket.userservice.service;

import com.submarket.userservice.dto.SubDto;
import com.submarket.userservice.dto.UserDto;
import com.submarket.userservice.jpa.entity.SubEntity;

import java.util.List;

public interface ISubService {
    List<SubEntity> findSub(SubDto subDto) throws Exception;

    int createNewSub(SubDto subDto);

    int updateSub(SubDto subDto);

    int cancelSub(SubDto subDto);
}

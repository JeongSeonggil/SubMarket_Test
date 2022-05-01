package com.submarket.userservice.service;

import com.submarket.userservice.dto.SubDto;
import com.submarket.userservice.dto.UserDto;

import java.util.List;

public interface ISubService {

    int createNewSub(SubDto subDto);

    int updateSub(SubDto subDto);

    int cancelSub(SubDto subDto);

}

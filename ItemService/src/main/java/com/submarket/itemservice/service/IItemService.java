package com.submarket.itemservice.service;

import com.submarket.itemservice.dto.GroupDto;

public interface IItemService {
    GroupDto findItemInfoByGroup(GroupDto groupDto) throws Exception;
}

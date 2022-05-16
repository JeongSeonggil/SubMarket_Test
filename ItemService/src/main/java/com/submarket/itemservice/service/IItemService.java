package com.submarket.itemservice.service;

import com.submarket.itemservice.dto.GroupDto;
import com.submarket.itemservice.dto.ItemDto;

import java.util.List;

public interface IItemService {
    GroupDto findItemInfoByGroup(GroupDto groupDto) throws Exception;

    ItemDto findItemInfo(ItemDto itemDto) throws Exception;

    List<ItemDto> findAllItem() throws Exception;
}

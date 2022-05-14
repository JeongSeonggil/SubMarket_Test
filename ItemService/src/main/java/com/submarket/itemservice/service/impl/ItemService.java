package com.submarket.itemservice.service.impl;

import com.submarket.itemservice.dto.GroupDto;
import com.submarket.itemservice.jpa.GroupRepository;
import com.submarket.itemservice.jpa.ItemRepository;
import com.submarket.itemservice.jpa.entity.GroupEntity;
import com.submarket.itemservice.mapper.GroupMapper;
import com.submarket.itemservice.service.IItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service("ItemService")
public class ItemService implements IItemService {
    private final ItemRepository itemRepository;
    private final GroupRepository groupRepository;

    @Override
    public GroupDto findItemInfoByGroup(GroupDto groupDto) throws Exception {
        log.info(this.getClass().getName() + ".findItemInfoByGroup Start!");
        int groupSeq = groupDto.getGroupSeq();

        log.info("groupSeq : " + groupSeq);

        Optional<GroupEntity> groupEntityOptional = groupRepository.findById(groupSeq);
        GroupEntity groupEntity = groupEntityOptional.get();

        if (groupEntity == null) {
            // TODO: 2022/05/15 Exception Change
            throw new RuntimeException("Not found GroupEntity");
        }
        GroupDto rDto = GroupMapper.INSTANCE.groupEntityToGroupDto(groupEntity);

        log.info(this.getClass().getName() + ".findItemInfoByGroup End!");
        return rDto;
    }
}

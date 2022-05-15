package com.submarket.itemservice.service.impl;

import com.submarket.itemservice.dto.GroupDto;
import com.submarket.itemservice.jpa.GroupRepository;
import com.submarket.itemservice.jpa.entity.GroupEntity;
import com.submarket.itemservice.mapper.GroupMapper;
import com.submarket.itemservice.service.IGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service(value = "GroupService")
@Slf4j
@RequiredArgsConstructor
public class GroupService implements IGroupService {

    private final GroupRepository groupRepository;

    @Override
    @Transactional
    public GroupDto findItemInfoByGroupSeq(GroupDto groupDto) throws Exception {
        log.info(this.getClass().getName() + ".findItemInfoByGroup Start!");
        if (groupDto == null) {
            throw new RuntimeException("Group Seq를 찾을 수 없습니다");

        }
        Optional<GroupEntity> groupEntityOptional = groupRepository.findById(groupDto.getGroupSeq());

        GroupEntity groupEntity = groupEntityOptional.get();

        GroupDto rDto = GroupMapper.INSTANCE.groupEntityToGroupDto(groupEntity);

                log.info(this.getClass().getName() + ".findItemInfoByGroup End!");
        return rDto;
    }
}

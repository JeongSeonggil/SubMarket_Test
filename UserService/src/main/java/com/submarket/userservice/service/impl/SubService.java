package com.submarket.userservice.service.impl;

import com.submarket.userservice.dto.SubDto;
import com.submarket.userservice.dto.UserDto;
import com.submarket.userservice.jpa.SubRepository;
import com.submarket.userservice.jpa.UserRepository;
import com.submarket.userservice.jpa.entity.SubEntity;
import com.submarket.userservice.jpa.entity.UserEntity;
import com.submarket.userservice.mapper.SubMapper;
import com.submarket.userservice.mapper.UserMapper;
import com.submarket.userservice.service.ISubService;
import com.submarket.userservice.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service(value = "SubService")
@Slf4j
public class SubService implements ISubService {
    private SubRepository subRepository;
    private UserRepository userRepository;
    private UserService userService;

    @Autowired
    public SubService(SubRepository subRepository, UserService userService, UserRepository userRepository) {
        this.subRepository = subRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }
    /** ------------------------- 구독 조회 ------------------------------*/
    @Override
    public List<SubEntity> findSub(SubDto subDto) throws RuntimeException{
        Optional<UserEntity> user = userRepository.findById(subDto.getUserSeq());

        if (user == null) {
            log.info("User 정보 없음");
            throw new EntityNotFoundException("User Entity Not Found");
        }
        Iterable<SubEntity> subEntityIterable = subRepository.findByUser(user.get());

        log.info(this.getClass().getName() + ". find Sub Info");

        List<SubEntity> subEntityList = new ArrayList<>();

        subEntityIterable.forEach(e -> {
            subEntityList.add(e);
        });
        return subEntityList;
    }

    /** ------------------------- 구독 생성 ------------------------------*/
    @Override
    public int createNewSub(SubDto subDto) {
        log.info(this.getClass().getName() + "createNewSub Start!");

        int res = 0;
        subDto.setUser(userRepository.findByUserId("dataofsg02")); // 수정 필요
        subDto.setSubDate(DateUtil.getDateTime("dd"));
        subDto.setSubCount(1);
        log.info("itemSeq : " + subDto.getItemSeq());

        SubEntity subEntity = SubMapper.INSTANCE.subDtoToSubEntity(subDto);

        log.info("subEntity (itemSeq) : " + subEntity.getItemSeq());
        subRepository.save(subEntity);

        res = 1;

        log.info(this.getClass().getName() + "createNewSub End");

        return res;
    }

    /** ------------------------- 구독 갱신 ------------------------------*/
    @Override
    public int updateSub(SubDto subDto) {
        log.info(this.getClass().getName() + ".updateSub Start!");

        String date = DateUtil.getDateTime(DateUtil.getDateTime("dd"));
        int res = subRepository.updateSub(date, subDto.getSubSeq());


        log.info(this.getClass().getName() + ".updateSub End!");
        return res;
    }


    /** ------------------------- 구독 취소 ------------------------------*/
    @Override
    public int cancelSub(SubDto subDto) {
        log.info(this.getClass().getName() + ".cancelSub Start!");
        subRepository.deleteById(subDto.getSubSeq());
        log.info(this.getClass().getName() + "cancelSub End!");
        return 1;
    }
}

package com.submarket.itemservice.controller;

import com.submarket.itemservice.dto.CategoryDto;
import com.submarket.itemservice.dto.GroupDto;
import com.submarket.itemservice.jpa.CategoryRepository;
import com.submarket.itemservice.jpa.ItemRepository;
import com.submarket.itemservice.jpa.entity.CategoryEntity;
import com.submarket.itemservice.mapper.CategoryMapper;
import com.submarket.itemservice.service.impl.GroupService;
import com.submarket.itemservice.service.impl.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MainController {

    private final Environment env;
    private final CategoryRepository categoryRepository;
    private final ItemService itemService;

    @GetMapping("/health")
    public String health() {
        log.info("UserService On");
        return env.getProperty("spring.application.name")
                + ", port(local.server.port) : " + env.getProperty("local.server.port")
                + ", port(server.port) : " + env.getProperty("server.port")
                + ", token secret : " + env.getProperty("token.secret")
                + ", token expiration time : " + env.getProperty("token.expiration_time");
    }

    @GetMapping("/test")
    @Transactional
    public CategoryDto test() throws Exception {

        Optional<CategoryEntity> category = categoryRepository.findById(1);

        CategoryEntity categoryEntity = category.get();

        CategoryDto categoryDto = new CategoryDto();

        categoryDto = CategoryMapper.INSTANCE.categoryEntityToCategoryDto(categoryEntity);


        return categoryDto;
    }

    @GetMapping("/groupTest")
    @Transactional
    public GroupDto groupTest() throws Exception {
        log.info(this.getClass().getName() + ".group Test");
        GroupDto groupDto = new GroupDto();

        groupDto.setGroupSeq(1);

        GroupDto rGroupDto = itemService.findItemInfoByGroup(groupDto);

        log.info("" + rGroupDto.getGroupSeq());
        log.info("" + rGroupDto.getGroupName());


        log.info(this.getClass().getName() + ".group Test");
        return rGroupDto;
    }
}

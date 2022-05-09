package com.submarket.userservice.service.impl;

import com.submarket.userservice.dto.SubDto;
import com.submarket.userservice.jpa.SubRepository;
import com.submarket.userservice.jpa.entity.SubEntity;
import com.submarket.userservice.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SchedulerService {
    /**
     * 특정 시간에 실행 Batch, and if (day == new Date()) == 결제 + 구독 정보 업데이트 로직 실행
     */
    private SubService subService;
    private SubRepository subRepository;

    @Autowired
    public SchedulerService(SubService subService, SubRepository subRepository) {
        this.subService = subService;
        this.subRepository = subRepository;
    }

    //    @Scheduled // 특정 시간에 실행
    @Scheduled(cron = "* * 9 * * *") // s, m, h, d, M (매일 9시에 구독 확인)
    @Async
    public void checkSub() throws Exception {
        log.info(this.getClass().getName() + ".checkSub Start!");

        Iterable<SubEntity> subEntityIterable = subRepository.findUpdateSub(DateUtil.getDateTime("dd"));
        // 오늘 날짜에 맞는 구독 정보 조회 ex.) 01, 02, 03

        if (subEntityIterable == null) {
            // 갱신할 내용이 없다면
            return;
        }

        SubDto subDto = new SubDto();

        subEntityIterable.forEach(subEntity -> {
            subDto.setSubSeq(subEntity.getSubSeq());


            /** 결제 모듈 추가 */


            // 구독 업데이트
            subService.updateSub(subDto);
        });


        log.info(this.getClass().getName() + ".checkSub End!");

    }
}
package com.submarket.userservice.controller;

import com.submarket.userservice.dto.SubDto;
import com.submarket.userservice.service.impl.SubService;
import com.submarket.userservice.vo.RequestSub;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
public class SubController {
    private SubService subService;

    @Autowired
    public SubController(SubService subService) {
        this.subService = subService;
    }

    @PostMapping("/sub")
    public ResponseEntity<String> createNewSub(@RequestBody RequestSub requestSub) {
        log.info(this.getClass().getName() + ".createNewSub Start!");

        SubDto subDto = new SubDto();
        subDto.setItemSeq(requestSub.getItemSeq());

        int res = subService.createNewSub(subDto);

        if (res != 1) {
            return ResponseEntity.status(500).body("오류");
        }

        log.info(this.getClass().getName() + ".createNewSub End! ");

        return ResponseEntity.status(HttpStatus.CREATED).body("구독 성공");
    }

    @DeleteMapping("/sub")
    public ResponseEntity<String> cancelSub(@RequestBody RequestSub requestSub) throws Exception {
        log.info(this.getClass().getName() + "cancel Sub Start!");

        SubDto subDto = new SubDto();

        subDto.setSubSeq(requestSub.getSubSeq());

        int res = subService.cancelSub(subDto);


        log.info(this.getClass().getName() + "cancel Sub End!");

        if (res != 1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("구독 취소 실패");
        }
        return ResponseEntity.status(HttpStatus.CONTINUE).body("구독 취소 성공");
    }

    @PatchMapping("/sub")
    public ResponseEntity<String> updateSub(@RequestBody RequestSub requestSub) throws Exception {
        log.info(this.getClass().getName() + ".updateSub Start!");
        SubDto subDto = new SubDto();
        subDto.setSubSeq(requestSub.getSubSeq());

        int res = subService.updateSub(subDto);

        if (res != 1) {
            return ResponseEntity.ok("갱신 실패");
        }

        log.info(this.getClass().getName() + "updateSub End!");
        return ResponseEntity.ok("갱신 완료");


    }
}

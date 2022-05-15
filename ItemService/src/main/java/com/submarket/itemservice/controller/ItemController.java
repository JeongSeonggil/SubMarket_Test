package com.submarket.itemservice.controller;

import com.submarket.itemservice.service.impl.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    // TODO: 2022/05/16 로직 추가
    @GetMapping("/items")
    public ResponseEntity<Object> findAllItem() throws Exception {
        return null;
    }

    @GetMapping("/items/{itemSeq}")
    public ResponseEntity<Object> findOneItem(@PathVariable int itemSeq) throws Exception {
        return null;
    }

    @PostMapping("/items")
    public ResponseEntity<Object> saveItem() throws Exception {
        return null;
    }

    @PatchMapping("/items")
    public ResponseEntity<Object> modifyItem() throws Exception {
        return null;
    }

    @DeleteMapping("/items/{itemSeq}")
    public ResponseEntity<Object> deleteItem(@PathVariable int itemSeq) throws Exception {
        // TODO: 2022/05/16 비활성화, 사업자 인증
        return null;
    }
}

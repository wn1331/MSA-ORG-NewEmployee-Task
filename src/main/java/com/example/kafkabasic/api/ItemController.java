package com.example.kafkabasic.api;

import com.example.kafkabasic.api.response.ItemResponseDto;
import com.example.kafkabasic.application.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api1/v1")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items")
    public ResponseEntity<List<ItemResponseDto>> getItems(){
        return ResponseEntity.ok(itemService.findAllItems());
    }
}

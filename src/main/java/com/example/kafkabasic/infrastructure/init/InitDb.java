package com.example.kafkabasic.infrastructure.init;

import com.example.kafkabasic.api.request.CreateItemRequestDto;
import com.example.kafkabasic.application.ItemService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final ItemService itemService;

    @PostConstruct
    public void init() {
        itemInitDb();
    }

    public void itemInitDb() {
        createItem();
    }

    private void createItem() {
        CreateItemRequestDto request1 = new CreateItemRequestDto("product1", 1000000, 10);
        CreateItemRequestDto request2 = new CreateItemRequestDto("product2", 1000000, 5);
        CreateItemRequestDto request3 = new CreateItemRequestDto("product3", 1000000, 0);
        itemService.createItem(request1);
        itemService.createItem(request2);
        itemService.createItem(request3);
    }
}

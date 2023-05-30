package com.shop.order.infrastructure.init;

import com.shop.order.api.request.CreateItemRequestDto;
import com.shop.order.application.ItemService;
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

    private void itemInitDb() {
        createItem();
    }

    private void createItem() {
        CreateItemRequestDto request1 = new CreateItemRequestDto("스프링", 50000, 10);
        CreateItemRequestDto request2 = new CreateItemRequestDto("카프카", 30000, 5);
        CreateItemRequestDto request3 = new CreateItemRequestDto("리액트", 10000, 1);
        itemService.createItem(request1);
        itemService.createItem(request2);
        itemService.createItem(request3);
    }
}

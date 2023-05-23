package com.example.kafkabasic.application;

import com.example.kafkabasic.api.request.CreateItemRequestDto;
import com.example.kafkabasic.api.response.ItemResponseDto;
import com.example.kafkabasic.domain.item.Item;
import com.example.kafkabasic.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public void createItem(CreateItemRequestDto request) {
        Item item = request.toEntity();
        itemRepository.save(item);
    }

    public List<ItemResponseDto> findAllItems() {
        return itemRepository.findAllByOrderById()
                .stream()
                .map(item -> new ItemResponseDto(item.getName(), item.getStockQuantity()))
                .collect(Collectors.toList());
    }
}

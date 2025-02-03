package com.alexey.spring.springboot.controller;

import com.alexey.spring.springboot.domain.dto.InventoryItemDTO;
import com.alexey.spring.springboot.domain.entity.InventoryItem;
import com.alexey.spring.springboot.mapper.InventoryItemMapper;
import com.alexey.spring.springboot.service.InventoryService;
import com.alexey.spring.springboot.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/inventory")
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;
    private final SearchService searchService;

    @Autowired
    public InventoryController(InventoryService inventoryService, SearchService searchService) {
        this.inventoryService = inventoryService;
        this.searchService = searchService;
    }

    @GetMapping
    public List<InventoryItemDTO> getAllItems() {
        return inventoryService.getAllItems().stream()
                .map(InventoryItemMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<InventoryItemDTO> searchItems(@RequestParam Map<String, String> filters) {
        List<InventoryItem> items;
        if (filters.containsKey("filter")) {
            items = searchService.searchByFilter(filters.get("filter"));
        } else {
            items = searchService.searchByWord(filters);
        }
        return items.stream()
                .map(InventoryItemMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public InventoryItemDTO addItem(@RequestBody InventoryItemDTO itemDTO) {
        log.info("ПОЛУЧЕННОЕ DTO: {}", itemDTO);
        try {
            InventoryItem item = InventoryItemMapper.toEntity(itemDTO);
            InventoryItem savedItem = inventoryService.addItem(item);
            return InventoryItemMapper.toDTO(savedItem);
        } catch (Exception e) {
            log.error("ОШИБКА ПОЛУЧЕНИЯ", e);
            throw e;
        }
    }


    @PatchMapping("/{id}")
    public InventoryItemDTO updateItem(@PathVariable Long id, @RequestBody Map<String, String> updates) {
        InventoryItem updatedItem = inventoryService.updateItem(id, updates);
        return InventoryItemMapper.toDTO(updatedItem);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteItemById(@PathVariable Long id) {
        inventoryService.deleteItemById(id);
    }
}

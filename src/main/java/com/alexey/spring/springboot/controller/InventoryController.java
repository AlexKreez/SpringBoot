package com.alexey.spring.springboot.controller;


import com.alexey.spring.springboot.service.SearchService;
import com.alexey.spring.springboot.springApplication.InventoryItem;
import com.alexey.spring.springboot.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    private final SearchService searchService;

    @Autowired
    public InventoryController(InventoryService inventoryService, SearchService searchService) {
        this.inventoryService = inventoryService;

        this.searchService = searchService;
    }


    @GetMapping
    public List<InventoryItem> getAllItems() {
        return inventoryService.getAllItems();
    }


    @GetMapping("/search")
    public List<InventoryItem> searchItems(@RequestParam Map<String, String> filters) {
        if (filters.containsKey("filter")) {
            return searchService.searchByFilter(filters.get("filter"));
        } else {
            return searchService.searchByWord(filters);
        }
    }


    @PostMapping("/add")
   public InventoryItem addItem(@RequestBody InventoryItem item) {
        return inventoryService.addItem(item);
   }


    @PatchMapping("/{id}")
    public InventoryItem UpdateItem(@PathVariable Long id, @RequestBody Map<String, String> updates) {
        return inventoryService.updateItem(id, updates);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteItemById(@PathVariable Long id) {
        inventoryService.deleteItemById(id);
    }

}

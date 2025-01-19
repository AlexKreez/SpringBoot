package com.alexey.spring.springboot.service;

import com.alexey.spring.springboot.springApplication.InventoryItem;
import com.alexey.spring.springboot.repository.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryItemRepository inventoryItemRepository;

    @Autowired
    public InventoryService(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }

    // Метод для получения всех записей
    public List<InventoryItem> getAllItems() {
        return inventoryItemRepository.findAll();
    }

    // Метод для поиска по "Наименованию материала/актива"
    public List<InventoryItem> getItemsByMaterialName(String materialName) {
        return inventoryItemRepository.findByMaterialName(materialName);
    }

    // Метод для добавления нового элемента
    public InventoryItem addItem(InventoryItem item) {
        return inventoryItemRepository.save(item);
    }

    // Метод для удаления элемента по ID
    public void deleteItemById(Long id) {
        inventoryItemRepository.deleteById(id);
    }

}

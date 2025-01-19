package com.alexey.spring.springboot.service;

import com.alexey.spring.springboot.springApplication.InventoryItem;
import com.alexey.spring.springboot.repository.InventoryItemRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private final InventoryItemRepository repository;

    public SearchService(InventoryItemRepository repository) {
        this.repository = repository;
    }

    public List<InventoryItem> searchByFilter(String filter) {
        return repository.findAll().stream()
                .filter(item -> matchesFilter(item, filter))
                .collect(Collectors.toList());
    }

    public List<InventoryItem> searchByWord(Map<String, String> filters) {
        return repository.findAll().stream()
                .filter(item -> filters.entrySet().stream()
                        .allMatch(filter -> matchesField(item, filter.getKey(), filter.getValue())))
                .collect(Collectors.toList());
    }

    private boolean matchesField(InventoryItem item, String fieldName, String value) {
        try {
            // Получаем поле по имени
            Field field = InventoryItem.class.getDeclaredField(fieldName);
            field.setAccessible(true);

            // Получаем значение поля
            Object fieldValue = field.get(item);

            // Проверяем, содержит ли значение искомый текст
            return fieldValue != null && fieldValue.toString().toLowerCase().contains(value.toLowerCase());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return false;
        }
    }

    private boolean matchesFilter(InventoryItem item, String filter) {
        if (filter == null || filter.isBlank()) {
            return true;
        }

        filter = filter.toLowerCase();

        // Получаем все поля класса InventoryItem
        for (Field field : InventoryItem.class.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                // Получаем значение текущего поля
                Object value = field.get(item);
                if (value != null && value.toString().toLowerCase().contains(filter)) {
                    return true;
                }
            } catch (IllegalAccessException e) {

                continue;
            }
        }

        return false;
    }


}
package com.alexey.spring.springboot.service;

import com.alexey.spring.springboot.domain.entity.InventoryItem;
import com.alexey.spring.springboot.repository.InventoryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Service
public class InventoryService {

    private final InventoryItemRepository inventoryItemRepository;

    @Autowired
    public InventoryService(InventoryItemRepository inventoryItemRepository) {
        this.inventoryItemRepository = inventoryItemRepository;
    }


    public List<InventoryItem> getAllItems() {
        return inventoryItemRepository.findAll();
    }

    public InventoryItem updateItem(Long id, Map<String, String> updates) {
        return inventoryItemRepository.findById(id).map(item -> {
            updates.forEach((fieldName, fieldValue) -> {
                try {
                    Field field = InventoryItem.class.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    field.set(item, convertValue(field, fieldValue));
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    throw new RuntimeException("НЕ ОБНОВЛЕНО ПОЛЕ: " + fieldName, e);
                }
            });
            return inventoryItemRepository.save(item);
        }).orElseThrow(() -> new RuntimeException("НЕ НАЙДЕНО ID" + id));
    }

    // Метод для преобразования значения в нужный тип
    private Object convertValue(Field field, String value) {
        Class<?> fieldType = field.getType();
        if (fieldType.equals(int.class) || fieldType.equals(Integer.class)) {
            return Integer.parseInt(value);
        } else if (fieldType.equals(double.class) || fieldType.equals(Double.class)) {
            return Double.parseDouble(value);
        } else if (fieldType.equals(float.class) || fieldType.equals(Float.class)) {
            return Float.parseFloat(value);
        } else if (fieldType.equals(long.class) || fieldType.equals(Long.class)) {
            return Long.parseLong(value);
        }
        return value;
    }



    public InventoryItem addItem(InventoryItem item) {
        return inventoryItemRepository.save(item);
    }


    public void deleteItemById(Long id) {
        inventoryItemRepository.deleteById(id);
    }

}

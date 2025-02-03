package com.alexey.spring.springboot.mapper;

import com.alexey.spring.springboot.domain.dto.InventoryItemDTO;
import com.alexey.spring.springboot.domain.entity.InventoryItem;

import java.lang.reflect.Field;

public class InventoryItemMapper {

    public static InventoryItem toEntity(InventoryItemDTO dto) {
        InventoryItem item = new InventoryItem();

        Field[] dtoFields = InventoryItemDTO.class.getDeclaredFields();
        Field[] entityFields = InventoryItem.class.getDeclaredFields();

        for (Field dtoField : dtoFields) {
            dtoField.setAccessible(true);
            try {
                Object value = dtoField.get(dto);
                if (value != null) {
                    for (Field entityField : entityFields) {
                        entityField.setAccessible(true);
                        if (dtoField.getName().equals(entityField.getName())) {
                            entityField.set(item, value);
                            break;
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("ОШИБКА ПРИОБРАЗОВАНИЯ ПОЛЯ: " + dtoField.getName(), e);
            }
        }
        return item;
    }


    public static InventoryItemDTO toDTO(InventoryItem item) {
        InventoryItemDTO dto = new InventoryItemDTO();

        Field[] entityFields = InventoryItem.class.getDeclaredFields();
        Field[] dtoFields = InventoryItemDTO.class.getDeclaredFields();

        for (Field entityField : entityFields) {
            entityField.setAccessible(true);
            try {
                Object value = entityField.get(item);
                if (value != null) {
                    for (Field dtoField : dtoFields) {
                        dtoField.setAccessible(true);
                        if (entityField.getName().equals(dtoField.getName())) {
                            dtoField.set(dto, value);
                            break;
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("ОШИБКА ПРИОБРАЗОВАНИЯ ПОЛЯ: " + entityField.getName(), e);
            }
        }
        return dto;
    }
}

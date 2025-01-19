package com.alexey.spring.springboot.repository;


import com.alexey.spring.springboot.springApplication.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryItemRepository extends JpaRepository<InventoryItem, Long> {


    List<InventoryItem> findByMaterialName(String materialName);


    List<InventoryItem> findByOrganizationCode(String organizationCode);


}

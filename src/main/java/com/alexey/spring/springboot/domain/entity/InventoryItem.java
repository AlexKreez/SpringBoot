package com.alexey.spring.springboot.domain.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name ="inv_table333")
@Data
public class InventoryItem {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "organization_code")
    private String organizationCode;

    @Column(name = "organization_name")
    private String organizationName;

    @Column(name = "sp_name")
    private String spName;

    @Column(name = "sp_code")
    private String spCode;

    @Column(name = "mol_name")
    private String molFio;

    @Column(name = "active_assignment_mol")
    private String activeAssignmentMol;

    @Column(name = "item_number")
    private String nomenclatureNumber;

    @Column(name = "item_name")
    private String materialName;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "inventory_number")
    private String inventoryNumber;

    @Column(name = "unit")
    private String unit;

    @Column(name = "personnel_number")
    private String tabNumber;

    @Column(name = "mol_login")
    private String molLogin;

    @Column(name = "balance_unit")
    private String balanceUnit;

    @Column(name = "sp_be")
    private String beSp;

    @Column(name = "current_account")
    private String currentWarehouseAccount;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "price")
    private String price;

    @Column(name = "cost_value")
    private String cost;

    @Column(name = "address")
    private String address;

    @Column(name = "file_id")
    private String fileId;

    @Column(name = "batch")
    private String inventoryName;

    @Column(name = "inventory_name")
    private String batch;

    @Column(name = "source_value")
    private String uploadSource;

    @Column(name = "source_system")
    private String sourceSystem;

    @Column(name = "center")
    private String co;

    @Column(name = "code_sa")
    private String saCode;

    @Column(name = "start_cost")
    private String initialCost;

    @Column(name = "last_cost")
    private String residualCost;

    @Column(name = "total_cost")
    private String costAccount;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "in_transit")
    private String inTransit;

    @Column(name = "mol2_name")
    private String mol2Fio;

    @Column(name = "mol2_active")
    private String mol2ActiveAssignment;

    @Column(name = "mol2_number")
    private String mol2TabNumber;

    @Column(name = "mol2_login")
    private String mol2Login;

    @Column(name = "mol3_name")
    private String mol3Fio;

    @Column(name = "mol3_active")
    private String mol3ActiveAssignment;

    @Column(name = "mol3_number")
    private String mol3TabNumber;

    @Column(name = "mol3_login")
    private String mol3Login;

    @Column(name = "mol4_name")
    private String mol4Fio;

    @Column(name = "mol4_active")
    private String mol4ActiveAssignment;

    @Column(name = "mol4_number")
    private String mol4TabNumber;

    @Column(name = "mol4_login")
    private String mol4Login;

    @Column(name = "inventory_type")
    private String inventoryType;

    @Column(name = "author_name")
    private String fileAuthorFio;

    @Column(name = "author_login")
    private String fileAuthorLogin;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "import_mistake")
    private String importErrors;

    @Column(name = "inventorization_mistake")
    private String inventoryErrors;

    @Column(name = "rik_code")
    private String rikCode;

    @Column(name = "rik_distribution_errors")
    private String rikErrors;
}

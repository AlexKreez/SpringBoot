package com.alexey.spring.springboot.springApplication;


import jakarta.persistence.*;

@Entity
@Table(name ="inv_table333")
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


    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getSpName() {
        return spName;
    }

    public void setSpName(String spName) {
        this.spName = spName;
    }

    public String getSpCode() {
        return spCode;
    }

    public void setSpCode(String spCode) {
        this.spCode = spCode;
    }

    public String getMolFio() {
        return molFio;
    }

    public void setMolFio(String molFio) {
        this.molFio = molFio;
    }

    public String getActiveAssignmentMol() {
        return activeAssignmentMol;
    }

    public void setActiveAssignmentMol(String activeAssignmentMol) {
        this.activeAssignmentMol = activeAssignmentMol;
    }

    public String getNomenclatureNumber() {
        return nomenclatureNumber;
    }

    public void setNomenclatureNumber(String nomenclatureNumber) {
        this.nomenclatureNumber = nomenclatureNumber;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(String inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTabNumber() {
        return tabNumber;
    }

    public void setTabNumber(String tabNumber) {
        this.tabNumber = tabNumber;
    }

    public String getMolLogin() {
        return molLogin;
    }

    public void setMolLogin(String molLogin) {
        this.molLogin = molLogin;
    }

    public String getBalanceUnit() {
        return balanceUnit;
    }

    public void setBalanceUnit(String balanceUnit) {
        this.balanceUnit = balanceUnit;
    }

    public String getBeSp() {
        return beSp;
    }

    public void setBeSp(String beSp) {
        this.beSp = beSp;
    }

    public String getCurrentWarehouseAccount() {
        return currentWarehouseAccount;
    }

    public void setCurrentWarehouseAccount(String currentWarehouseAccount) {
        this.currentWarehouseAccount = currentWarehouseAccount;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public void setInventoryName(String inventoryName) {
        this.inventoryName = inventoryName;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getUploadSource() {
        return uploadSource;
    }

    public void setUploadSource(String uploadSource) {
        this.uploadSource = uploadSource;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getCo() {
        return co;
    }

    public void setCo(String co) {
        this.co = co;
    }

    public String getSaCode() {
        return saCode;
    }

    public void setSaCode(String saCode) {
        this.saCode = saCode;
    }

    public String getInitialCost() {
        return initialCost;
    }

    public void setInitialCost(String initialCost) {
        this.initialCost = initialCost;
    }

    public String getResidualCost() {
        return residualCost;
    }

    public void setResidualCost(String residualCost) {
        this.residualCost = residualCost;
    }

    public String getCostAccount() {
        return costAccount;
    }

    public void setCostAccount(String costAccount) {
        this.costAccount = costAccount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getInTransit() {
        return inTransit;
    }

    public void setInTransit(String inTransit) {
        this.inTransit = inTransit;
    }

    public String getMol2Fio() {
        return mol2Fio;
    }

    public void setMol2Fio(String mol2Fio) {
        this.mol2Fio = mol2Fio;
    }

    public String getMol2ActiveAssignment() {
        return mol2ActiveAssignment;
    }

    public void setMol2ActiveAssignment(String mol2ActiveAssignment) {
        this.mol2ActiveAssignment = mol2ActiveAssignment;
    }

    public String getMol2TabNumber() {
        return mol2TabNumber;
    }

    public void setMol2TabNumber(String mol2TabNumber) {
        this.mol2TabNumber = mol2TabNumber;
    }

    public String getMol2Login() {
        return mol2Login;
    }

    public void setMol2Login(String mol2Login) {
        this.mol2Login = mol2Login;
    }

    public String getMol3Fio() {
        return mol3Fio;
    }

    public void setMol3Fio(String mol3Fio) {
        this.mol3Fio = mol3Fio;
    }

    public String getMol3ActiveAssignment() {
        return mol3ActiveAssignment;
    }

    public void setMol3ActiveAssignment(String mol3ActiveAssignment) {
        this.mol3ActiveAssignment = mol3ActiveAssignment;
    }

    public String getMol3TabNumber() {
        return mol3TabNumber;
    }

    public void setMol3TabNumber(String mol3TabNumber) {
        this.mol3TabNumber = mol3TabNumber;
    }

    public String getMol3Login() {
        return mol3Login;
    }

    public void setMol3Login(String mol3Login) {
        this.mol3Login = mol3Login;
    }

    public String getMol4Fio() {
        return mol4Fio;
    }

    public void setMol4Fio(String mol4Fio) {
        this.mol4Fio = mol4Fio;
    }

    public String getMol4ActiveAssignment() {
        return mol4ActiveAssignment;
    }

    public void setMol4ActiveAssignment(String mol4ActiveAssignment) {
        this.mol4ActiveAssignment = mol4ActiveAssignment;
    }

    public String getMol4TabNumber() {
        return mol4TabNumber;
    }

    public void setMol4TabNumber(String mol4TabNumber) {
        this.mol4TabNumber = mol4TabNumber;
    }

    public String getMol4Login() {
        return mol4Login;
    }

    public void setMol4Login(String mol4Login) {
        this.mol4Login = mol4Login;
    }

    public String getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(String inventoryType) {
        this.inventoryType = inventoryType;
    }

    public String getFileAuthorFio() {
        return fileAuthorFio;
    }

    public void setFileAuthorFio(String fileAuthorFio) {
        this.fileAuthorFio = fileAuthorFio;
    }

    public String getFileAuthorLogin() {
        return fileAuthorLogin;
    }

    public void setFileAuthorLogin(String fileAuthorLogin) {
        this.fileAuthorLogin = fileAuthorLogin;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getImportErrors() {
        return importErrors;
    }

    public void setImportErrors(String importErrors) {
        this.importErrors = importErrors;
    }

    public String getInventoryErrors() {
        return inventoryErrors;
    }

    public void setInventoryErrors(String inventoryErrors) {
        this.inventoryErrors = inventoryErrors;
    }

    public String getRikCode() {
        return rikCode;
    }

    public void setRikCode(String rikCode) {
        this.rikCode = rikCode;
    }

    public String getRikErrors() {
        return rikErrors;
    }

    public void setRikErrors(String rikErrors) {
        this.rikErrors = rikErrors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

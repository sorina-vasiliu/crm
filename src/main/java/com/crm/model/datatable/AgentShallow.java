package com.crm.model.datatable;

/**
 * Entity class for modeling a AgentShallow, in the datatable jquery.
 */
public class AgentShallow extends UserShallow {
    private int warehouseId;
    private String warehouseName;

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
}

package de.ustutt.iaas.lcm.labs.shop.model;

import java.util.UUID;

public class SupplyRequest {

    public SupplyRequest(UUID productID, int amount) {
        this.productID = productID;
        this.amount = amount;
    }

    /**
     * The product id of the supply request
     */
    private UUID productID;

    /**
     * The amount of the supply request
     */
    private int amount;

    /*
        Getter & Setter
     */

    public UUID getProductID() {
        return productID;
    }

    public void setProductID(UUID productID) {
        this.productID = productID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

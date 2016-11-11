package de.ustutt.iaas.lcm.labs.shop.model;

import java.util.UUID;

public class SupplierOffer {

    /**
     * The product id of the supply request
     */
    private UUID productID;

    /**
     * The amount of the supply offer
     */
    private int amount;

    /**
     * The price of the supply offer
     */
    private float price;

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

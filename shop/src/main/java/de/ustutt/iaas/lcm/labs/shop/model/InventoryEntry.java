package de.ustutt.iaas.lcm.labs.shop.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class InventoryEntry {

    /**
     * The entry's identifier
     */
    @Id
    @GeneratedValue
    protected UUID id;

    /**
     * Product
     */
    @OneToOne
    private Product product;

    /**
     * Amount of the product
     */
    private int amount;

    /**
     * Getter & Setter
     */

    public UUID getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

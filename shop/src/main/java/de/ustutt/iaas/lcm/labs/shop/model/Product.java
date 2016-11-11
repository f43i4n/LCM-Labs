package de.ustutt.iaas.lcm.labs.shop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
public class Product {

    /**
     * The products identifier
     */
    @Id
    @GeneratedValue
    protected UUID id;

    /**
     * The products name
     */
    private String name;

    /**
     * The products category
     */

    private String category;

    /**
     * The products producer
     */
    private String producer;

    /**
     * The products weight
     */
    private float weight;

    /**
     * The products price
     */
    private float price;

    /**
     *  Getter & Setter
     */

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

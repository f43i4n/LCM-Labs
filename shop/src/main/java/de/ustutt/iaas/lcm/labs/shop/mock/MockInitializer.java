package de.ustutt.iaas.lcm.labs.shop.mock;

import de.ustutt.iaas.lcm.labs.shop.model.InventoryEntry;
import de.ustutt.iaas.lcm.labs.shop.model.Product;
import de.ustutt.iaas.lcm.labs.shop.persistence.InventoryRepository;
import de.ustutt.iaas.lcm.labs.shop.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MockInitializer {

    private InventoryRepository inventoryRepository;
    private ProductRepository productRepository;

    @Autowired
    MockInitializer(InventoryRepository inventoryRepository, ProductRepository productRepository){
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    public void perform() {
        if (productRepository.count() > 0) {
            return; // already performed
        }

        for (int i=0;i<10;i++){
            Product createdProduct = productRepository.save(createProduct(i));
            InventoryEntry inventoryEntry = new InventoryEntry();
            inventoryEntry.setProduct(createdProduct);
            inventoryEntry.setAmount(randomInt());
            inventoryRepository.save(inventoryEntry);
        }
    }

    private Product createProduct(int suffix){
        Product product = new Product();
        product.setName("TestProduct" + suffix);
        product.setCategory("TestCategory");
        product.setProducer("TestProducer");
        product.setWeight(randomFloat());
        product.setPrice(randomFloat());
        return product;
    }

    private int randomInt(){
        return new Random().nextInt(10)+0;
    }

    private float randomFloat(){
        return new Random().nextFloat()+0;
    }
}

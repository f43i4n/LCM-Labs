package de.ustutt.iaas.lcm.labs.shop.persistence;

import de.ustutt.iaas.lcm.labs.shop.model.InventoryEntry;
import de.ustutt.iaas.lcm.labs.shop.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface InventoryRepository extends CrudRepository<InventoryEntry, UUID> {

    InventoryEntry findByProduct(Product product);
}

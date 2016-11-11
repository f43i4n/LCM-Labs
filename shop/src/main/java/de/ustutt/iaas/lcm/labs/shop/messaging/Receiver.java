package de.ustutt.iaas.lcm.labs.shop.messaging;

import de.ustutt.iaas.lcm.labs.shop.Application;
import de.ustutt.iaas.lcm.labs.shop.model.AcknowledgeMessage;
import de.ustutt.iaas.lcm.labs.shop.model.InventoryEntry;
import de.ustutt.iaas.lcm.labs.shop.model.Product;
import de.ustutt.iaas.lcm.labs.shop.model.SupplierOffer;
import de.ustutt.iaas.lcm.labs.shop.persistence.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;

import javax.jms.Message;

@Configuration
public class Receiver {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private InventoryRepository inventoryRepository;

    // Queues
    private final String BEST_OFFER_QUEUE = "BestOfferQueue";
    private final String CHECK_AVAILABILITY_QUEUE = "CheckAvailabilityQueue";
    private final String BUY_QUEUE = "BuyQueue";

    @JmsListener(destination = BEST_OFFER_QUEUE)
    public AcknowledgeMessage receiveMessage(SupplierOffer supplierOffer) {
    	
    	logger.error("received offer with product: " + supplierOffer.getProductID());

        // check if product is available in the inventory
        // if not send bad acknowledgement message
        if(!inventoryRepository.exists(supplierOffer.getProductID())){
            logger.error("Received product id=" + supplierOffer.getProductID() + " doesn't exist in the inventory");
            return new AcknowledgeMessage(false);
        }

        // update amount of product in the inventory
        InventoryEntry inventoryEntry = inventoryRepository.findOne(supplierOffer.getProductID());
        inventoryEntry.setAmount(inventoryEntry.getAmount() + supplierOffer.getAmount());
        inventoryRepository.save(inventoryEntry);

        logger.info("Received and saved product id=" + supplierOffer.getProductID() +
                " amount=" + supplierOffer.getAmount() +
                " price=" + supplierOffer.getPrice());

        return new AcknowledgeMessage(true);
    }
}

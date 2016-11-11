package de.ustutt.iaas.lcm.labs.shop.web;

import de.ustutt.iaas.lcm.labs.shop.Application;
import de.ustutt.iaas.lcm.labs.shop.model.InventoryEntry;
import de.ustutt.iaas.lcm.labs.shop.model.SupplyRequest;
import de.ustutt.iaas.lcm.labs.shop.persistence.InventoryRepository;
import org.apache.activemq.command.ActiveMQMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.UUID;

@RestController
@RequestMapping("/operations")
public class OperationsController {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    // Topics
    private final String SUPPLY_PUBSUB = "SupplyPubSub";

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private InventoryRepository inventoryRepository;

    /**
     * Method to send a supply request for all products
     * where the amout is under 5.
     *
     * @return HTML message
     */
    @RequestMapping(value = "/sendSupplyRequest", method = RequestMethod.GET)
    public String sendSupplyRequest() {

        // set pub sub mode
        jmsTemplate.setPubSubDomain(true);

        Iterable<InventoryEntry> inventory = inventoryRepository.findAll();

        int count = 0;
        for (InventoryEntry i : inventory){
            if(i.getAmount() < 5){
                count++;
                jmsTemplate.convertAndSend(SUPPLY_PUBSUB, new SupplyRequest(i.getId(),5));
                logger.info("Supply request send for product id=" + i.getId() + " and amount=" + 5);
            }
        }

        return "Supply request send for " + count + " Products. <a href='../../'>Back</a>";
    }
}

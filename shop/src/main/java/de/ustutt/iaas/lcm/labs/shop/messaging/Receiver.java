package de.ustutt.iaas.lcm.labs.shop.messaging;

import de.ustutt.iaas.lcm.labs.shop.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;

@Configuration
public class Receiver {

    @JmsListener(destination = "supplyerQueue")
    public void receiveMessage(String message) {
        System.out.println("Received: " + message);
    }

    @JmsListener(destination = "pubSub", containerFactory = "pubSubFactory")
    public void receiveProduct(Product product) {
        System.out.println("Received product: " + product.getName());
    }
}

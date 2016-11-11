package de.ustutt.iaas.lcm.labs.shop.messaging;

import de.ustutt.iaas.lcm.labs.shop.model.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;

import javax.jms.Message;

@Configuration
public class Receiver {

    @JmsListener(destination = "BestOfferQueue")
    public void receiveMessage(String message) {
        System.out.println("Received: " + message);
        // TODO send json message for ack
    }
}

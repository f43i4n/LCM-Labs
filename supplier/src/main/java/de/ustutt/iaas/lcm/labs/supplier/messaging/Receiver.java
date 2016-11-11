package de.ustutt.iaas.lcm.labs.supplier.messaging;

import java.util.Random;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.messaging.handler.annotation.Header;

import de.ustutt.iaas.lcm.labs.model.SupplierOffer;
import de.ustutt.iaas.lcm.labs.model.SupplyRequest;

@Configuration
public class Receiver {
	
	@Autowired
	private JmsTemplate jmsTemplate;

    @JmsListener(destination = "SupplyPubSub", containerFactory = "pubSubFactory")
    public void receiveProduct(SupplyRequest supplyRequest, @Header("jms_messageId") String messageId) {
        SupplierOffer offer = new SupplierOffer();
        
        offer.setAmount(supplyRequest.getAmount());
        offer.setProductID(supplyRequest.getProductID());
        offer.setPrice(offer.getAmount() * 10.0 * ( 1 + new Random().nextGaussian() * 0.1));
        
        
        jmsTemplate.convertAndSend("SupplierOfferingsQueue", offer, new MessagePostProcessor() {
			
			@Override
			public Message postProcessMessage(Message message) throws JMSException {
				message.setJMSCorrelationID(messageId);
				return message;
			}
		});
    }
}

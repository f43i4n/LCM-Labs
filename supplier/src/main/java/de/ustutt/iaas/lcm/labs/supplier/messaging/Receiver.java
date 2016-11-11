package de.ustutt.iaas.lcm.labs.supplier.messaging;

import java.util.Random;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TemporaryQueue;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;
import org.springframework.messaging.handler.annotation.Header;

import de.ustutt.iaas.lcm.labs.model.AcknowledgeMessage;
import de.ustutt.iaas.lcm.labs.model.SupplierOffer;
import de.ustutt.iaas.lcm.labs.model.SupplyRequest;

@Configuration
public class Receiver {
	
    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);
	
	@Autowired
	private JmsTemplate jmsTemplate;

    @JmsListener(destination = "SupplyPubSub", containerFactory = "pubSubFactory")
    public void receiveProduct(SupplyRequest supplyRequest, @Header("jms_messageId") String messageId, Session session) throws JMSException {
        SupplierOffer offer = new SupplierOffer();
        
        offer.setAmount(supplyRequest.getAmount());
        offer.setProductID(supplyRequest.getProductID());
        offer.setPrice(offer.getAmount() * 10.0 * ( 1 + new Random().nextGaussian() * 0.1));
        
        Queue temporaryReplyQueue = session.createTemporaryQueue();

        jmsTemplate.convertAndSend("SupplierOfferingsQueue", offer, new MessagePostProcessor() {
			
			@Override
			public Message postProcessMessage(Message message) throws JMSException {
				message.setJMSReplyTo(temporaryReplyQueue);
				message.setJMSCorrelationID(messageId);
				return message;
			}
		});
        
        jmsTemplate.setReceiveTimeout(2000);
        
        AcknowledgeMessage message = (AcknowledgeMessage)jmsTemplate.receiveAndConvert(temporaryReplyQueue);
        
        if(message != null) {
            logger.info("ack: " + message.getAcknowledgement());
        } else {
        	logger.info("no ack received");
        }
    }
}

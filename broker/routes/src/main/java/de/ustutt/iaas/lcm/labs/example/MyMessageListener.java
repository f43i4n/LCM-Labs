package de.ustutt.iaas.lcm.labs.example;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageListener implements MessageListener {

	public void onMessage(Message message) {
		System.out.println("MyMessageListener");
		if (message instanceof TextMessage) {
			try {
				System.out.println("Received text message with: "+((TextMessage)message).getText());
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("Received non-text message.");
		}
	}

}
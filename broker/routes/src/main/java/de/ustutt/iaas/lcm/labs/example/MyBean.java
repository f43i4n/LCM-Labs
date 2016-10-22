package de.ustutt.iaas.lcm.labs.example;

import org.apache.camel.Exchange;
import org.apache.camel.Message;

public class MyBean {

	public void methodA(Message msg, Exchange ex) {
		System.out.println("MyBean, methodA, body is: "+msg.getBody(String.class));
		msg.setBody("+++"+msg.getBody(String.class)+"+++");
		System.out.println("MyBean, methodA, body is now: "+msg.getBody(String.class));
	}

	public void methodB(String body) {
		System.out.println("MyBean, methodB, body is: "+body);
	}

}
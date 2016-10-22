package de.ustutt.iaas.lcm.labs.example;

import org.apache.camel.builder.RouteBuilder;

public class MyRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		// the language used in e.g. logging expressions: http://camel.apache.org/simple.html
		
		// example route 1, moving messages from A to B
		from("activemq:A")
		.log("message in (message ${id}, exchange ${exchangeId})")
		.delay(5000)
		.log("message out (message ${id}, exchange ${exchangeId})")
		.to("activemq:B")
		.log("route done (message ${id}, exchange ${exchangeId})");
		
		// example rout 2, containing a JMS message handler bean
		from("activemq:C")
		.bean(MyMessageListener.class);
		
		// example route 3, containing a java bean (http://camel.apache.org/bean.html, http://camel.apache.org/bean-binding.html)
		from("activemq:D")
		.bean(MyBean.class, "methodA")
		.bean(MyBean.class, "methodB");

	}

}
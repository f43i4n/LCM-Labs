package de.ustutt.iaas.lcm.labs.routes;

import org.apache.camel.builder.RouteBuilder;

public class RouteConfigurationBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("activemq:SupplierOfferingsQueue")
			.aggregate(new BestSupplierOfferAggregationStrategy())
			.header("JMSCorrelationID")
			.completionTimeout(3000L)
			.to("activemq:BestOfferQueue");
	}

}

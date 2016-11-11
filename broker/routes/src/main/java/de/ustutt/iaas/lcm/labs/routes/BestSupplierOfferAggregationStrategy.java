package de.ustutt.iaas.lcm.labs.routes;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.json.JSONObject;

public class BestSupplierOfferAggregationStrategy implements AggregationStrategy {

	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		if(oldExchange == null) {
			return newExchange;
		}
		
		JSONObject oldBid = new JSONObject(oldExchange.getIn().getBody(String.class));
		JSONObject newBid = new JSONObject(newExchange.getIn().getBody(String.class));
		
		if(newBid.getJSONObject("body").getDouble("price") < oldBid.getJSONObject("body").getDouble("price")) {
			return newExchange;
		} else {
			return oldExchange;
		}
	}

	
	
}

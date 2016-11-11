package de.ustutt.iaas.lcm.labs.model;

import java.util.UUID;

public class SupplyRequest {
	
	private UUID productID;
	
	private int amount;
	
	public int getAmount() {
		return amount;
	}
	
	public UUID getProductID() {
		return productID;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void setProductID(UUID productID) {
		this.productID = productID;
	}
}

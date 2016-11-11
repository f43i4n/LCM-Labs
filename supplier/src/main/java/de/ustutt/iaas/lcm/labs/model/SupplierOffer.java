package de.ustutt.iaas.lcm.labs.model;

import java.util.UUID;

public class SupplierOffer {
	
	private UUID productID;
	private int amount;
	private double price;
	
	public int getAmount() {
		return amount;
	}
	
	public double getPrice() {
		return price;
	}
	
	public UUID getProductID() {
		return productID;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setProductID(UUID productID) {
		this.productID = productID;
	}
}

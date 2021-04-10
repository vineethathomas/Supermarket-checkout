package supermarket.checkout.shoppingitem;

import java.math.BigDecimal;

public enum Item {
	
	 // Four Items with prices
    A ("A", 60.00), B ("B", 45.00), C ("C", 20.00), R ("R", 10.00);
	
	private String name;
	private BigDecimal price;
	private Boolean hasDiscountApplied = false;
	
	private Item(String name, double price) {
		this.name = name;
		this.price = new BigDecimal(price);
	}
	
	
	public String getName() {
		return name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(double newPrice) {
		price = new BigDecimal(newPrice);
	}

	public void markAsDiscounted() {
		hasDiscountApplied = true;
	}

	public Boolean hasDiscountApplied() {
		return hasDiscountApplied;
	}

}

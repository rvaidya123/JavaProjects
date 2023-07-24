package com.onlineshopping.order;

import com.onlineshopping.item.Item;

public class CartItem extends Item{
	private int quantity;
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
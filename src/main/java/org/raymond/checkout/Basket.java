/**
 * Javabean for Basket data structure
 */
package org.raymond.checkout;

import java.util.HashMap;

/**
 * Items with data structure HashMap<String,Integer> <br>
 * String: item's name <br>
 * Integer: item's quantity
 */
public class Basket {
	private HashMap<String, Integer> items;

	public Basket(){
		this.items = new HashMap<String, Integer>();
	}
	
	public HashMap<String, Integer> getItems() {
		return items;
	}

	public void setItems(HashMap<String, Integer> items) {
		this.items = items;
	}

}

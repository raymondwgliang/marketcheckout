/**
 * Description:
 * Checkout service to simulate that in the super market when an item added to the basket 
 * and how much it will be cost totally
 */
package org.raymond.checkout;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class CheckoutService {
	/**
	 * Call PriceDao to retrieve Unit Price/Special Offer Price/Special Offer
	 * Factor from DB or properties file
	 */
	private final PriceDao priceDao;

	/**
	 * Constructor of CheckoutService to invoke PriceDao
	 * 
	 * @param priceDao
	 */
	public CheckoutService(PriceDao priceDao) {
		this.priceDao = priceDao;
	}

	/**
	 * calculateTotalPriceAfterAddItem(Basket basket, String addItem) simulate
	 * that the cashier is scanning an item and customer put it in the basket
	 * and how much it will be cost totally showing on the screen
	 * @param basket
	 * @param addItem
	 * @return
	 */
	public int calculateTotalPriceAfterAddItem(Basket basket, String addItem) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> mapItems = basket.getItems();
		int totalReturn = 0;
		int existCountOfAddItem = 0;
		Iterator<Entry<String, Integer>> it = mapItems.entrySet().iterator();
		// calculate the total value of the existing items in the basket
		while (it.hasNext()) {
			Map.Entry<String, Integer> entryItem = (Map.Entry<String, Integer>) it.next();
			int count = entryItem.getValue();
			totalReturn += priceDao.getUnitPrice(entryItem.getKey()) * count;
			if (entryItem.getKey().equals(addItem)) {
				existCountOfAddItem = entryItem.getValue();
				System.out.println("exist count of add item[" + addItem + "]=" + existCountOfAddItem);
			}
			if (priceDao.getSpecialPriceOfferFactor(entryItem.getKey()) != 0) {
				if (entryItem.getValue() % priceDao.getSpecialPriceOfferFactor(entryItem.getKey()) == 0) {
					totalReturn -= priceDao.getSpecialPriceOfferDeduction(entryItem.getKey());
				}
			}
		}
		System.out.println("existing basket total cost=" + totalReturn);
		// calculate the total value after adding an item
		totalReturn += priceDao.getUnitPrice(addItem);
		// current quantity of the new added item plus ONE
		existCountOfAddItem++;
		System.out.println("current count after add item[" + addItem + "]=" + existCountOfAddItem);
		// if the quantity meets the special offer condition give a deduction
		// from the total cost value
		if (priceDao.getSpecialPriceOfferFactor(addItem) != 0) {
			if (existCountOfAddItem % priceDao.getSpecialPriceOfferFactor(addItem) == 0) {
				totalReturn -= priceDao.getSpecialPriceOfferDeduction(addItem);
			}
		}
		System.out.println("final total value cost =" + totalReturn);
		System.out.println("---------------------------------------------");
		return totalReturn;
	}
	
	/**
	 * Update basket items after calculation
	 * @param basket
	 * @param addItem
	 */
	public void updateBasket(Basket basket, String addItem) {
		HashMap<String, Integer> mapItems = basket.getItems();
		if(!mapItems.containsKey(addItem)||mapItems.get(addItem)==null||mapItems.get(addItem).equals("")){
			mapItems.put(addItem, 1);
		}else{
			int count = mapItems.get(addItem);
			mapItems.put(addItem, ++count);
			basket.setItems(mapItems);
		}
	}
}

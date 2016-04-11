/**
 * Description: 
 * This is a simulator as the Cashier scan the items 
 * and the systems calculate the total values which show to the customer
 */

package org.raymond.checkout;

public class SimulatorPriceScanner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Basket basket = new Basket();
		PriceService priceService = new PriceService();
		CheckoutService checkoutService = new CheckoutService(priceService);
		checkoutService.calculateTotalPriceAfterAddItem(basket, "A");
		checkoutService.updateBasket(basket, "A");
		checkoutService.calculateTotalPriceAfterAddItem(basket, "B");
		checkoutService.updateBasket(basket, "B");
		checkoutService.calculateTotalPriceAfterAddItem(basket, "C");
		checkoutService.updateBasket(basket, "C");
		checkoutService.calculateTotalPriceAfterAddItem(basket, "D");
		checkoutService.updateBasket(basket, "D");
		checkoutService.calculateTotalPriceAfterAddItem(basket, "A");
		checkoutService.updateBasket(basket, "A");
		checkoutService.calculateTotalPriceAfterAddItem(basket, "A");
		checkoutService.updateBasket(basket, "A");
		checkoutService.calculateTotalPriceAfterAddItem(basket, "B");
		checkoutService.updateBasket(basket, "B");
		checkoutService.calculateTotalPriceAfterAddItem(basket, "C");
		checkoutService.updateBasket(basket, "C");
		checkoutService.calculateTotalPriceAfterAddItem(basket, "D");
		checkoutService.updateBasket(basket, "D");
		checkoutService.calculateTotalPriceAfterAddItem(basket, "B");
		checkoutService.updateBasket(basket, "B");
		checkoutService.calculateTotalPriceAfterAddItem(basket, "B");
		checkoutService.updateBasket(basket, "B");
		checkoutService.calculateTotalPriceAfterAddItem(basket, "B");
		checkoutService.updateBasket(basket, "B");

	}

}

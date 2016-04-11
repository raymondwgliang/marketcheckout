/**
 * JUnit Test With Mockito
 * Please import the whole project and run it with Maven
 */
package org.raymond.checkout;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.raymond.checkout.CheckoutService;

public class CheckoutServiceTest {

	private Basket basket;
	private PriceDao priceDao;
	private CheckoutService checkoutService;

	/**
	 * Description: Mock PriceDao with Mockito Assume that can get the <br>
	 * 1.Unit Price 2.Special Offer Price 3.Special Offer Factor from DB or properties <br>
	 * 1.Unit Price: price for a unit item <br>
	 * 2.Special Offer Price: when it meets the "Special Offer Factor", the deducted price from the total value <br>
	 * 3.Special Offer Factor: when the quantity of items meets the special offer condition
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		priceDao = mock(PriceDao.class);
		when(priceDao.getUnitPrice("A")).thenReturn(50);
		when(priceDao.getSpecialPriceOfferDeduction("A")).thenReturn(20);
		when(priceDao.getSpecialPriceOfferFactor("A")).thenReturn(3);

		when(priceDao.getUnitPrice("B")).thenReturn(30);
		when(priceDao.getSpecialPriceOfferDeduction("B")).thenReturn(15);
		when(priceDao.getSpecialPriceOfferFactor("B")).thenReturn(2);

		when(priceDao.getUnitPrice("C")).thenReturn(20);
		when(priceDao.getSpecialPriceOfferDeduction("C")).thenReturn(0);
		when(priceDao.getSpecialPriceOfferFactor("C")).thenReturn(0);

		when(priceDao.getUnitPrice("D")).thenReturn(15);
		when(priceDao.getSpecialPriceOfferDeduction("D")).thenReturn(0);
		when(priceDao.getSpecialPriceOfferFactor("D")).thenReturn(0);

		MockitoAnnotations.initMocks(this);
		checkoutService = new CheckoutService(priceDao);
	}

	/**
	 * Test Case for adding an item A to the empty basket Expected result: 50
	 * 
	 * @throws Exception
	 */
	@Test
	public void testTotalCostWhenEmptyBasketAddItemA() throws Exception {
		System.out.println("testTotalCostWhenEmptyBasketAddItem[A]");
		basket = new Basket();
		HashMap<String, Integer> items = new HashMap<String, Integer>();
		basket.setItems(items);
		int totalPrice = checkoutService.calculateTotalPriceAfterAddItem(basket, "A");
		Assert.assertEquals(50, totalPrice);
	}

	/**
	 * Test Case for adding an item A to the existing basket with items A,B,C,D
	 * Expected result: 165
	 * 
	 * @throws Exception
	 */
	@Test
	public void testTotalCostWhenBasketHasABCDAfterAddItemA() throws Exception {
		System.out.println("testTotalCostWhenBasketHas[ABCD]AfterAddItem[A]");
		basket = new Basket();
		HashMap<String, Integer> items = new HashMap<String, Integer>();
		items.put("A", 1);
		items.put("B", 1);
		items.put("C", 1);
		items.put("D", 1);
		basket.setItems(items);
		int totalPrice = checkoutService.calculateTotalPriceAfterAddItem(basket, "A");
		Assert.assertEquals(165, totalPrice);
	}

	/**
	 * Test Case for adding an item A to the existing basket with items
	 * A,A,B,C,D Expected result: 195
	 * 
	 * @throws Exception
	 */
	@Test
	public void testTotalCostWhenBasketHas2ABCDAfterAddItemA() throws Exception {
		System.out.println("testTotalCostWhenBasketHas[2ABCD]AfterAddItem[A]");
		basket = new Basket();
		HashMap<String, Integer> items = new HashMap<String, Integer>();
		items.put("A", 2);
		items.put("B", 1);
		items.put("C", 1);
		items.put("D", 1);
		basket.setItems(items);
		int totalPrice = checkoutService.calculateTotalPriceAfterAddItem(basket, "A");
		Assert.assertEquals(195, totalPrice);
	}

	/**
	 * Test Case for adding an item B to the existing basket with items
	 * A,A,A,B,C,D Expected result: 210
	 * 
	 * @throws Exception
	 */
	@Test
	public void testTotalCostWhenBasketHas3ABCDAfterAddItemB() throws Exception {
		System.out.println("testTotalCostWhenBasketHas[3ABCD]AfterAddItem[B]");
		basket = new Basket();
		HashMap<String, Integer> items = new HashMap<String, Integer>();
		items.put("A", 3);
		items.put("B", 1);
		items.put("C", 1);
		items.put("D", 1);
		basket.setItems(items);
		int totalPrice = checkoutService.calculateTotalPriceAfterAddItem(basket, "B");
		Assert.assertEquals(210, totalPrice);
	}

	/**
	 * Test Case for adding an item C to the existing basket with items
	 * A,A,A,B,B,C,D Expected result: 230
	 * 
	 * @throws Exception
	 */
	@Test
	public void testTotalCostWhenBasketHas3A2BCDAfterAddItemC() throws Exception {
		System.out.println("testTotalCostWhenBasketHas[3A2BCD]AfterAddItem[C]");
		basket = new Basket();
		HashMap<String, Integer> items = new HashMap<String, Integer>();
		items.put("A", 3);
		items.put("B", 2);
		items.put("C", 1);
		items.put("D", 1);
		basket.setItems(items);
		int totalPrice = checkoutService.calculateTotalPriceAfterAddItem(basket, "C");
		Assert.assertEquals(230, totalPrice);
	}

	/**
	 * Test Case for adding an item D to the existing basket with items
	 * A,A,A,B,B,C,C,D Expected result: 245
	 * 
	 * @throws Exception
	 */
	@Test
	public void testTotalCostWhenBasketHas3A2B2CDAfterAddItemD() throws Exception {
		System.out.println("testTotalCostWhenBasketHas[3A2B2CD]AfterAddItem[D]");
		basket = new Basket();
		HashMap<String, Integer> items = new HashMap<String, Integer>();
		items.put("A", 3);
		items.put("B", 2);
		items.put("C", 2);
		items.put("D", 1);
		basket.setItems(items);
		int totalPrice = checkoutService.calculateTotalPriceAfterAddItem(basket, "D");
		Assert.assertEquals(245, totalPrice);
	}

	/**
	 * Test Case for adding an item B to the existing basket with items
	 * A,B,B,B,C,D Expected result: 175
	 * 
	 * @throws Exception
	 */
	@Test
	public void testTotalCostWhenBasketHasA3BCDAfterAddItemB() throws Exception {
		System.out.println("testTotalCostWhenBasketHas[A3BCD]AfterAddItem[B]");
		basket = new Basket();
		HashMap<String, Integer> items = new HashMap<String, Integer>();
		items.put("A", 1);
		items.put("B", 3);
		items.put("C", 1);
		items.put("D", 1);
		basket.setItems(items);
		int totalPrice = checkoutService.calculateTotalPriceAfterAddItem(basket, "B");
		Assert.assertEquals(175, totalPrice);
	}
	
	/**
	 * Test Case for adding an item B to the existing basket with items
	 * A,B,B,B,B,C,D Expected result: 205
	 * 
	 * @throws Exception
	 */
	@Test
	public void testTotalCostWhenBasketHasA4BCDAfterAddItemB() throws Exception {
		System.out.println("testTotalCostWhenBasketHas[A4BCD]AfterAddItem[B]");
		basket = new Basket();
		HashMap<String, Integer> items = new HashMap<String, Integer>();
		items.put("A", 1);
		items.put("B", 4);
		items.put("C", 1);
		items.put("D", 1);
		basket.setItems(items);
		int totalPrice = checkoutService.calculateTotalPriceAfterAddItem(basket, "B");
		Assert.assertEquals(205, totalPrice);
	}
	
	/**
	 * Test Case for adding an item B to the existing basket with items
	 * A,A,A,B,B,C,C,D,D Expected result: 275
	 * 
	 * @throws Exception
	 */
	@Test
	public void testTotalCostWhenBasketHas3A2B2C2DAfterAddItemB() throws Exception {
		System.out.println("testTotalCostWhenBasketHas[3A2B2C2D]AfterAddItem[B]");
		basket = new Basket();
		HashMap<String, Integer> items = new HashMap<String, Integer>();
		items.put("A", 3);
		items.put("B", 2);
		items.put("C", 2);
		items.put("D", 2);
		basket.setItems(items);
		int totalPrice = checkoutService.calculateTotalPriceAfterAddItem(basket, "B");
		Assert.assertEquals(275, totalPrice);
	}
	
	/**
	 * Test Case for adding an item B to the existing basket with items
	 * A,A,A,B,B,B,C,C,D,D Expected result: 290
	 * 
	 * @throws Exception
	 */
	@Test
	public void testTotalCostWhenBasketHas3A3B2C2DAfterAddItemB() throws Exception {
		System.out.println("testTotalCostWhenBasketHas[3A3B2C2D]AfterAddItem[B]");
		basket = new Basket();
		HashMap<String, Integer> items = new HashMap<String, Integer>();
		items.put("A", 3);
		items.put("B", 3);
		items.put("C", 2);
		items.put("D", 2);
		basket.setItems(items);
		int totalPrice = checkoutService.calculateTotalPriceAfterAddItem(basket, "B");
		Assert.assertEquals(290, totalPrice);
	}
	
	/**
	 * Test Case for adding an item A to the existing basket with items
	 * A,B,C,D add item A
	 * 
	 * @throws Exception
	 */
	@Test
	public void testUpdateBasketWithABCDAddItemA() throws Exception {
		System.out.println("testUpdateBasketWith[ABCD]AddItem[A]");
		basket = new Basket();
		HashMap<String, Integer> items = new HashMap<String, Integer>();
		items.put("A", 1);
		items.put("B", 1);
		items.put("C", 1);
		items.put("D", 1);
		basket.setItems(items);
		checkoutService.updateBasket(basket, "A");
		Assert.assertEquals(2, basket.getItems().get("A").intValue());
	}
	
}

/**
 * Interface of PriceDao to retrieve data from DB or properties file
 * Can be further implemented by other team mates for multiple development
 */
package org.raymond.checkout;

public interface PriceDao {
	/**
	 * Price for a unit item
	 * @param item
	 * @return int Unit Price 
	 */
	public int getUnitPrice(String item);
	
	/**
	 * Quantity when the items meets the special offer condition
	 * @param item
	 * @return int Special Offer Factor
	 */
	public int getSpecialPriceOfferFactor(String item);
	
	/**
	 * When it meets the "Special Offer Factor", the deducted price from the total value
	 * @param item
	 * @return int Special Offer Price
	 */
	public int getSpecialPriceOfferDeduction(String item);
}

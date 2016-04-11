/**
 * Description:
 * Below class get the configuration
 * from the special_offer.properties
 * for "Unit Price","Special Price Offer Factor",
 * and calculate "Special Price Offer Deduction"
 */
package org.raymond.checkout;

import java.util.HashMap;
import org.raymond.checkout.PriceDao;

public class PriceService implements PriceDao {

	private final String UNIT_PRICE = "UnitPrice";
	private final String SPECIAL_PRICE_OFFER_FACTOR = "SpecialPriceOfferFactor";
	private final String SPECIAL_PRICE_OFFER_TOTAL = "SpecialPriceOfferTotal";
	private final String DELIMITER = ".";

	/**
	 * get "Unit Price" from configuration in properties
	 */
	public int getUnitPrice(String item) {
		HashMap<String, String> specialOfferMap = PropertiesUtil.readProperties();
		String strUnitPrice = specialOfferMap.get(item + DELIMITER + UNIT_PRICE);
		return Integer.parseInt(strUnitPrice);
	}

	/**
	 * get "Special Price Offer Factor" from configuration in properties
	 */
	public int getSpecialPriceOfferFactor(String item) {
		HashMap<String, String> specialOfferMap = PropertiesUtil.readProperties();
		String strSpecialPriceOfferFactor = specialOfferMap.get(item + DELIMITER + SPECIAL_PRICE_OFFER_FACTOR);
		return Integer.parseInt(strSpecialPriceOfferFactor);
	}

	/**
	 * calculate "Special Price Offer Deduction" from configuration in properties
	 */
	public int getSpecialPriceOfferDeduction(String item) {
		HashMap<String, String> specialOfferMap = PropertiesUtil.readProperties();
		String strUnitPrice = specialOfferMap.get(item + DELIMITER + UNIT_PRICE);
		String strSpecialPriceOfferFactor = specialOfferMap.get(item + DELIMITER + SPECIAL_PRICE_OFFER_FACTOR);
		String strSpecialPriceOfferTotal = specialOfferMap.get(item + DELIMITER + SPECIAL_PRICE_OFFER_TOTAL);
		int intSpecialPriceOfferDeduction = Integer.parseInt(strUnitPrice)*Integer.parseInt(strSpecialPriceOfferFactor)
				- Integer.parseInt(strSpecialPriceOfferTotal);
		return intSpecialPriceOfferDeduction;
	}

}

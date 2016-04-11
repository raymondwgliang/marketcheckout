/**
 * Description:
 * Below class get the configuration as global variables from the special_offer.properties
 */
package org.raymond.checkout;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public final class PropertiesUtil {
	private final static String fileName = "/special_offer.properties";
	
	public static final HashMap<String,String> readProperties() {
		InputStream in = null;
		Properties pros = new Properties();
		HashMap<String,String> specialOfferMap = new HashMap<String,String>();
		try {
			if (null != fileName) {
				in = Object.class.getResourceAsStream(fileName);
				pros.load(in);
				Enumeration en = pros.propertyNames();
				while (en.hasMoreElements()) {
					String key = (String) en.nextElement();
					specialOfferMap.put(key, pros.getProperty(key));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("read file error");
		} finally {
			try {
				if (null != in) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("close stream error");
			}
		}
		return specialOfferMap;
	}

}

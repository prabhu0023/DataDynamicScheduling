package com.datadynamicscheduling.common.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class PropertyReader {

	private PropertyReader() {
	}

	public static Map<String, String> getDBProperties() {
		ResourceBundle rb = ResourceBundle.getBundle("db");
		Map<String, String> map = new HashMap<>();
		Enumeration<String> keys = rb.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			String value = rb.getString(key);
			System.out.println(key + ": " + value);
			map.put(key, value);
		}
		return map;
	}

}

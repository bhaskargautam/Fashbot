package com.domain.resources;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class Session {
	
	private static HashMap<String, String> sessions_arrive_day = new HashMap<String, String>();
	private static HashMap<String, String> sessions_order_price = new HashMap<String, String>();
	
	public static void addSession(String sessionId) {
		Date arrive_date = new Date(System.currentTimeMillis() + 86400 * 1000 * (new Random()).nextInt(10)); 
		sessions_arrive_day.put(sessionId, arrive_date.toString());
		Float price = (new Random()).nextFloat() * 10 + 10;
		sessions_order_price.put(sessionId, price.toString());
	}

	public static String getArrivalDate(String sessionId) {
		return sessions_arrive_day.get(sessionId);
	}
	
	public static String getOrderPrice(String sessionId) {
		return sessions_order_price.get(sessionId);
	}
}

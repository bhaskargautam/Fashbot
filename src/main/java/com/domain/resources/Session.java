package com.domain.resources;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**
 * DATA CACHE: Maps user's session id an expected arrival date and Order Price.
 * Randomly Assigns a date within next 10 days.
 * Randomly Assigns Order price between 10 and 200.
 * @author bhaskargautam
 *
 */
public class Session {

    private static Integer twoDaysInMs = 86400000;
    private static Integer deliveryRange = 10;

     /**
     * Hashmap to store session_id with expected arrival date.
     */
     private static HashMap<String, String> sessionsArriveDay =
               new HashMap<String, String>();

     /**
      * Hashmap to store session_id with expected order price.
      */
     private static HashMap<String, String> sessionsOrderPrice =
                new HashMap<String, String>();

     /**
      * Create a new session for the user.
      * Assigns random values.
      * @param sessionId Unique ID for session
      */
     public static void addSession(final String sessionId) {
        Integer extraTime = twoDaysInMs * (new Random()).nextInt(deliveryRange);
        Date arriveDate = new Date(System.currentTimeMillis() + extraTime);
        sessionsArriveDay.put(sessionId, arriveDate.toString());
        Float price = (new Random()).nextFloat() *  deliveryRange
                + deliveryRange;
        sessionsOrderPrice.put(sessionId, String.format(".2f", price));
     }

     /**
      * Returns Expected Date of Arrival of Order.
      * @param sessionId Unique ID for session
      * @return expected arrival date
      */
     public static String getArrivalDate(final String sessionId) {
         return sessionsArriveDay.get(sessionId);
     }

     /**
      * Returns the Price of the Order.
      * @param sessionId Unique ID for session
      * @return Price of the order
      */
     public static String getOrderPrice(final String sessionId) {
         return sessionsOrderPrice.get(sessionId);
     }
}

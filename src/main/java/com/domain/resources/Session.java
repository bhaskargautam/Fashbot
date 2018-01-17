package com.domain.resources;

import java.text.SimpleDateFormat;
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
public final class Session {

    /**
     * Represents 2 days in milliseconds.
     */
    private static final Integer TWO_DAYS_IN_MS = 86400000;

    /**
     * Represents Maximum days a delivery takes.
     */
    private static final Integer MAX_DELIVERY_DAYS = 10;

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
        if (sessionsArriveDay.containsKey(sessionId)) {
            return;
        }

        Integer deliveryInDays = (new Random()).nextInt(MAX_DELIVERY_DAYS);
        Integer extraTime = TWO_DAYS_IN_MS * deliveryInDays;
        Date arriveDate = new Date(System.currentTimeMillis() + extraTime);
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd, hh:mm");
        System.out.println(format.format(arriveDate));
        sessionsArriveDay.put(sessionId, format.format(arriveDate));
        Float price = (new Random()).nextFloat() *  MAX_DELIVERY_DAYS
                + MAX_DELIVERY_DAYS;
        sessionsOrderPrice.put(sessionId, String.format("%.2f", price));
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

     /**
      * Private Constructor to prevent instantiation.
     * @throws Exception Cannot be instantiated.
      */
     private Session() throws Exception {
         throw new Exception("Session class provides static methods."
                 + " Instantiation not needed");
     }

     /**
      * Public method to get instant of Session.
      * Should throw an Error since Session cannot be instantiated.
      * @return Session object.
      * @throws Exception Cannot be instantiated.
      */
     public static Session getSessionInstance() throws Exception {
         return new Session();
     }
}

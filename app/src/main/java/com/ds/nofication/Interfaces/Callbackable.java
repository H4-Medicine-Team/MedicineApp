package com.ds.nofication.Interfaces;

import java.text.ParseException;

public interface Callbackable {

     /**
      * Method that will be called on receiving information
      * @param update Object that will be received
      */
     void updateCallback(Object update) throws ParseException;

     /**
      * Method that will be called if there was an error on receiving information
      * @param errorMessage Message that will be received
      */
     void errorCallback(String errorMessage);
}

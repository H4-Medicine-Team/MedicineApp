package com.ds.nofication.Interfaces;

public interface Callbackable {

     /**
      * Method that will be called on receiving information
      * @param update Object that will be received
      */
     void updateCallback(Object update);

     /**
      * Method that will be called if there was an error on receiving information
      * @param errorMessage Message that will be received
      */
     void errorCallback(String errorMessage);
}

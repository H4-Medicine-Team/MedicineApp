package com.ds.nofication.Interfaces;

import java.lang.reflect.Type;
import java.util.ArrayList;

public interface Listenable<T> {
    /**
     * Adds objects to listener list
     * @param _o Object to add to listener list
     */
    void addListener(T _o);

    /**
     * Remove object from listener
     * @param _o Object to remove from listener list
     */
    void removeListener(T _o);
}

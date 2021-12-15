package com.ds.nofication.Interfaces;

import java.lang.reflect.Type;
import java.util.ArrayList;

public interface Listenable {
    void addListener(Object _o);
    void removeListener(Object _o);
}

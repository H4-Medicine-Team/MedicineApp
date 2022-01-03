package com.ds.nofication.Controllers;

import com.ds.nofication.Interfaces.Callbackable;
import com.ds.nofication.Interfaces.Listenable;
import com.ds.nofication.Listeners.ReminderListener;

import java.util.ArrayList;

public abstract class BaseApiController<T> implements Callbackable, Listenable {
    protected ArrayList<T> listeners = new ArrayList<>();
}

package com.ds.nofication.Interfaces;

import com.ds.nofication.Models.Reminder;

import java.util.ArrayList;

public interface Callbackable {
     void updateCallback(Object update);
     void errorCallback(String errorMessage);
}

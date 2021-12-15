package com.ds.nofication;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ds.nofication.Interfaces.ReminderCallback;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class ReminderListCaller implements Response.Listener, Response.ErrorListener {

    ReminderCallback reminderCallback;

    public ReminderListCaller(ReminderCallback callback){
        this.reminderCallback = callback;
    }


    public void createCall(Context context){
        // TODO: change url to correct later
        String url = "http://93.176.82.48/weatherforecast";

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET, url, null, this, this);

        queue.add(jsonObjectRequest2);
    }

    @Override
    public void onResponse(Object response) {
        Gson gson = new Gson();

        //TODO: Mock list to fit the api dto

        reminderCallback.updateListerns(null);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        reminderCallback.errorListeners(error.getMessage());
    }
}

package com.ds.nofication;

import android.content.Context;
import android.nfc.Tag;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ds.nofication.Interfaces.Callbackable;
import com.ds.nofication.Models.Backend.AmountType;
import com.ds.nofication.Models.Backend.BeginEndDate;
import com.ds.nofication.Models.Backend.Days;
import com.ds.nofication.Models.Backend.Dosage;
import com.ds.nofication.Models.Backend.Drug;
import com.ds.nofication.Models.Backend.DrugMedication;
import com.ds.nofication.Models.Backend.Interval;
import com.ds.nofication.Models.Backend.MedicineCard;
import com.google.gson.Gson;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class ReminderListCaller implements Response.Listener, Response.ErrorListener {

    private Callbackable reminderCallback;

    public ReminderListCaller(Callbackable callback){
        this.reminderCallback = callback;
    }

    /**
     *  Sends get request to endpoint     *
     * @param context
     */
    public void createCall(Context context){
        String baseUrl = new ConfigLoader().getConfigValue(context,"medicinecard_url");
        String url = baseUrl + "?cprnumber=12345678912";

        RequestQueue queue = Volley.newRequestQueue(context);

        // TODO: change to JsonObjectArray if its object we got from endpoint
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);

        queue.add(jsonObjectRequest);
    }

    // TODO: Remove requiers as its only here because we are using localdatetime.now()
    /**
    * Gets response object from the endpoint, calls callback method
    * {@link #reminderCallback} update will be called on this callback
    * @param response Response object from endpoint
    * */
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onResponse(Object response) {
        Gson gson = new Gson();

        //Log.e("gson log", response.toString());
        //String jString = response.toString();

        MedicineCard medicineCard = gson.fromJson(response.toString(), MedicineCard.class);

        reminderCallback.updateCallback(medicineCard);
    }

    /**
     * Gets response error from backend
     * {@link #reminderCallback} errorCallback will be called on this callback
     * @param error VolleyError
     */
    @Override
    public void onErrorResponse(VolleyError error) {
        reminderCallback.errorCallback(error.toString());
    }
}

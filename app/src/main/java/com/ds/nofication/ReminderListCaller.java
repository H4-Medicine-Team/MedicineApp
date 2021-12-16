package com.ds.nofication;

import android.content.Context;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReminderListCaller implements Response.Listener, Response.ErrorListener {

    Callbackable reminderCallback;

    public ReminderListCaller(Callbackable callback){
        this.reminderCallback = callback;
    }


    public void createCall(Context context){
        // TODO: change url to correct later
        String url = "http://93.176.82.48/weatherforecast";

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);

        queue.add(jsonObjectRequest);
    }

    // TODO: Remove requiers as its only here because we are using localdatetime.now()
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onResponse(Object response) {
        Gson gson = new Gson();

        //ArrayList<Dosage> dosages, Drug drug, String identifier, BeginEndDate beginEndDate
        //TODO: Mock list to fit the api dto

        ArrayList<Dosage> dosages = new ArrayList<>();
        dosages.add(new Dosage(1, AmountType.ML, new Interval(LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), new Days[] { Days.Monday })));

        DrugMedication drugMedication = new DrugMedication(dosages, new Drug("Yeet", "123"), "321", new BeginEndDate(LocalDateTime.now(), LocalDateTime.now()));
        ArrayList<DrugMedication> drugMedications = new ArrayList<>();
        drugMedications.add(drugMedication);
        MedicineCard medicineCard = new MedicineCard(drugMedications);

        reminderCallback.updateCallback(medicineCard);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        reminderCallback.errorCallback(error.getMessage());
    }
}

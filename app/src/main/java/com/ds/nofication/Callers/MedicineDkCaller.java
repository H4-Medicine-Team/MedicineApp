package com.ds.nofication.Callers;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ds.nofication.ConfigLoader;
import com.ds.nofication.Interfaces.Callbackable;
import com.ds.nofication.Models.Backend.MedicineDkWithIdDTO;
import com.google.gson.Gson;

public class MedicineDkCaller implements Response.Listener, Response.ErrorListener {

    private Callbackable medicineDkCallback;

    public MedicineDkCaller(Callbackable callback){
        this.medicineDkCallback = callback;
    }

    /**
     *  Sends get request to endpoint
     * @param context Activity Context
     */
    public void createCall(Context context, String identifier){
        String medicineDkGetWithIdUrl = new ConfigLoader().getConfigValue(context,"medicineDkGetWithId_url");
        String url = medicineDkGetWithIdUrl + "?dliID=" + identifier;

        RequestQueue queue = Volley.newRequestQueue(context);

        // TODO: change to JsonObjectArray if its object we got from endpoint
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);

        queue.add(jsonObjectRequest);
    }

    /**
     * Gets response object from the endpoint, calls callback method
     * {@link #medicineDkCallback} update will be called on this callback
     * @param response Response object from endpoint
     * */
    @Override
    public void onResponse(Object response) {
        Gson gson = new Gson();
        try{

            MedicineDkWithIdDTO medicineDkWithIdDTO = gson.fromJson(response.toString(), MedicineDkWithIdDTO.class);

            if(medicineDkCallback == null){
                throw new Exception("medicineDk callback is null");
            }

            medicineDkCallback.updateCallback(medicineDkWithIdDTO);

        }catch (Exception e){
            Log.e("MedicineDkCaller", "Response: " + response.toString());
            Log.e("MedicineDkCaller", "Exception: " + e.toString());
            medicineDkCallback.errorCallback("Error:" + e.toString());
        }
    }

    /**
     * Gets response error from backend
     * {@link #medicineDkCallback} errorCallback will be called on this callback
     * @param error VolleyError
     */
    @Override
    public void onErrorResponse(VolleyError error) {
        medicineDkCallback.errorCallback(error.toString());
    }
}

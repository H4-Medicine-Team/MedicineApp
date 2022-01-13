package com.ds.nofication.Services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ds.nofication.Interfaces.Callbackable;
import com.ds.nofication.Models.Backend.UserAuthentication;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;


public class AuthenticationCaller implements Response.Listener, Response.ErrorListener {

    private final Callbackable _callback;
    private final String _baseUrl;

    public AuthenticationCaller(Callbackable callback, String baseurl) {
        _callback = callback;
        _baseUrl = baseurl;
    }

    /**
     * Creates a new resource to login. Uses the base url and the given endpoint to create the resource
     * @param context The android context
     * @param authInfo The authentication info to post.
     * @throws JSONException Happpens if the authInfo can't be serialized
     */
    public void UserLogin(Context context, UserAuthentication authInfo) throws JSONException {
        RequestQueue queue = createQueue(context);

        try {
            String jsonStr = new Gson().toJson(authInfo);
            JsonObjectRequest request = CreatePostJsonObject("AuthUser", jsonStr);

            queue.add(request);
        }
        catch (JSONException e) {
            throw e;
        }
    }

    /**
     * Creates a new queue where requests can be added
     * @param context The android context
     */
    private RequestQueue createQueue(Context context) {
        return Volley.newRequestQueue(context);
    }

    /**
     * Creates a now post json request, from the given json string. Will format url to be (baseUrl + "/" + endpoint)
     * @param endpoint The endpoint to call to.
     * @param resource The json string to post
     * @return The request which can be added to a queue
     * @throws JSONException Happens when json string can't be serialized
     */
    private JsonObjectRequest CreatePostJsonObject(String endpoint, String resource) throws JSONException {
        JSONObject jObj;

        try {
            jObj = new JSONObject(resource);
        }
        catch (JSONException e) {
            throw e;
        }

        return new JsonObjectRequest(Request.Method.POST, _baseUrl + endpoint, jObj,this, this);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        _callback.errorCallback(error.toString());
    }

    @Override
    public void onResponse(Object response) {
        _callback.updateCallback(response);
    }
}

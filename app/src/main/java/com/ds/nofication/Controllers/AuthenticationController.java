package com.ds.nofication.Controllers;

import android.content.Context;

import com.ds.nofication.ConfigLoader;
import com.ds.nofication.Listeners.AuthenticationListenAble;
import com.ds.nofication.Models.Backend.UserAuthentication;
import com.ds.nofication.Services.AuthenticationCaller;

import org.json.JSONException;

public class AuthenticationController extends BaseApiController<AuthenticationListenAble> {

    private final AuthenticationCaller _caller;
    private final Context _context;

    public AuthenticationController(Context context) {
        _context = context;
        String baseUrl = new ConfigLoader().getConfigValue(context, "api_url");
        _caller = new AuthenticationCaller(this, baseUrl);
    }

    public void Login(UserAuthentication userInfo) {
        try {
            _caller.UserLogin(_context, userInfo);
        }
        catch (JSONException e) {
            errorCallback(e.toString());
        }
    }

    @Override
    public void updateCallback(Object update) {
        for (AuthenticationListenAble listener : listeners)
            listener.onLogin((String) update);
    }

    @Override
    public void errorCallback(String errorMessage) {
        for (AuthenticationListenAble listener : listeners)
            listener.onError(errorMessage);
    }

    @Override
    public void addListener(AuthenticationListenAble _o) {
        listeners.add(_o);
    }

    @Override
    public void removeListener(AuthenticationListenAble _o) {
        listeners.add(_o);
    }
}

package com.ds.nofication.Listeners;

public interface AuthenticationListenAble {

    /**
     * Called when a login happens.
     * @param token The token which is returned from the callback
     */
    public void onLogin(String token);

    /**
     * Called when a error happens for the authentication callback.
     * @param errorResponse The error response from the callback.
     */
    public void onError(String errorResponse);
}

package com.ds.nofication;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.*;

import com.ds.nofication.Interfaces.Callbackable;
import com.ds.nofication.Models.Backend.UserAuthentication;
import com.ds.nofication.Services.AuthenticationCaller;

import org.json.JSONException;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;
import org.testng.Assert;

import java.lang.reflect.Executable;
import java.util.concurrent.Callable;

public class AuthenticationCallerTest {

    @Test
    public void LoginShouldThrowException_WhenUserAuthIsNull() {
        // Arrange
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        AuthenticationCaller caller = fakeAuthenticationCaller();
        UserAuthentication data = null;

        // Act
        Assert.ThrowingRunnable func = new Assert.ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                caller.UserLogin(context, data);
            }
        };

        // Assert
        Assert.assertThrows(JSONException.class,func);
    }

    @Test
    public void LoginShouldNotThrowException() {
        // Arrange
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        AuthenticationCaller caller = fakeAuthenticationCaller();
        UserAuthentication data = new UserAuthentication("", "");

        // Act
        FailingRunnable func = new FailingRunnable() {
            @Override
            public void run() throws Exception {
                caller.UserLogin(context, data);
            }
        };

        // Assert
        DoesNotThrow(func);
    }

    private AuthenticationCaller fakeAuthenticationCaller() {
       return new AuthenticationCaller(new Callbackable() {
            @Override
            public void updateCallback(Object update) {

            }

            @Override
            public void errorCallback(String errorMessage) {

            }
        }, "");
    }

    private void DoesNotThrow(FailingRunnable method ) {
        try {
            method.run();
        }
        catch (Exception e) {
            throw new Error(method.getClass().getName() + "threw an exception was thrown, when not expected: " + e.getClass().toString());
        }
    }

    @FunctionalInterface interface FailingRunnable { void run() throws Exception; }
}

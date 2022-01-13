package com.ds.nofication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ds.nofication.Controllers.AuthenticationController;
import com.ds.nofication.Listeners.AuthenticationListenAble;
import com.ds.nofication.Models.Backend.UserAuthentication;
import com.ds.nofication.Security.SecurityHandler;
import com.ds.nofication.Services.AuthenticationCaller;

import org.w3c.dom.Text;

public class SignIn_MitId extends AppCompatActivity implements AuthenticationListenAble {

    private AuthenticationController _authController;
    private SecurityHandler _storageHandler;
    private ConfigLoader _config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_mit_id);

        _authController = new AuthenticationController(getBaseContext());
        _storageHandler = new SecurityHandler(getBaseContext());
        _config = new ConfigLoader();

        _authController.addListener(this);

        Button loginButton = (Button) findViewById(R.id.continue_sign_in_btn);
        loginButton.setOnClickListener(onLoginButtonClicked());
    }

    @Override
    public void onLogin(String token) {
        String key = _config.getConfigValue(getBaseContext(), "token_storage_key");
        _storageHandler.write(key, token);

        startActivity(new Intent(SignIn_MitId.this, MedicineActivity.class));
    }

    @Override
    public void onError(String errorResponse) {
        Log.e("SignIn_MitId", errorResponse);
        TextView errorText = (TextView) findViewById(R.id.signIn_error_text);
        errorText.setVisibility(View.VISIBLE);
        errorText.setText("Der opstod et problem med at logge ind");
    }

    private View.OnClickListener onLoginButtonClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAuthentication userAuth = new UserAuthentication(getUsername(), getPassword());
                _authController.Login(userAuth);
            }
        };
    }

    /**
     * @return The username from the ui
     */
    private String getUsername() {
        return ((EditText)findViewById(R.id.username_field)).getText().toString();
    }

    /**
     * @return The password from the ui
     */
    private String getPassword() {
        return ((EditText)findViewById(R.id.password_field)).getText().toString();
    }
}
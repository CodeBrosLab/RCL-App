package com.example.rcl_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rcl_app.R;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText loginUsernameInput, loginPasswordInput;
    private Button loginButton;
    private TextView goToRegistrationScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsernameInput = findViewById(R.id.loginUsernameInput);
        loginPasswordInput = findViewById(R.id.loginPasswordInput);
        loginButton = findViewById(R.id.loginButton);
        goToRegistrationScreen = findViewById(R.id.goToRegisterScreen);

        String usernameAsAString = loginUsernameInput.getText().toString();
        String passwordAsAString = loginPasswordInput.getText().toString();

        //assume we checked the validity for now

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent yourRewardsIntent = new Intent(LoginActivity.this, YourRewardsActivity.class);
                //startActivity(yourRewardsIntent);

                Intent adminOpenRequestsIntent = new Intent(LoginActivity.this, AdminOpenRequestsActivity.class);
                startActivity(adminOpenRequestsIntent);
            }
        });

        goToRegistrationScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registrationIntent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(registrationIntent);
            }
        });

    }
}
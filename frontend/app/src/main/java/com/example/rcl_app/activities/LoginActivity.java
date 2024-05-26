package com.example.rcl_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rcl_app.R;
import com.example.rcl_app.http_requests.LoginORegisterOkHttpHandler;

public class LoginActivity extends AppCompatActivity {

    private EditText loginUsernameInput, loginPasswordInput;
    private Button loginButton;
    private TextView goToRegistrationScreen;
    private LoginORegisterOkHttpHandler loginHttp = new LoginORegisterOkHttpHandler();

    private String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsernameInput = findViewById(R.id.loginUsernameInput);
        loginPasswordInput = findViewById(R.id.loginPasswordInput);
        loginButton = findViewById(R.id.loginButton);
        goToRegistrationScreen = findViewById(R.id.goToRegisterScreen);

        ip = getString(R.string.ipv4);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernameAsAString = loginUsernameInput.getText().toString();
                String passwordAsAString = loginPasswordInput.getText().toString();

                Integer idResponse = -2;

                try {
                    idResponse = loginHttp.loginPostRequest("http://" + ip + ":8080/login", usernameAsAString, passwordAsAString);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //Toast.makeText(LoginActivity.this,idResponse.toString(),Toast.LENGTH_LONG).show();

                if(idResponse == 1){
                    Intent adminOpenRequestsIntent = new Intent(LoginActivity.this, AdminOpenRequestsActivity.class);
                    startActivity(adminOpenRequestsIntent);
                }
                else if(idResponse > 0){
                    Intent yourRewardsIntent = new Intent(LoginActivity.this, YourRewardsActivity.class);
                    yourRewardsIntent.putExtra("userid",idResponse);
                    startActivity(yourRewardsIntent);
                }
                else
                Toast.makeText(LoginActivity.this,"Wrong Username or Password",Toast.LENGTH_LONG).show();

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

package com.example.rcl_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rcl_app.R;

import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity {

    private EditText registrationUsernameInput, registrationPasswordInput, registrationPasswordRetypeInput;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registrationUsernameInput = findViewById(R.id.registrationUsernameInput);
        registrationPasswordInput = findViewById(R.id.registrationPasswordInput);
        registrationPasswordRetypeInput = findViewById(R.id.registrationPasswordRetypeInput);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernameAsAString = registrationUsernameInput.getText().toString();
                String passwordAsAString = registrationPasswordInput.getText().toString();
                String passwordRetypeAsAString = registrationPasswordRetypeInput.getText().toString();

                if(arePasswordInputsTheSame(passwordAsAString, passwordRetypeAsAString)) {
                    Intent yourRewardsIntent = new Intent(RegistrationActivity.this, YourRewardsActivity.class);
                    startActivity(yourRewardsIntent);
                } else {
                    Toast.makeText(RegistrationActivity.this, "Password fields do not contain the same password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean arePasswordInputsTheSame(String password, String passwordRetype)
    {
        return password.equals(passwordRetype);
    }
}

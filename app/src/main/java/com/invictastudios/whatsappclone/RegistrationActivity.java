package com.invictastudios.whatsappclone;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText emailEditText;
    private Button registrationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        usernameEditText = findViewById(R.id.username_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);
        registrationButton = findViewById(R.id.registration_button);

    }
}
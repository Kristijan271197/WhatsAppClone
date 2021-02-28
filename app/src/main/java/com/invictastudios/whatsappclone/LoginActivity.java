package com.invictastudios.whatsappclone;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private EditText userETLogin, passETLogin;

    private FirebaseAuth auth;

    public static final Pattern EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );





    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        // Checking for users existance: Saving the current user
        if (firebaseUser != null) {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        userETLogin = findViewById(R.id.editText);
        passETLogin = findViewById(R.id.editText3);
        Button loginBtn = findViewById(R.id.buttonLogin);
        Button registerBtn = findViewById(R.id.registerBtn);

        // Fire base Auth:
        auth = FirebaseAuth.getInstance();


        // Register Button:

        registerBtn.setOnClickListener(v -> {
            Intent i = new Intent(LoginActivity.this, RegistrationActivity.class);
            startActivity(i);
        });

        // Login Button:
        loginBtn.setOnClickListener(v -> {
            String email_text = userETLogin.getText().toString();
            String pass_text = passETLogin.getText().toString();

            if (isValidEmail(email_text)) {
                if (TextUtils.isEmpty(email_text) || TextUtils.isEmpty((pass_text))) {
                    Toast.makeText(LoginActivity.this, "Please fill the Fields", Toast.LENGTH_SHORT).show();
                } else {
                    auth.signInWithEmailAndPassword(email_text, pass_text)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(i);
                                    finish();
                                } else
                                    Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();

                            });
                }
            } else
                Toast.makeText(LoginActivity.this, "Invalid Email", Toast.LENGTH_SHORT).show();
        });

    }

    public boolean isValidEmail(CharSequence email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }


}
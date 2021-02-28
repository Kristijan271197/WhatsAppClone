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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.regex.Pattern;

public class RegistrationActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText emailEditText;

    private FirebaseAuth auth;
    private DatabaseReference myRef;

    public static final Pattern EMAIL_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._%\\-]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
    );

    public static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");

    public static final Pattern USERNAME_PATTERN = Pattern.compile("^[^\\\\<>/*&^%$#@!)(.,;'\"}{+=\\[\\]]*$");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        usernameEditText = findViewById(R.id.username_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);
        Button registrationButton = findViewById(R.id.registration_button);

        auth = FirebaseAuth.getInstance();

        registrationButton.setOnClickListener(v -> {
            String username_text = usernameEditText.getText().toString();
            String email_text = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            if (isValidEmail(username_text)) {
                if(isValidPassword(password)) {
                    if(isValidUsername(username_text)) {
                        RegisterNow(username_text, email_text, password);
                    } else
                        Toast.makeText(RegistrationActivity.this, "Username must contain " +
                                "only numbers and letters and must not be empty", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(RegistrationActivity.this, "Password must contain at least:\n" +
                            "One Digit\n" +
                            "One Uppercase letter\n" +
                            "One Lowercase letter\n" +
                            "Is minimum 8 characters\n" +
                            "Must not contain special charracters", Toast.LENGTH_SHORT).show();



            } else
                Toast.makeText(RegistrationActivity.this, "Invalid Email Format", Toast.LENGTH_SHORT).show();
        });

    }

    private void RegisterNow(final String username, String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = auth.getCurrentUser();
                        String userId = null;
                        if (firebaseUser != null) {
                            userId = firebaseUser.getUid();
                            myRef = FirebaseDatabase.getInstance()
                                    .getReference("MyUsers")
                                    .child(userId);
                        }
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("id", userId);
                        hashMap.put("username", username);
                        hashMap.put("imageURL", "default");
                        hashMap.put("status", "offline");

                        //Opening MainActivity after Success Registration
                        myRef.setValue(hashMap).addOnCompleteListener(task1 -> {
                            if (task1.isSuccessful()) {
                                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                                Toast.makeText(RegistrationActivity.this, "Finished", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(e -> Toast.makeText(RegistrationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show());
                    }
                }).addOnFailureListener(e -> Toast.makeText(RegistrationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    public  boolean isValidEmail(CharSequence email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public boolean isValidPassword(CharSequence password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }

    public boolean isValidUsername(CharSequence username) {
        return username != null && USERNAME_PATTERN.matcher(username).matches() && username.length() >= 1;
    }
}
package com.invictastudios.whatsappclone.Firebase;

import java.util.HashMap;
import java.util.regex.Pattern;

public class RegistrationTests {

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


    public boolean validRegistrationInfo(String userId, String username, HashMap<String, String> hashMap) {
        if (validateUserId(userId) && isValidUsername(username)) {
            hashMap.put("id", userId);
            hashMap.put("username", username);
            hashMap.put("imageURL", "default");
            hashMap.put("status", "offline");
            return true;
        } else return false;
    }

    public boolean isValidEmailPasswordUsername(String username, String password, String email) {
        return isValidEmail(email) && isValidPassword(password) && isValidUsername(username);
    }

    public boolean isValidEmail(CharSequence email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public boolean isValidPassword(CharSequence password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }

    public boolean isValidUsername(CharSequence username) {
        return username != null && USERNAME_PATTERN.matcher(username).matches() && username.length() >= 1;
    }

    public boolean validateUserId(CharSequence userId) {
        return userId != null && USERNAME_PATTERN.matcher(userId).matches() && userId.length() >= 1;
    }
}

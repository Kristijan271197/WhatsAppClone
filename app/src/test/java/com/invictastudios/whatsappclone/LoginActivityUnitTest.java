package com.invictastudios.whatsappclone;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginActivityUnitTest {


    LoginActivity loginActivity = new LoginActivity();

    @Test
    public void emailValidator_CorrectEmail_ReturnsTrue(){
        assertTrue(loginActivity.isValidEmail("kristijanstojanoski@yahoo.com"));
    }

    @Test
    public void emailValidator_CorrectEmailSubDomain_ReturnsTrue() {
        assertTrue(loginActivity.isValidEmail("kristijanstojanoski@email.co.uk"));
    }

    @Test
    public void emailValidator_InvalidEmailNoTld_ReturnsFalse() {
        assertFalse(loginActivity.isValidEmail("name@email"));
    }

    @Test
    public void emailValidator_InvalidEmailDoubleDot_ReturnsFalse() {
        assertFalse(loginActivity.isValidEmail("name@email..com"));
    }

    @Test
    public void emailValidator_InvalidEmailNoUsername_ReturnsFalse() {
        assertFalse(loginActivity.isValidEmail("@email.com"));
    }

    @Test
    public void emailValidator_EmptyEmail_ReturnsTrue(){
        assertFalse(loginActivity.isValidEmail(""));
    }

    @Test
    public void emailValidator_NonNullEmail_ReturnsTrue(){
        assertFalse(loginActivity.isValidEmail(null));
    }
}

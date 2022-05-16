package com.invictastudios.whatsappclone;

import com.invictastudios.whatsappclone.firebase.LoginTests;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginActivityUnitTest {


    final LoginTests loginTests = new LoginTests();

    @Test
    public void emailValidator_CorrectEmail_ReturnsTrue(){
        assertTrue(loginTests.isValidEmail("kristijanstojanoski@yahoo.com"));
    }

    @Test
    public void emailValidator_CorrectEmailSubDomain_ReturnsTrue() {
        assertTrue(loginTests.isValidEmail("kristijanstojanoski@email.co.uk"));
    }

    @Test
    public void emailValidator_InvalidEmailNoTld_ReturnsFalse() {
        assertFalse(loginTests.isValidEmail("name@email"));
    }

    @Test
    public void emailValidator_InvalidEmailDoubleDot_ReturnsFalse() {
        assertFalse(loginTests.isValidEmail("name@email..com"));
    }

    @Test
    public void emailValidator_InvalidEmailNoUsername_ReturnsFalse() {
        assertFalse(loginTests.isValidEmail("@email.com"));
    }

    @Test
    public void emailValidator_EmptyEmail_ReturnsTrue(){
        assertFalse(loginTests.isValidEmail(""));
    }

    @Test
    public void emailValidator_NonNullEmail_ReturnsTrue(){
        assertFalse(loginTests.isValidEmail(null));
    }
}

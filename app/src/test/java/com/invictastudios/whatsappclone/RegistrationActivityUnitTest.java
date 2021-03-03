package com.invictastudios.whatsappclone;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegistrationActivityUnitTest {

    RegistrationActivity registrationActivity = new RegistrationActivity();

    @Test
    public void emailValidator_CorrectEmail_ReturnsTrue(){
        assertTrue(registrationActivity.isValidEmail("kristijanstojanoski@yahoo.com"));
    }

    @Test
    public void emailValidator_CorrectEmailSubDomain_ReturnsTrue() {
        assertTrue(registrationActivity.isValidEmail("kristijanstojanoski@email.co.uk"));
    }

    @Test
    public void emailValidator_InvalidEmailNoTld_ReturnsFalse() {
        assertFalse(registrationActivity.isValidEmail("name@email"));
    }

    @Test
    public void emailValidator_InvalidEmailDoubleDot_ReturnsFalse() {
        assertFalse(registrationActivity.isValidEmail("name@email..com"));
    }

    @Test
    public void emailValidator_InvalidEmailNoUsername_ReturnsFalse() {
        assertFalse(registrationActivity.isValidEmail("@email.com"));
    }

    @Test
    public void emailValidator_EmptyEmail_ReturnsFalse(){
        assertFalse(registrationActivity.isValidEmail(""));
    }

    @Test
    public void emailValidator_NonNullEmail_ReturnsFalse(){
        assertFalse(registrationActivity.isValidEmail(null));
    }

    @Test
    public void passwordValidator_CorrectPassword_ReturnsTrue(){
        assertTrue(registrationActivity.isValidPassword("SomePassword1"));
    }

    @Test
    public void passwordValidator_NoDigits_ReturnsFalse(){
        assertFalse(registrationActivity.isValidPassword("SomePassword"));
    }

    @Test
    public void passwordValidator_NoLowercaseLetters_ReturnsFalse(){
        assertFalse(registrationActivity.isValidPassword("SOMEPASSWORD1"));
    }

    @Test
    public void passwordValidator_NoUppercaseLetters_ReturnsFalse(){
        assertFalse(registrationActivity.isValidPassword("somepassword1"));
    }

    @Test
    public void passwordValidator_ContainsSpecialCharacters_ReturnsFalse(){
        assertFalse(registrationActivity.isValidPassword("SomePassword%$"));
    }

    @Test
    public void passwordValidator_AtLeastEightCharacters_ReturnsFalse(){
        assertFalse(registrationActivity.isValidPassword("SomeP1"));
    }

    @Test
    public void passwordValidator_ContainsEmptySpace_ReturnsFalse(){
        assertFalse(registrationActivity.isValidPassword("Some Password1"));
    }

    @Test
    public void passwordValidator_EmptyPassword_ReturnsFalse(){
        assertFalse(registrationActivity.isValidPassword(""));
    }

    @Test
    public void passwordValidator_NonNullPassword_ReturnsFalse(){
        assertFalse(registrationActivity.isValidPassword(null));
    }

    @Test
    public void usernameValidator_CorrectUsername_ReturnsTrue(){
        assertTrue(registrationActivity.isValidUsername("SomeUsername1234"));
    }

    @Test
    public void usernameValidator_NoSpecialCharacters_ReturnsFalse(){
        assertFalse(registrationActivity.isValidUsername("SomeUsername@#$"));
    }

    @Test
    public void usernameValidator_EmptyUsername_ReturnsFalse(){
        assertFalse(registrationActivity.isValidUsername(""));
    }

    @Test
    public void usernameValidator_NonNullUsername_ReturnsFalse(){
        assertFalse(registrationActivity.isValidUsername(null));
    }

    @Test
    public void userIdValidator_CorrectUserId_ReturnsTrue(){
        assertTrue(registrationActivity.validateUserId("2mPa8YFCYTSyOKr1f4NupAo2A0M2"));
    }

    @Test
    public void userIdValidator_NoSpecialCharacters_ReturnsFalse(){
        assertFalse(registrationActivity.validateUserId("2mPa8YFCYTSyOKr1f4NupAo2A0M2@#$"));
    }

    @Test
    public void userIdValidator_EmptyUserId_ReturnsFalse(){
        assertFalse(registrationActivity.validateUserId(""));
    }

    @Test
    public void userIdValidator_NonNullUserId_ReturnsFalse(){
        assertFalse(registrationActivity.validateUserId(null));
    }

}
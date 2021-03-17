package com.invictastudios.whatsappclone;

import com.invictastudios.whatsappclone.Firebase.RegistrationTests;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class RegistrationActivityUnitTest {

    RegistrationTests registrationTests = new RegistrationTests();

    @Test
    public void emailValidator_CorrectEmail_ReturnsTrue(){
        assertTrue(registrationTests.isValidEmail("kristijanstojanoski@yahoo.com"));
    }

    @Test
    public void emailValidator_CorrectEmailSubDomain_ReturnsTrue() {
        assertTrue(registrationTests.isValidEmail("kristijanstojanoski@email.co.uk"));
    }

    @Test
    public void emailValidator_InvalidEmailNoTld_ReturnsFalse() {
        assertFalse(registrationTests.isValidEmail("name@email"));
    }

    @Test
    public void emailValidator_InvalidEmailDoubleDot_ReturnsFalse() {
        assertFalse(registrationTests.isValidEmail("name@email..com"));
    }

    @Test
    public void emailValidator_InvalidEmailNoUsername_ReturnsFalse() {
        assertFalse(registrationTests.isValidEmail("@email.com"));
    }

    @Test
    public void emailValidator_EmptyEmail_ReturnsFalse(){
        assertFalse(registrationTests.isValidEmail(""));
    }

    @Test
    public void emailValidator_NonNullEmail_ReturnsFalse(){
        assertFalse(registrationTests.isValidEmail(null));
    }

    @Test
    public void passwordValidator_CorrectPassword_ReturnsTrue(){
        assertTrue(registrationTests.isValidPassword("SomePassword1"));
    }

    @Test
    public void passwordValidator_NoDigits_ReturnsFalse(){
        assertFalse(registrationTests.isValidPassword("SomePassword"));
    }

    @Test
    public void passwordValidator_NoLowercaseLetters_ReturnsFalse(){
        assertFalse(registrationTests.isValidPassword("SOMEPASSWORD1"));
    }

    @Test
    public void passwordValidator_NoUppercaseLetters_ReturnsFalse(){
        assertFalse(registrationTests.isValidPassword("somepassword1"));
    }

    @Test
    public void passwordValidator_ContainsSpecialCharacters_ReturnsFalse(){
        assertFalse(registrationTests.isValidPassword("SomePassword%$"));
    }

    @Test
    public void passwordValidator_AtLeastEightCharacters_ReturnsFalse(){
        assertFalse(registrationTests.isValidPassword("SomeP1"));
    }

    @Test
    public void passwordValidator_ContainsEmptySpace_ReturnsFalse(){
        assertFalse(registrationTests.isValidPassword("Some Password1"));
    }

    @Test
    public void passwordValidator_EmptyPassword_ReturnsFalse(){
        assertFalse(registrationTests.isValidPassword(""));
    }

    @Test
    public void passwordValidator_NonNullPassword_ReturnsFalse(){
        assertFalse(registrationTests.isValidPassword(null));
    }

    @Test
    public void usernameValidator_CorrectUsername_ReturnsTrue(){
        assertTrue(registrationTests.isValidUsername("SomeUsername1234"));
    }

    @Test
    public void usernameValidator_NoSpecialCharacters_ReturnsFalse(){
        assertFalse(registrationTests.isValidUsername("SomeUsername@#$"));
    }

    @Test
    public void usernameValidator_EmptyUsername_ReturnsFalse(){
        assertFalse(registrationTests.isValidUsername(""));
    }

    @Test
    public void usernameValidator_NonNullUsername_ReturnsFalse(){
        assertFalse(registrationTests.isValidUsername(null));
    }

    @Test
    public void userIdValidator_CorrectUserId_ReturnsTrue(){
        assertTrue(registrationTests.validateUserId("2mPa8YFCYTSyOKr1f4NupAo2A0M2"));
    }

    @Test
    public void userIdValidator_NoSpecialCharacters_ReturnsFalse(){
        assertFalse(registrationTests.validateUserId("2mPa8YFCYTSyOKr1f4NupAo2A0M2@#$"));
    }

    @Test
    public void userIdValidator_EmptyUserId_ReturnsFalse(){
        assertFalse(registrationTests.validateUserId(""));
    }

    @Test
    public void userIdValidator_NonNullUserId_ReturnsFalse(){
        assertFalse(registrationTests.validateUserId(null));
    }

    @Test
    public void userEmailPasswordValidator_CorrectCase_ReturnsTrue(){
        assertTrue(registrationTests.isValidEmailPasswordUsername("Username","Password123","kristijanstojanoski@yahoo.com"));
    }

    @Test
    public void correctRegistrationInfo_CorrectCase_ReturnsTrue(){
        assertTrue(registrationTests.validRegistrationInfo("someUserId","someUsername", new HashMap<>()));
    }



}
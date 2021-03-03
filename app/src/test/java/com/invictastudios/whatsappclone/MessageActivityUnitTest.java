package com.invictastudios.whatsappclone;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MessageActivityUnitTest {

    MessageActivity messageActivity = new MessageActivity();


    @Test
    public void messageValidator_CorrectMessage_ReturnsTrue(){
        assertTrue(messageActivity.checkMessage("Some message"));
    }

    @Test
    public void messageValidator_NonNullMessage_ReturnsFalse(){
        assertFalse(messageActivity.checkMessage(null));
    }

    @Test
    public void messageValidator_NonEmptyMessage_ReturnsFalse(){
        assertFalse(messageActivity.checkMessage(""));
    }

    @Test
    public void messageValidator_MessageLengthExceed_ReturnsFalse(){
        String longMessage = "HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHell" +
                "oHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHe" +
                "lloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello" +
                "HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHel" +
                "loHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello" +
                "HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello" +
                "HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello;";

        assertFalse(messageActivity.checkMessage(longMessage));
    }

    @Test
    public void userIdValidator_CorrectUserId_ReturnsTrue(){
        assertTrue(messageActivity.validateUserId("2mPa8YFCYTSyOKr1f4NupAo2A0M2"));
    }

    @Test
    public void userIdValidator_NoSpecialCharacters_ReturnsFalse(){
        assertFalse(messageActivity.validateUserId("2mPa8YFCYTSyOKr1f4NupAo2A0M2@#$"));
    }

    @Test
    public void userIdValidator_EmptyUserId_ReturnsFalse(){
        assertFalse(messageActivity.validateUserId(""));
    }

    @Test
    public void userIdValidator_NonNullUserId_ReturnsFalse(){
        assertFalse(messageActivity.validateUserId(null));
    }

    @Test
    public void usernameValidator_CorrectUsername_ReturnsTrue(){
        assertTrue(messageActivity.isValidUsername("SomeUsername1234"));
    }

    @Test
    public void usernameValidator_NoSpecialCharacters_ReturnsFalse(){
        assertFalse(messageActivity.isValidUsername("SomeUsername@#$"));
    }

    @Test
    public void usernameValidator_EmptyUsername_ReturnsFalse(){
        assertFalse(messageActivity.isValidUsername(""));
    }

    @Test
    public void usernameValidator_NonNullUsername_ReturnsFalse(){
        assertFalse(messageActivity.isValidUsername(null));
    }

    @Test
    public void urlValidator_CorrectUrl_ReturnsTrue(){
        assertTrue(messageActivity.validateURL("https://stackoverflow.com/questions/5617749/how-to-validate-a-url-website-name-in-edittext-in-android"));
    }

    @Test
    public void urlValidator_IncorrectUrl_ReturnsFalse(){
        assertFalse(messageActivity.validateURL("SomekindofURl"));
    }

    @Test
    public void urlValidator_EmptyUrl_ReturnsFalse(){
        assertFalse(messageActivity.validateURL(""));
    }

    @Test
    public void urlValidator_NonNullUrl_ReturnsFalse(){
        assertFalse(messageActivity.validateURL(null));
    }

}

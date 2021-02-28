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

}

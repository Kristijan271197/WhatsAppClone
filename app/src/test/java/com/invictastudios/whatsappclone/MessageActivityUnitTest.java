package com.invictastudios.whatsappclone;

import com.invictastudios.whatsappclone.Firebase.MessageTests;
import com.invictastudios.whatsappclone.Model.Chat;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MessageActivityUnitTest {

    MessageTests messageTest = new MessageTests();

    @Test
    public void messageValidator_CorrectMessage_ReturnsTrue() {
        assertTrue(messageTest.checkMessage("Some message"));
    }
    @Test
    public void messageValidator_NonNullMessage_ReturnsFalse() {
        assertFalse(messageTest.checkMessage(null));
    }
    @Test
    public void messageValidator_NonEmptyMessage_ReturnsFalse() {
        assertFalse(messageTest.checkMessage(""));
    }
    @Test
    public void messageValidator_MessageLengthExceed_ReturnsFalse() {
        String longMessage = "HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHell" +
                "oHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHe" +
                "lloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello" +
                "HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHel" +
                "loHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello" +
                "HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello" +
                "HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHello;";

        assertFalse(messageTest.checkMessage(longMessage));
    }
    @Test
    public void userIdValidator_CorrectUserId_ReturnsTrue() {
        assertTrue(messageTest.validateUserId("2mPa8YFCYTSyOKr1f4NupAo2A0M2"));
    }
    @Test
    public void userIdValidator_NoSpecialCharacters_ReturnsFalse() {
        assertFalse(messageTest.validateUserId("2mPa8YFCYTSyOKr1f4NupAo2A0M2@#$"));
    }
    @Test
    public void userIdValidator_EmptyUserId_ReturnsFalse() {
        assertFalse(messageTest.validateUserId(""));
    }
    @Test
    public void userIdValidator_NonNullUserId_ReturnsFalse() {
        assertFalse(messageTest.validateUserId(null));
    }
    @Test
    public void usernameValidator_CorrectUsername_ReturnsTrue() {
        assertTrue(messageTest.isValidUsername("SomeUsername1234"));
    }
    @Test
    public void usernameValidator_NoSpecialCharacters_ReturnsFalse() {
        assertFalse(messageTest.isValidUsername("SomeUsername@#$"));
    }
    @Test
    public void usernameValidator_EmptyUsername_ReturnsFalse() {
        assertFalse(messageTest.isValidUsername(""));
    }
    @Test
    public void usernameValidator_NonNullUsername_ReturnsFalse() {
        assertFalse(messageTest.isValidUsername(null));
    }
    @Test
    public void readMessageDataValidator_CorrectCase_ReturnsTrue() {
        Chat chat = new Chat("someSender", "someReciever", "", true);
        List<Chat> chatList = new ArrayList<>();
        assertTrue(messageTest.readMessageData("someSender", "someReciever", chat, chatList));
    }
    @Test
    public void readMessageDataValidator_WrongSender_ReturnsFalse() {
        Chat chat = new Chat("someReciever", "someReciever", "", true);
        List<Chat> chatList = new ArrayList<>();
        assertFalse(messageTest.readMessageData("someSender", "someSender", chat, chatList));
    }
    @Test
    public void readMessageDataValidator_IncorrectMyId_ReturnsFalse() {
        Chat chat = new Chat("someSender%41", "someReciever", "", true);
        List<Chat> chatList = new ArrayList<>();
        assertFalse(messageTest.readMessageData("someSender", "someReciever", chat, chatList));
    }
    @Test
    public void readMessageDataValidator_IncorrectUserId_ReturnsFalse() {
        Chat chat = new Chat("someSender", "someReciever%#$#", "", true);
        List<Chat> chatList = new ArrayList<>();
        assertFalse(messageTest.readMessageData("someSender", "someReciever", chat, chatList));
    }
    @Test
    public void sendMessageDataValidator_CorrectCase_ReturnsTrue() {
        String sender = "someSender";
        String receiver = "someReceiver";
        String message = "someMessage";
        HashMap<String, Object> hashMap = new HashMap<>();
        assertTrue(messageTest.sendMessageData(sender, receiver, message, hashMap));
    }
    @Test
    public void messageSeenDataValidator_CorrectCase_ReturnsTrue() {
        String userId = "someSender";
        Chat chat = new Chat("someSender", "FirebaseReciever", "", true);
        assertTrue(messageTest.messageSeen(userId, null, chat, null));
    }
}

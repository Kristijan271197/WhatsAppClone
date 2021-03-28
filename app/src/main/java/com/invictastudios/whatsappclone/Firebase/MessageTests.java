package com.invictastudios.whatsappclone.Firebase;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.invictastudios.whatsappclone.Model.Chat;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

public class MessageTests {

    public static final Pattern USERNAME_PATTERN = Pattern.compile("^[^\\\\<>/*&^%$#@!)(.,;'\"}{+=\\[\\]]*$");

    public boolean messageSeen(String userid, FirebaseUser firebaseUser, Chat chat, DataSnapshot dataSnapshot) {
        if (validateUserId(userid)) {
            String firebaseId;
            if (firebaseUser != null) firebaseId = firebaseUser.getUid();
            else firebaseId = chat.getReceiver();

            if (chat.getReceiver().equals(firebaseId) && chat.getSender().equals(userid)) {
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("isseen", true);
            }
            return true;
        } else return false;
    }
    public boolean sendMessageData(String sender, String receiver, String message, HashMap<String, Object> hashMap) {
        if (validateUserId(sender) && validateUserId(receiver) && checkMessage(message)) {
            hashMap.put("sender", sender);
            hashMap.put("receiver", receiver);
            hashMap.put("message", message);
            hashMap.put("isseen", false);
            return true;
        } else return false;
    }
    public boolean readMessageData(String myid, String userid, Chat chat, List<Chat> listChat) {
        if (validateUserId(myid) && validateUserId(userid)) {
            if (chat.getReceiver().equals(myid) && chat.getSender().equals(userid)
                    || chat.getReceiver().equals(userid) && chat.getSender().equals(myid)) {
                listChat.add(chat);
                return true;
            } else
                return false;
        } else return false;
    }
    public boolean checkIds(String myId, String userId) {
        return validateUserId(myId) && validateUserId(userId);
    }
    public boolean checkMessage(String message) {
        return message != null && !message.equals("") && message.length() <= 200;
    }
    public boolean isValidUsername(CharSequence username) {
        return username != null && USERNAME_PATTERN.matcher(username).matches() && username.length() >= 1;
    }
    public boolean validateUserId(CharSequence userId) {
        return userId != null && USERNAME_PATTERN.matcher(userId).matches() && userId.length() >= 1;
    }
}

package com.invictastudios.whatsappclone;

import androidx.annotation.NonNull;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.invictastudios.whatsappclone.Model.Chat;
import com.invictastudios.whatsappclone.Model.ChatList;
import com.invictastudios.whatsappclone.Model.Users;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@LargeTest
public class FirebaseIntegrationTests {

    @Test
    public void AfirebaseAuthRegisterUserTest() throws InterruptedException {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.createUserWithEmailAndPassword("someEmail@gmail.com", "somepassword123")
                .addOnSuccessListener(authResult -> {
                    System.out.println("firebaseAuthRegisterUserTest: User Registered");
                    System.out.println("firebaseAuthRegisterUserTest: User Id: " + authResult.getUser().getUid());
                    System.out.println("firebaseAuthRegisterUserTest: User Email: " + authResult.getUser().getEmail());
                    System.out.println("firebaseAuthRegisterUserTest: PASSED");

                })
                .addOnFailureListener(e -> {
                    System.out.println("firebaseAuthRegisterUserTest: Failed to register user: " + e.getMessage());
                    System.out.println("firebaseAuthRegisterUserTest: FAILED");
                    fail();
                });

        Thread.sleep(3000);
    }

    @Test
    public void BfirebaseAuthLoginUserTest() throws InterruptedException {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword("someEmail@gmail.com", "somepassword123")
                .addOnSuccessListener(authResult -> {
                    System.out.println("firebaseAuthLoginUserTest: User Logged in");
                    System.out.println("firebaseAuthLoginUserTest: User Id: " + authResult.getUser().getUid());
                    System.out.println("firebaseAuthLoginUserTest: PASSED");
                    authResult.getUser().delete();
                })
                .addOnFailureListener(e -> {
                    System.out.println("firebaseAuthLoginUserTest: Failed to login user: " + e.getMessage());
                    System.out.println("firebaseAuthLoginUserTest: FAILED");
                    fail();
                });

        Thread.sleep(3000);
    }

    @Test
    public void CaddUserToDatabaseTest() throws InterruptedException {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        Users user = new Users("abcdefgh123456789", "someUsername", "default", "status");
        database.child("MyUsers").child(user.getId()).setValue(user)
                .addOnSuccessListener(aVoid -> database.child("MyUsers").child("abcdefgh123456789").get()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                System.out.println("addUserToDatabaseTest: User Successfully added to database: " + task.getResult().getValue());
                                System.out.println("PASSED");
                                database.child("MyUsers").child("abcdefgh123456789").removeValue();
                            }
                        })
                        .addOnFailureListener(e -> {
                            System.out.println("addUserToDatabaseTest: Failed to add: " + e.getMessage());
                            System.out.println("FAILED");
                            fail();
                        }))
                .addOnFailureListener(e -> {
                    System.out.println("addUserToDatabaseTest: Failed to add: " + e.getMessage());
                    System.out.println("FAILED");
                    fail();
                });
        Thread.sleep(3000);
    }

    @Test
    public void EgetAllUsersTest() throws InterruptedException {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("MyUsers");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Users user = snapshot.getValue(Users.class);
                    System.out.println("getAllUsersTest: User: " + user.getUsername());
                }
                System.out.println("getAllUsersTest: PASSED");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("getAllUsersTest: Error " + error.getMessage());
                System.out.println("getAllUsersTest: FAILED");
                fail();
            }
        });
        Thread.sleep(3000);
    }

    @Test
    public void FaddMessageToDatabaseTest() throws InterruptedException {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        Chat chat = new Chat("someSender", "someReceiver", "someMessage", false);
        database.child("Chats").push().setValue(chat)
                .addOnSuccessListener(aVoid -> {
                    System.out.println("addMessageToDatabaseTest: Message Successfully added to database");
                    System.out.println("PASSED");
                })
                .addOnFailureListener(e -> {
                    System.out.println("addMessageToDatabaseTest: Failed to add: " + e.getMessage());
                    System.out.println("addMessageToDatabaseTest: FAILED");
                    fail();
                });
        Thread.sleep(3000);
    }

    @Test
    public void GgetMessagesWithUserId() throws InterruptedException {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Chats");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);
                    if (chat != null)
                        if (chat.getSender().equals("someSender")) {
                            System.out.println("getMessagesWithUserId: Message Received: " + chat.getMessage());
                            System.out.println("getMessagesWithUserId: PASSED");
                            database.child(snapshot.getKey()).removeValue();
                        }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("getMessagesWithUserId: Error " + error.getMessage());
                System.out.println("getMessagesWithUserId: FAILED");
                fail();
            }
        });
        Thread.sleep(3000);
    }

    @Test
    public void HaddChatListToDatabaseTest() throws InterruptedException {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("ChatList").child("someChild");
        ChatList chatList = new ChatList("someID");
        database.push().setValue(chatList)
                .addOnSuccessListener(aVoid -> {
                    System.out.println("addChatListToDatabaseTest: ChatList Successfully added to database");
                    System.out.println("PASSED");
                })
                .addOnFailureListener(e -> {
                    System.out.println("addChatListToDatabaseTest: Failed to add: " + e.getMessage());
                    System.out.println("addChatListToDatabaseTest: FAILED");
                    fail();
                });
        Thread.sleep(3000);
    }

    @Test
    public void IgetChatListIDFromDatabaseTest() throws InterruptedException {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("ChatList").child("someChild");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ChatList chatList = snapshot.getValue(ChatList.class);
                    if (chatList != null) {
                        System.out.println("getChatListIDFromDatabaseTest: ID Received: " + chatList.getId());
                        System.out.println("getChatListIDFromDatabaseTest: PASSED");
                        database.removeValue();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("getChatListIDFromDatabaseTest: Error " + error.getMessage());
                System.out.println("getChatListIDFromDatabaseTest: FAILED");
                fail();
            }
        });
        Thread.sleep(3000);
    }

    @Test
    public void JaddFileToStorageTest() throws InterruptedException {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference("test");
        String data = "This is my data";
        storageReference.child("file.txt").putBytes(data.getBytes())
                .addOnSuccessListener(taskSnapshot -> {
                    System.out.println("addFileToStorageTest: File uploaded successfully");
                    System.out.println("addFileToStorageTest: PASSED");
                })
                .addOnFailureListener(e -> {
                    System.out.println("addFileToStorageTest: ERROR: " + e.getMessage());
                    System.out.println("addFileToStorageTest: FAIL");
                });
        Thread.sleep(3000);
    }


    @Test
    public void KgetImageUrlFromStorageTest() throws InterruptedException {
        StorageReference storageReference = FirebaseStorage.getInstance().getReference("uploads").child("1607777014552.jpg");
        storageReference.getDownloadUrl()
                .addOnSuccessListener(uri -> {
                    System.out.println("getImageUrlFromStorageTest: URL: " + uri.toString());
                    System.out.println("getImageUrlFromStorageTest: PASSED");
                })
                .addOnFailureListener(e -> {
                    System.out.println("getImageUrlFromStorageTest: ERROR: " + e.getMessage());
                    System.out.println("getImageUrlFromStorageTest: FAIL");
                });

        Thread.sleep(3000);
    }
}

package com.invictastudios.whatsappclone;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.invictastudios.whatsappclone.Model.Chat;
import com.invictastudios.whatsappclone.Model.Users;

import org.junit.Test;

import static org.junit.Assert.fail;

public class FirebaseIntegrationTests {

    @Test
    public void firebaseAuthRegisterUserTest() throws InterruptedException {
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
    public void firebaseAuthLoginUserTest() throws InterruptedException {
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
    public void addUserToDatabaseTest() throws InterruptedException {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        Users user = new Users("abcdefgh123456789", "someUsername", "default", "status");
        database.child("MyUsers").child(user.getId()).setValue(user)
                .addOnSuccessListener(aVoid -> database.child("MyUsers").child("abcdefgh123456789").get()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                System.out.println("addUserToDatabaseTest: User Successfully added to database: " + task.getResult().getValue());
                                System.out.println("PASSED");
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
    public void getAllUsersTest() throws InterruptedException {
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
    public void addMessageToDatabaseTest() throws InterruptedException {
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
    public void getMessagesWithUserId() throws InterruptedException {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("Chats");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);
                    if (chat != null)
                        if (chat.getSender().equals("someSender"))
                            System.out.println("getMessagesWithUserId: Message Received: " + chat.getMessage());
                }
                System.out.println("getMessagesWithUserId: PASSED");
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
    public void getImageUrlFromStorageTest() throws InterruptedException {
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

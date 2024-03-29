package com.invictastudios.whatsappclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.invictastudios.whatsappclone.adapter.MessageAdapter;
import com.invictastudios.whatsappclone.firebase.MessageTests;
import com.invictastudios.whatsappclone.model.Chat;
import com.invictastudios.whatsappclone.model.Users;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    private TextView username;
    private ImageView imageView;

    private RecyclerView recyclerView;
    private EditText msg_editText;

    private FirebaseUser firebaseUser;
    private DatabaseReference reference;

    private MessageAdapter messageAdapter;
    private List<Chat> chatList;
    private String userId;


    ValueEventListener seenListener;
    private MessageTests messageTests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        //Widgets
        imageView = findViewById(R.id.imageview_profile);
        username = findViewById(R.id.username);
        ImageButton sendBtn = findViewById(R.id.btn_send);
        msg_editText = findViewById(R.id.text_send);
        messageTests = new MessageTests();

        //RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();

        userId = intent.getStringExtra("userid");

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("MyUsers").child(userId);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Users user = dataSnapshot.getValue(Users.class);
                if (user != null) {
                    if (messageTests.isValidUsername(user.getUsername()))
                        username.setText(user.getUsername());

                    if (user.getImageURL().equals("default")) {
                        imageView.setImageResource(R.mipmap.ic_launcher);
                    } else {
                        Picasso.get().load(user.getImageURL()).into(imageView);
                    }

                    if (messageTests.validateUserId(userId))
                        readMessages(firebaseUser.getUid(), userId, user.getImageURL());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sendBtn.setOnClickListener(v -> {
            String msg = msg_editText.getText().toString();

            if (msg.length() <= 200) {
                if (messageTests.checkMessage(msg))
                    sendMessage(firebaseUser.getUid(), userId, msg);
                else
                    Toast.makeText(MessageActivity.this, "Please send a non empty message", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(MessageActivity.this, "Message too long", Toast.LENGTH_SHORT).show();

            msg_editText.setText("");

        });

        SeenMessage(userId);
    }

    private void SeenMessage(String userid) {
        reference = FirebaseDatabase.getInstance().getReference("Chats");
        seenListener = reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Chat chat = dataSnapshot.getValue(Chat.class);
                    if (chat != null) {
                        messageTests.messageSeen(userid, firebaseUser, chat, dataSnapshot);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void sendMessage(String sender, String receiver, String message) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        messageTests.sendMessageData(sender, receiver, message, hashMap);

        reference.child("Chats").push().setValue(hashMap);

        //Adding User to chat fragment: Latest Chats with contacts
        final DatabaseReference chatRef = FirebaseDatabase.getInstance()
                .getReference("ChatList")
                .child(firebaseUser.getUid())
                .child(userId);

        chatRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    chatRef.child("id").setValue(userId);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void readMessages(String myid, String userid, String imageurl) {

        chatList = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                chatList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);
                    messageTests.readMessageData(myid, userid, chat, chatList);
                    messageAdapter = new MessageAdapter(MessageActivity.this, MessageActivity.this.chatList, imageurl);
                    recyclerView.setAdapter(messageAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void CheckStatus(String status) {
        reference = FirebaseDatabase.getInstance().getReference("MyUsers").child(firebaseUser.getUid());
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", status);
        reference.updateChildren(hashMap);
    }

    @Override
    protected void onResume() {
        super.onResume();
        CheckStatus("online");
    }

    @Override
    protected void onPause() {
        super.onPause();
        reference.removeEventListener(seenListener);
        CheckStatus("offline");
    }


}
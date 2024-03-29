package com.invictastudios.whatsappclone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.invictastudios.whatsappclone.model.Chat;
import com.invictastudios.whatsappclone.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private final Context context;
    private final List<Chat> mChat;
    private final String imgURL;

    //Firebase
    FirebaseUser firebaseUser;

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;

    //Constructor
    public MessageAdapter(Context context, List<Chat> mChat, String imgURL) {
        this.context = context;
        this.mChat = mChat;
        this.imgURL = imgURL;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == MSG_TYPE_RIGHT) {
            view = LayoutInflater.from(context).inflate(R.layout.chat_item_right,
                    parent,
                    false);
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.chat_item_left,
                    parent,
                    false);
        }
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.ViewHolder holder, int position) {
        Chat chat = mChat.get(position);

        holder.show_message.setText(chat.getMessage());

        if (imgURL.equals("default")) {
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        } else {
            Picasso.get().load(imgURL).into(holder.profile_image);
        }

        if (position == mChat.size() - 1) {
            if (chat.isSeen()) {
                holder.txt_seen.setText(R.string.seen);
            } else {
                holder.txt_seen.setText(R.string.delivered);
            }
        } else {
            holder.txt_seen.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView show_message;
        public final ImageView profile_image;
        public final TextView txt_seen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            show_message = itemView.findViewById(R.id.show_message);
            profile_image = itemView.findViewById(R.id.profile_image);
            txt_seen = itemView.findViewById(R.id.txt_seen_status);
        }
    }

    @Override
    public int getItemViewType(int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mChat.get(position).getSender().equals(firebaseUser.getUid())) {
            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;
        }
    }
}

package com.example.hometask;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    @NonNull

    private List<UserInformation> users;
    public UserAdapter(List<UserInformation> users)
    {
        this.users =users;
    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_user,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        UserInformation userInformation= users.get(position);

        holder.userImage.setImageResource(userInformation.getImage());
        holder.id.setText(userInformation.getId());
        holder.user.setText(userInformation.getUser());
        holder.name.setText(userInformation.getName());
        holder.who.setText(userInformation.getWho());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView userImage;
        private TextView id,user,name,who;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userImage = itemView.findViewById(R.id.userImage);
            id = itemView.findViewById(R.id.id);
            user = itemView.findViewById(R.id.user);
            name = itemView.findViewById(R.id.name);
            who = itemView.findViewById(R.id.who);
        }
    }
}

package com.example.hometask;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    Context context;
    private List<UserInformation> users;



    public UserAdapter(Context context, List<UserInformation> users) {
        this.context = context;
        this.users = users;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_user,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final UserInformation userInformation= users.get(position);

        holder.userImage.setImageResource(userInformation.getImage());
        holder.id.setText(userInformation.getId());
        holder.user.setText(userInformation.getUser());
        holder.name.setText(userInformation.getName());
        holder.who.setText(userInformation.getWho());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,SecondActivity.class);
                intent.putExtra("userImage",userInformation.getImage());
                intent.putExtra("id",userInformation.getId());
                intent.putExtra("user",userInformation.getUser());
                intent.putExtra("name",userInformation.getName());
                intent.putExtra("who",userInformation.getWho());

                context.startActivity(intent);

            }
        });
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

package com.example.hometask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {


private  int image;
private int id;
private String user;
private String name;
private String who;

private ImageView imageView;
private TextView idTV,userTV,nameTV,whoTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        init();


        if(getIntent().getExtras() !=null)
        {
            image =getIntent().getIntExtra("userImage",0);
            id =getIntent().getIntExtra("id",0);
            user =getIntent().getStringExtra("user");
            name = getIntent().getStringExtra("name");
            who = getIntent().getStringExtra("who");

            imageView.setImageResource(image);
            idTV.setText(id);
            userTV.setText(user);
            nameTV.setText(name);
            whoTV.setText(who);
        }
    }

    private void init() {

        imageView =findViewById(R.id.imageSecond);
        idTV =findViewById(R.id.idTV);
        userTV =findViewById(R.id.userTV);
        nameTV=findViewById(R.id.nameTV);
        whoTV =findViewById(R.id.whoTV);
    }
}

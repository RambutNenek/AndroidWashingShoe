package com.example.sepatuu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView id,userName,userEmail,gender;
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(SharePrefManager.getInstance(this).isLoggedIn()){
            id = findViewById(R.id.textViewId);
            userName = findViewById(R.id.textViewUsername);
            userEmail = findViewById(R.id.textViewEmail);
            gender = findViewById(R.id.textViewGender);
            btnLogout = findViewById(R.id.buttonLogout);
            user user = SharePrefManager.getInstance(this).getUser();

            id.setText(String.valueOf(user.getId()));
            userEmail.setText(user.getEmail());
            gender.setText(user.getGender());
            userName.setText(user.getName());

            btnLogout.setOnClickListener(this);
        }
        else{
            Intent  intent = new Intent(MainActivity.this,activity_login.class);
            startActivity(intent);
            finish();
        }
    }
    public void onClick(View view){
        if(view.equals(btnLogout)){
            SharePrefManager.getInstance(getApplicationContext()).logout();
        }
    }
}
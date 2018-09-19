package com.example.amotie.pharmacyproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void Create_Account(View view){
        Intent intent=new Intent(this,Signup.class);
        startActivity(intent);
    }
    public void Login(View view){
Intent intent=new Intent(this,MainActivity.class);
startActivity(intent);
    }
}

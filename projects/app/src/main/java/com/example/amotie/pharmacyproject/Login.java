package com.example.amotie.pharmacyproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.math.BigInteger;

public class Login extends AppCompatActivity {
    EditText username,password;
    ImageView logo;
LinearLayout linearLayout;
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        logo=(ImageView)findViewById(R.id.logo);
        linearLayout=(LinearLayout)findViewById(R.id.liner);
        progressBar=(ProgressBar)findViewById(R.id.progressbar);
    }
    public void Create_Account(View view){
        Intent intent=new Intent(this,Signup.class);
        startActivity(intent);
    }
    public void Login(View view){
        String usernameText=username.getText().toString();
        ConnectivityManager connectivityManager=(ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        boolean isConnected=networkInfo!=null&&networkInfo.isConnectedOrConnecting();
        if(isConnected==true){
            byte[]md5input=password.getText().toString().getBytes();
            BigInteger md5data=null;

            try {
                md5data=new BigInteger(1,md5.encryptMD5(md5input));
            } catch (Exception e) {
                e.printStackTrace();
            }
            String md5st = md5data.toString(16);
Network network=new Network(Login.this);
network.execute(usernameText,md5st);

        }
        else{
            Toast.makeText(getApplicationContext(), "Connect to Network  ", Toast.LENGTH_SHORT).show();

        }


    }
}

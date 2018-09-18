package com.example.amotie.lifebook;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

EditText username,password;
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=(EditText)findViewById(R.id.Email);
        password=(EditText)findViewById(R.id.Password);
        progressBar=(ProgressBar)findViewById(R.id.prog);



    }
    public void SignUp(View view){
        
    }
    public void login(View view){
        String usernameText = username.getText().toString();

        ConnectivityManager connectivityManager=(ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        boolean isConnected=networkInfo!=null&&networkInfo.isConnectedOrConnecting();
        if(isConnected==true) {
            byte[]md5input=password.getText().toString().getBytes();
            BigInteger md5data=null;

            try {
                md5data=new BigInteger(1,md5.encryptMD5(md5input));
            } catch (Exception e) {
                e.printStackTrace();
            }
            String md5st = md5data.toString(16);

            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            backgroundWorker.execute("login", usernameText, md5st);
        }
        else{
            Toast.makeText(getApplicationContext(), "Connect to Network  ", Toast.LENGTH_SHORT).show();

        }
    }
}

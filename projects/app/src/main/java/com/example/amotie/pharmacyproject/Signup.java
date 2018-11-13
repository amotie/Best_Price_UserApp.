package com.example.amotie.pharmacyproject;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;

public class Signup extends AppCompatActivity {
    EditText username, email, phone, password, password1, address, companyname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username = (EditText) findViewById(R.id.usename);
        email = (EditText) findViewById(R.id.email);
       phone = (EditText) findViewById(R.id.phone3);
        password = (EditText) findViewById(R.id.password);
        password1 = (EditText) findViewById(R.id.password1);
        address = (EditText) findViewById(R.id.address);
        companyname = (EditText) findViewById(R.id.companyname);
    }

    public void SignUp(View view) {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
        if (isConnected == true) {
            if (password.getText().toString().equals(password1.getText().toString()) ) {

                byte[] md5input = password.getText().toString().getBytes();
                BigInteger md5data = null;

                try {
                    md5data = new BigInteger(1, md5.encryptMD5(md5input));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String md5st = md5data.toString(16);
                String usernamt=username.getText().toString();


                String emailt=email.getText().toString();
                String phonet=phone.getText().toString();
                String addresst=address.getText().toString();
                String comp=companyname.getText().toString();
                System.out.println(usernamt);

                NetworkRegester networkRegester = new NetworkRegester(Signup.this);
                networkRegester.execute(usernamt,emailt,phonet, md5st,addresst,comp);
            }
            else{
                Toast.makeText(getApplicationContext(), "Password is not correct ", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Connect to Network  ", Toast.LENGTH_SHORT).show();
        }
    }
}

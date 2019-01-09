package com.example.amotie.pharmacyproject;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigInteger;

public class Signup extends AppCompatActivity {
    EditText username, email, phone, password, password1, address, companyname;
LinearLayout l1,l2,l3,l4,l5,l6;
    Animation in;
    Animation out;

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
        l1=(LinearLayout)findViewById(R.id.Lin1);
        l2=(LinearLayout)findViewById(R.id.lin2);
        l3=(LinearLayout)findViewById(R.id.lin3);
        l4=(LinearLayout)findViewById(R.id.lin4);
        l5=(LinearLayout)findViewById(R.id.lin5);
        l6=(LinearLayout)findViewById(R.id.lin6);
      in  = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.in);
        out=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.out);
        in.setDuration(400);
        out.setDuration(400);
        username.setOnEditorActionListener(editorActionListener);
        email.setOnEditorActionListener(editorActionListener);
        phone.setOnEditorActionListener(editorActionListener);
        password1.setOnEditorActionListener(editorActionListener);
        address.setOnEditorActionListener(editorActionListener);
        companyname.setOnEditorActionListener(editorActionListener);







    }
    private TextView.OnEditorActionListener editorActionListener=new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if(v==username&&actionId==EditorInfo.IME_ACTION_SEND){
                Next1(v);
            }
            else if(v==email&&actionId==EditorInfo.IME_ACTION_SEND){
                Next2(v);
            }
            else if (v==phone&&actionId==EditorInfo.IME_ACTION_SEND){
                Next3(v);
            }
            else if(v==password1&&actionId==EditorInfo.IME_ACTION_SEND){
                Next4(v);
            }
            else if(v==address&&actionId==EditorInfo.IME_ACTION_SEND){
                Next5(v);
            }
            else if(v==companyname&&actionId==EditorInfo.IME_ACTION_SEND){
                SignUp(v);
            }
            return false;
        }
    };
    public void Next1(View view){
        //l1.setAnimation(out);
        l1.setVisibility(View.GONE);

        l2.setAnimation(in);
        l2.setVisibility(View.VISIBLE);
    }

    public void Next2(View view){
        in.setDuration(400);
        out.setDuration(400);
       // l2.setAnimation(out);
        String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(email.getText().toString().trim().matches(emailPattern)) {

            l2.setVisibility(View.GONE);
            l3.setAnimation(in);

            l3.setVisibility(View.VISIBLE);
        }
        else {
            Toast.makeText(getApplicationContext(), "Enter Valid Email", Toast.LENGTH_SHORT).show();

        }
    }

    public void Next3(View view){
        in.setDuration(400);
        out.setDuration(400);
       // l3.setAnimation(out);
        l3.setVisibility(View.GONE);
        l4.setAnimation(in);
        l4.setVisibility(View.VISIBLE);
    }

    public void Next4(View view){
        in.setDuration(400);
        out.setDuration(400);
        //l4.setAnimation(out);
        l4.setVisibility(View.GONE);
        l5.setAnimation(in);
        l5.setVisibility(View.VISIBLE);
    }

    public void Next5(View view){
        in.setDuration(400);
        out.setDuration(400);
       // l5.setAnimation(out);
        l5.setVisibility(View.GONE);
        l6.setAnimation(in);
        l6.setVisibility(View.VISIBLE);
    }

    public void SignUp(View view) {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
        if(username.getText().toString().isEmpty()||email.getText().toString().isEmpty()||phone.getText().toString().isEmpty()||password.getText().toString().isEmpty()||password1.getText().toString().isEmpty()||address.getText().toString().isEmpty()||companyname.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter Data ", Toast.LENGTH_SHORT).show();
        }
        else {


            if (isConnected == true) {
                if (password.getText().toString().equals(password1.getText().toString())) {

                        byte[] md5input = password.getText().toString().getBytes();
                        BigInteger md5data = null;

                        try {
                            md5data = new BigInteger(1, md5.encryptMD5(md5input));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        String md5st = md5data.toString(16);
                        String usernamt = username.getText().toString();


                        String emailt = email.getText().toString();
                        String phonet = phone.getText().toString();
                        String addresst = address.getText().toString();
                        String comp = companyname.getText().toString();
                        System.out.println(usernamt);

                        NetworkRegester networkRegester = new NetworkRegester(Signup.this);
                        networkRegester.execute(usernamt, emailt, phonet, md5st, addresst, comp);


                } else {
                    Toast.makeText(getApplicationContext(), "Password is not correct ", Toast.LENGTH_SHORT).show();
                }
            }
            else{

                Toast.makeText(getApplicationContext(), "Connect to Network  ", Toast.LENGTH_SHORT).show();


            }
        }
    }
}

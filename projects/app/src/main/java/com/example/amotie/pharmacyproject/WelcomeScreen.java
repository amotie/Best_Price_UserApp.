package com.example.amotie.pharmacyproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        Thread thread =new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                    SharedPreferences sharedPreferences =getSharedPreferences("userInfo",MODE_PRIVATE);

                    if(sharedPreferences.getString("username","").length()==0){
                 /*       Network network=new Network();
                        network.execute(sharedPreferences.getString("username",""),sharedPreferences.getString("password",""));
*/
                        Intent intent=new Intent(getApplicationContext(),Login.class);
                        startActivity(intent);
                    }
                    else {


                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);

                    }
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        thread.start();

    }
}

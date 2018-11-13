package com.example.amotie.pharmacyproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.widget.TextView;

public class ItemContent extends AppCompatActivity {
TextView medicne;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_content);
        medicne=(TextView)findViewById(R.id.medecine);
        Intent intent=getIntent();
        if(intent.hasExtra("medcine")){
         medicne.setText(intent.getStringExtra("medcine"));
        }
    }
}

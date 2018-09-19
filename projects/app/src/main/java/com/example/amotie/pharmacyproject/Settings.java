package com.example.amotie.pharmacyproject;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

    }
    public void ChangeUsername(View view){
        AlertDialog.Builder mbelder = new AlertDialog.Builder(Settings.this);
        View view1 = getLayoutInflater().inflate(R.layout.changeusername, null);


       ImageView Close=(ImageView) view1.findViewById(R.id.Close);
        EditText NewUsername=(EditText)view1.findViewById(R.id.New_username);
        Button Update=(Button)view1.findViewById(R.id.Updateusername_Btn);
        mbelder.setView(view1);
        final AlertDialog dialog = mbelder.create();
        dialog.show();
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });


    }
    public void ChangeEmail(View view){
        AlertDialog.Builder mbelder = new AlertDialog.Builder(Settings.this);
        View view1 = getLayoutInflater().inflate(R.layout.changeemail, null);


        ImageView Close=(ImageView) view1.findViewById(R.id.Close);
        EditText NewUEmail=(EditText)findViewById(R.id.New_Email);
        Button Update=(Button)findViewById(R.id.UpdateEmail_Btn);
        mbelder.setView(view1);
        final AlertDialog dialog = mbelder.create();

        dialog.show();
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
    }
    public void ChangrPassword(View view){
        AlertDialog.Builder mbelder = new AlertDialog.Builder(Settings.this);
        View view1 = getLayoutInflater().inflate(R.layout.changepassword, null);
        ImageView Close=(ImageView) view1.findViewById(R.id.Close);
        EditText Oldpassword=(EditText)findViewById(R.id.OldPasseord);
        EditText NewPassword=(EditText)findViewById(R.id.New_Password);
        EditText NewPassword2=(EditText)findViewById(R.id.New_Password2);
        Button Update=(Button)findViewById(R.id.UpdatePassword_Btn);
        mbelder.setView(view1);
        final AlertDialog dialog = mbelder.create();

        dialog.show();
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
    }
    public void ChangeAddress(View view){
        AlertDialog.Builder mbelder = new AlertDialog.Builder(Settings.this);
        View view1 = getLayoutInflater().inflate(R.layout.changeaddress, null);


        ImageView Close=(ImageView) view1.findViewById(R.id.Close);
        EditText NewAddress=(EditText)findViewById(R.id.New_Address);
        Button Update=(Button)findViewById(R.id.UpdateAddress_Btn);
        mbelder.setView(view1);
        final AlertDialog dialog = mbelder.create();

        dialog.show();
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
    }
    public void ChangeNumber(View view){
        AlertDialog.Builder mbelder = new AlertDialog.Builder(Settings.this);
        View view1 = getLayoutInflater().inflate(R.layout.changenumber, null);


        ImageView Close=(ImageView) view1.findViewById(R.id.Close);
        EditText NewNumber=(EditText)findViewById(R.id.New_Number);
        Button Update=(Button)findViewById(R.id.Updatenumber_Btn);
        mbelder.setView(view1);
        final AlertDialog dialog = mbelder.create();

        dialog.show();
        Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
    }
    public void AboutUs(View view){

    }


}

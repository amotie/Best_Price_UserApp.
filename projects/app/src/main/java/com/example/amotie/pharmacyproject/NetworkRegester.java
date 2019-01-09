package com.example.amotie.pharmacyproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class NetworkRegester extends AsyncTask<String,Void,String> {
    private WeakReference<Signup> activityWeakReference;
    NetworkRegester(Signup activity){
        activityWeakReference=new WeakReference<Signup>(activity);
    }
    @Override
    protected String doInBackground(String... strings) {
        String Regester_Url= "https://bestdiscounteg.com/Android/Regester.php";


        try {
                String username=strings[0];
                String email=strings[1];
                String phone=strings[2];
                String password=strings[3];
                String Address=strings[4];
                String pharmacy_name=strings[5];

                URL url=new URL(Regester_Url);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");

                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                    OutputStream outputStream=httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"utf-8"));
                String post_data= URLEncoder.encode("username","utf-8")+"="+URLEncoder.encode(username,"utf-8")+"&"
                        +URLEncoder.encode("email","utf-8")+"="+URLEncoder.encode(email,"utf-8")+"&"
                        +URLEncoder.encode("role","utf-8")+"="+URLEncoder.encode("1","utf-8")+"&"
                        +URLEncoder.encode("phone","utf-8")+"="+URLEncoder.encode(phone,"utf-8")+"&"
                        +URLEncoder.encode("password","utf-8")+"="+URLEncoder.encode(password,"utf-8")+"&"
                        +URLEncoder.encode("Address","utf-8")+"="+URLEncoder.encode(Address,"utf-8")+"&"
                        +URLEncoder.encode("Pharmacy_Name","utf-8")+"="+URLEncoder.encode(pharmacy_name,"utf-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line;
                while ((line=bufferedReader.readLine())!=null){
                    result+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        return null;
    }
    @Override
    protected void onPostExecute(String s) {
        Signup activity=activityWeakReference.get();
System.out.println(s);
        if(s.equals("Record Added")){
            if(activity==null||activity.isFinishing()){
                return;
            }
            Intent intent=new Intent(activity,Login.class);
            activity.startActivity(intent);
        }
        else{
            if(activity==null||activity.isFinishing()){
                return;
            }
            Toast.makeText(activity,s,Toast.LENGTH_SHORT).show();
        }
        System.out.println(s);
        super.onPostExecute(s);
    }
}

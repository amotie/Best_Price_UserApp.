package com.example.amotie.pharmacyproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import java.util.ArrayList;

public class NetwordDrop extends AsyncTask<String,Void,String> {
    private WeakReference<Login> activityWeakReference;
    NetwordDrop(Login activity){
        activityWeakReference=new WeakReference<Login>(activity);
    }

    @Override
    protected void onPreExecute() {
        Login activity = activityWeakReference.get();
        if (activity == null || activity.isFinishing()) {
            return;
        }

        super.onPreExecute();
    }

    @Override

    protected String doInBackground(String... strings) {

        String LoginUrl="https://bestdiscounteg.com/Android/login1.php";
        try {
            String username=strings[0];

            URL url= new URL(LoginUrl);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"utf-8"));
            String post_data=URLEncoder.encode("username","utf-8")+"="+URLEncoder.encode(username,"utf-8");
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
        Login activity=activityWeakReference.get();
//System.out.println(s.get(0));
        if(s.equals("Done")){
            if(activity==null||activity.isFinishing()){
                return;
            }
            Toast.makeText(activity,s,Toast.LENGTH_SHORT).show();


        }
        else {
            if(activity==null||activity.isFinishing()){
                return;
            }

            Toast.makeText(activity,"Username or password is Wrong",Toast.LENGTH_SHORT).show();

        }
        super.onPostExecute(s);
    }
}

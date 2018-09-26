package com.example.amotie.pharmacyproject;

import android.content.Intent;
import android.content.UriMatcher;
import android.os.AsyncTask;
import android.view.View;
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

public class Network extends AsyncTask<String,Void,String> {
    private WeakReference<Login> activityWeakReference;
    Network(Login activity){
        activityWeakReference=new WeakReference<Login>(activity);
    }

    @Override
    protected void onPreExecute() {
        Login activity=activityWeakReference.get();
        if(activity==null||activity.isFinishing()){
            return;
        }
        activity.linearLayout.setVisibility(View.GONE);
        activity.logo.setVisibility(View.GONE);
        activity.progressBar.setVisibility(View.VISIBLE);
        super.onPreExecute();
    }

    @Override

    protected String doInBackground(String... strings) {

      String LoginUrl="http://amotie.000webhostapp.com/login.php";
        try {
            String username=strings[0];
            String password=strings[1];
            URL url= new URL(LoginUrl);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"utf-8"));
            String post_data=URLEncoder.encode("username","utf-8")+"="+URLEncoder.encode(username,"utf-8")+"&"+
                    URLEncoder.encode("password","utf-8")+"="+URLEncoder.encode(password,"utf-8");
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
System.out.println(s);
if(s.equals("1")){
    if(activity==null||activity.isFinishing()){
        return;
    }
    activity.progressBar.setVisibility(View.INVISIBLE);
    activity.linearLayout.setVisibility(View.VISIBLE);
    activity.logo.setVisibility(View.VISIBLE);
    Intent intent=new Intent(activity,MainActivity.class);
    activity.startActivity(intent);
}
else {
    if(activity==null||activity.isFinishing()){
    return;
}

Toast.makeText(activity,s,Toast.LENGTH_SHORT).show();
    activity.progressBar.setVisibility(View.INVISIBLE);
    activity.linearLayout.setVisibility(View.VISIBLE);
    activity.logo.setVisibility(View.VISIBLE);
}
        super.onPostExecute(s);
    }
}

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
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

public class addtoCart extends AsyncTask<String,Void,String> {
    private WeakReference<MainActivity> activityWeakReference;
    addtoCart(MainActivity activity){
        activityWeakReference=new WeakReference<MainActivity>(activity);
    }
    @Override
    protected void onPreExecute() {
        MainActivity activity=activityWeakReference.get();
        if(activity==null||activity.isFinishing()){
            return;
        }
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String Url = "https://bestdiscounteg.com/Android/cart.php";

        try {
           String id = strings[0];
            String Amount=strings[1];
            String MedID = strings[2];

            URL url = new URL(Url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
            String post_data = URLEncoder.encode("ID","utf-8")+"="+URLEncoder.encode(id,"utf-8")+"&"+
                    URLEncoder.encode("Amount","utf-8")+"="+URLEncoder.encode(Amount,"utf-8")+"&"+
                    URLEncoder.encode("MedID","utf-8")+"="+URLEncoder.encode(MedID,"utf-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
            String result = "";
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

return null;
    }

    @Override
    protected void onPostExecute(String s) {
        MainActivity  activity=activityWeakReference.get();

        if(s.equals("Done")){
            if(activity==null||activity.isFinishing()){
                 return;
            }

            Toast.makeText(activity,"Added",Toast.LENGTH_SHORT).show();
        }
        else {
            if(activity==null||activity.isFinishing()){
                return;
            }

            Toast.makeText(activity,"Cant Add To Cart",Toast.LENGTH_SHORT).show();

        }
        super.onPostExecute(s);
    }
}
package com.example.amotie.pharmacyproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class NetworkChangeAddress extends AsyncTask<String,Void,String[]> {
    private WeakReference<Settings> activityWeakReference;
    NetworkChangeAddress(Settings activity){
        activityWeakReference=new WeakReference<Settings>(activity);
    }

    @Override
    protected void onPreExecute() {
        Settings activity=activityWeakReference.get();
        if(activity==null||activity.isFinishing()){
            return;
        }


        super.onPreExecute();
    }

    @Override

    protected String[] doInBackground(String... strings) {

        String UserUpdateUrl="https://bestdiscounteg.com/Android/updateUsers/updateAdress.php";
        try {
            String id=strings[0];
            String address=strings[1];
            URL url= new URL(UserUpdateUrl);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"utf-8"));
            String post_data= URLEncoder.encode("id","utf-8")+"="+URLEncoder.encode(id,"utf-8")+"&"+
                    URLEncoder.encode("Adress","utf-8")+"="+URLEncoder.encode(address,"utf-8");
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
            String[]output={result,id,address};
            return output;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String[] s) {
        Settings  activity=activityWeakReference.get();
        System.out.println(s[0]);
        if(s[0].equals("Record Updated")){
            if(activity==null||activity.isFinishing()){
                return;
            }

            SharedPreferences sharedPreferences=activity.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("id",s[1]);
            editor.putString("Adress",s[2]);
            editor.apply();
            Intent intent=new Intent(activity,MainActivity.class);
            activity.startActivity(intent);
        }
        else {
            if(activity==null||activity.isFinishing()){
                return;
            }

            Toast.makeText(activity,"Cant Update Address",Toast.LENGTH_SHORT).show();

        }
        super.onPostExecute(s);
    }
}

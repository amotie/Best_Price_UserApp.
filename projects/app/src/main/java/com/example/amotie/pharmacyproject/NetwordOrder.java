package com.example.amotie.pharmacyproject;

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

public class NetwordOrder extends AsyncTask<String,Void,String> {
    private WeakReference<ShoppingCart> activityWeakReference;
    NetwordOrder(ShoppingCart activity){
        activityWeakReference=new WeakReference<ShoppingCart>(activity);
    }

    @Override
    protected void onPreExecute() {
        ShoppingCart activity=activityWeakReference.get();
        if(activity==null||activity.isFinishing()){
            return;
        }
        super.onPreExecute();
    }


    @Override
    protected String doInBackground(String... strings) {
        String UserUpdateUrl="https://bestdiscounteg.com/Android/Order.php";
        try {
            String id=strings[0];
            String amount=strings[1];
            String medID=strings[2];
            String CartID=strings[3];
            URL url= new URL(UserUpdateUrl);
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream=httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"utf-8"));
            String post_data= URLEncoder.encode("ID","utf-8")+"="+URLEncoder.encode(id,"utf-8")+"&"+
                    URLEncoder.encode("Amount","utf-8")+"="+URLEncoder.encode(amount,"utf-8") +"&"+
                    URLEncoder.encode("MedID","utf-8")+"="+URLEncoder.encode(medID,"utf-8")+"&"+
                    URLEncoder.encode("CartID","utf-8")+"="+URLEncoder.encode(CartID,"utf-8");
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
        ShoppingCart  activity=activityWeakReference.get();



            Toast.makeText(activity,s,Toast.LENGTH_SHORT).show();

        super.onPostExecute(s);
    }
}

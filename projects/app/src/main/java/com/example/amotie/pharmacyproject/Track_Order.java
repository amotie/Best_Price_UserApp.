package com.example.amotie.pharmacyproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;


public class Track_Order extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track__order);
        ListView listView=(ListView)findViewById(R.id.Track_List);
        TarackAdaptor tarackAdaptor=new TarackAdaptor();
        listView.setAdapter(tarackAdaptor);
    }
    class TarackAdaptor extends BaseAdapter{

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
LayoutInflater inflater=getLayoutInflater();
View row;
row=inflater.inflate(R.layout.track_orderlistdata,parent,false);


            return row;
        }
    }
}

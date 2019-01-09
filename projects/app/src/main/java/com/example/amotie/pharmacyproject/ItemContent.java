package com.example.amotie.pharmacyproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class ItemContent extends AppCompatActivity {
    ArrayList<TableItem> tableItems=new ArrayList<>();
    ListView listView;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_content);
  String[]headerName={"Produc Name","Price","Amount","Company","Sale"};
        //tableItems.add(new TableItem("Clartine","Company Name",100,20,5));
        //tableItems.add(new TableItem("Clartine","Company Name",100,20,5));
        //tableItems.add(new TableItem("Clartine","Company Name",100,20,5));
        String[][]Item={{"Clartine","100","20","Company Name","5"},{"Clartine","100","20","Company Name","5"},{"Clartine","100","20","Company Name","5"},{"Clartine","100","20","Company Name","5"}};
        TableView tableView=(TableView)findViewById(R.id.list1);
        tableView.setColumnCount(6);

        tableView.setHeaderBackground(R.color.colorAccent);
        tableView.setHeaderAdapter(new SimpleTableHeaderAdapter(this,headerName));
        tableView.setDataAdapter(new SimpleTableDataAdapter(ItemContent.this,Item));

//listView=(ListView)findViewById(R.id.itemList);
//TableListAdapter tableListAdapter=new TableListAdapter(this,tableItems);

        context =this;

      //  listView.setAdapter(tableListAdapter);
    }
    class TableItem{
        String Product_Name;
        String Company_Name;
        int Price;
        int Amount;
        int Sale;

        TableItem(String Product_Name,String Company_Name,int price,int Amount,int Sale){
            this.Product_Name=Product_Name;
            this.Company_Name=Company_Name;
            this.Price=price;
            this.Amount=Amount;
            this.Sale=Sale;
        }

    }
    class TableListAdapter extends BaseAdapter {
        ArrayList<TableItem> tableItems;
        Context context;
        TableListAdapter(Context c,ArrayList<TableItem> tableItems1){
            context=c;
            tableItems=tableItems1;

            System.out.println("Asss");


        }
        @Override
        public int getCount() {
            if(tableItems.size()==0){

            }
            return tableItems.size();
        }

        @Override
        public Object getItem(int position) {
            return tableItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate(R.layout.searchtabledata,parent,false);
            TextView Name=(TextView)row.findViewById(R.id.product_Name);
            TextView Company=(TextView)row.findViewById(R.id.Company);
            TextView Price=(TextView)row.findViewById(R.id.Price);
            final TextView Amount=(TextView) row.findViewById(R.id.Amount);
            TextView Sale=(TextView) row.findViewById(R.id.sale);
            final TableItem tableItem=tableItems.get(position);
            Name.setText(tableItem.Product_Name);
            Company.setText(tableItem.Company_Name);
            Price.setText(Integer.toString(tableItem.Price)+" EGP");
            Amount.setText(Integer.toString(tableItem.Amount));
            Sale.setText(Integer.toString(tableItem.Sale));



            return row;
        }

    }
}

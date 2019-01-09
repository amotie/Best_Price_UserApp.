package com.example.amotie.pharmacyproject;

import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

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


public class ShoppingCart extends AppCompatActivity {
android.support.v7.widget.Toolbar toolbar;

    TextView sub_total;
  public  ArrayList<shopingListItem> shopingListItems=new ArrayList<>();
    SwipeMenuListView listView;
    ShoppingListAdapter shoppingListAdapter;
    Context context;
    SwipeMenuCreator creator;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shopping_cart);

        setSupportActionBar(toolbar);
        ConnectivityManager connectivityManager=(ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        boolean isConnected=networkInfo!=null&&networkInfo.isConnectedOrConnecting();
        if(isConnected==true) {
            SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
            String id = sharedPreferences.getString("id", "");
            DespalyCart despalyCary = new DespalyCart(ShoppingCart.this);
            despalyCary.execute(id);
        }
        else{
            Toast.makeText(ShoppingCart.this,"No Enternet Connection ",Toast.LENGTH_SHORT).show();


        }






      listView =(SwipeMenuListView) findViewById(R.id.ShoppingList);
        sub_total=(TextView)findViewById(R.id.Total_Price);



         creator=new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem openitem=new SwipeMenuItem(getApplicationContext());
                openitem.setBackground(R.drawable.borderblue);
                openitem.setWidth(220);
                openitem.setTitle("Delete");
                openitem.setIcon(R.drawable.ic_action_name);
                menu.addMenuItem(openitem);

            }
        };

listView.setMenuCreator(creator);
listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
    @Override
    public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
        switch (index){
            case 0:
                System.out.println(position);
shopingListItem shopingListItem=shoppingListAdapter.shoppingListItems.get(position);

                shoppingListAdapter.shoppingListItems.remove(shopingListItem);
shoppingListAdapter=new ShoppingListAdapter(context,shopingListItems);
listView.setAdapter(shoppingListAdapter);
DeleteCart deleteCart=new DeleteCart(ShoppingCart.this);
deleteCart.execute(shopingListItem.id);



                break;

        }
        return false;
    }
});

        }




public void Cheackout(View view){
    ConnectivityManager connectivityManager=(ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
    boolean isConnected=networkInfo!=null&&networkInfo.isConnectedOrConnecting();
    if(isConnected==true) {

        for (int i = 0; i < shopingListItems.size(); i++) {
            shopingListItem shopingListItem1 = shopingListItems.get(i);
            NetwordOrder networdOrder = new NetwordOrder(ShoppingCart.this);
            SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
            final String id = sharedPreferences.getString("id", "");
            networdOrder.execute(id, shopingListItem1.Amount, shopingListItem1.MedID, shopingListItem1.id);


        }
        shopingListItems.clear();
        shoppingListAdapter.notifyDataSetChanged();
    }
    else{
        Toast.makeText(getApplicationContext(), "Connect to Network  ", Toast.LENGTH_SHORT).show();

    }

}
    public  String Total(ArrayList<shopingListItem>shopingListItems){
        double z=0;
        for(int i=0;i<shopingListItems.size();i++){
          shopingListItem listItem  =shopingListItems.get(i);
        // System.out.print( listItem.Company_Name);
         System.out.println(listItem.Price);
      z=z+Double.parseDouble(listItem.Price);
        }
         String f=Double.toString(z);
        System.out.println(f);
        return f;
        }
        static class shopingListItem{
        String Product_Name;
        String Company_Name;
        String Price;
        String Amount;
        String sale;
        String id;
        String MedID;


        shopingListItem(String Product_Name,String Company_Name,String price,String Amount,String sale,String ID,String medID){
            this.Product_Name=Product_Name;
            this.Company_Name=Company_Name;
            this.Price=price;
            this.Amount=Amount;
            this.sale=sale;
            id=ID;
            MedID=medID;
        }

        }
    class ShoppingListAdapter extends BaseAdapter{
ArrayList<shopingListItem> shoppingListItems;
Context context;
ShoppingListAdapter(Context c,ArrayList<shopingListItem> shopingListItems1){
    context=c;
    shoppingListItems=shopingListItems1;

    System.out.println("Ali");


}
        @Override
        public int getCount() {
    if(shoppingListItems.size()==0){
        sub_total.setText("0 EGP");
    }
            return shoppingListItems.size();
        }

        @Override
        public Object getItem(int position) {
            return shoppingListItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row=inflater.inflate(R.layout.shoppinglistdata,parent,false);
       TextView Name=(TextView)row.findViewById(R.id.product_Name);
            TextView Company=(TextView)row.findViewById(R.id.Company_Name);
            TextView Price=(TextView)row.findViewById(R.id.Price);
            final TextView Amount=(TextView) row.findViewById(R.id.Amount);
            //Button Ok=(Button) row.findViewById(R.id.Ok);
            final shopingListItem shopingListItem=shoppingListItems.get(position);
            Name.setText(shopingListItem.Product_Name);
            Company.setText(shopingListItem.Company_Name);
            Price.setText(shopingListItem.Price+" EGP");
            Amount.setText(shopingListItem.Amount);
            String z=Total(shoppingListItems);
            sub_total.setText(z+" EGP");
/*Ok.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        shopingListItem.Amount=Integer.parseInt(Amount.getText().toString());
       String z= Total(shoppingListItems);
        sub_total.setText(z+" EGP");

    }
});*/

            return row;
        }

    }
    public class DespalyCart extends AsyncTask<String,Void,ArrayList<shopingListItem>> {
        private WeakReference<ShoppingCart> activityWeakReference;
        DespalyCart(ShoppingCart activity){
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
        protected ArrayList<shopingListItem> doInBackground(String... strings) {
            String UserUpdateUrl="https://bestdiscounteg.com/Android/DespalyCart.php";
            try {
                String id=strings[0];

                URL url= new URL(UserUpdateUrl);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"utf-8"));
                String post_data= URLEncoder.encode("ID","utf-8")+"="+URLEncoder.encode(id,"utf-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                ArrayList<shopingListItem> arrayList=new ArrayList<>();
                String []row=new String[7];
                String line;

                int i=0;
                while ((line=bufferedReader.readLine())!=null){
                    row[i]=line;
                    System.out.println(row[i]);

                    if(i==6){
                        i=0;
                        Log.d("A7a",row[0]);
                        arrayList.add( new shopingListItem(row[0],row[1],row[2],row[3],row[4],row[5],row[6]));

                    }
                    else {
                        i++;
                    }
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return arrayList;
            } catch (MalformedURLException e) {

                e.printStackTrace();
            } catch (IOException e) {

                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(ArrayList<shopingListItem> strings) {
            ShoppingCart  activity=activityWeakReference.get();




            if(activity==null||activity.isFinishing()){
                return;
            }

            activity.shopingListItems=strings;
            shoppingListAdapter   =new ShoppingListAdapter(activity,shopingListItems);
            context =activity;
            activity.listView.setAdapter(shoppingListAdapter);
            super.onPostExecute(strings);
        }


    }





}

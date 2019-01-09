package com.example.amotie.pharmacyproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
MaterialSearchView materialSearchView;
    ArrayList<TableItem> tableItems=new ArrayList<>();
    ListView listView;
LinearLayout linearLayout;
    int i=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView=(ListView)findViewById(R.id.ListSearch);


linearLayout=(LinearLayout)findViewById(R.id.NoResult);





materialSearchView=(MaterialSearchView)findViewById(R.id.mysearch);
materialSearchView.closeSearch();



        materialSearchView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
materialSearchView.setTextColor(getResources().getColor(R.color.colorAccent));
materialSearchView.setHintTextColor(getResources().getColor(R.color.colorAccent));

materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
    @Override
    public boolean onQueryTextSubmit(String query) {
        ConnectivityManager connectivityManager=(ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        boolean isConnected=networkInfo!=null&&networkInfo.isConnectedOrConnecting();
        if(isConnected==true) {

            tableItems.clear();
            SearchClick searchClick = new SearchClick(MainActivity.this);
            searchClick.execute(query);
        }
        else{
            Toast.makeText(MainActivity.this,"No Enternet Connection ",Toast.LENGTH_SHORT).show();



        }





        // System.out.println(tableItems.get(0).Amount);








        //   listView=(ListView)findViewById(R.id.list1);



      return false;
    }



    @Override
    public boolean onQueryTextChange(String newText) {




        return false;
    }
});


materialSearchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
    @Override
    public void onSearchViewShown() {

    }

    @Override
    public void onSearchViewClosed() {

    }

});



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView=navigationView.getHeaderView(0);

        TextView usernamenav=(TextView)headerView.findViewById(R.id.usernamenav);
        SharedPreferences sharedPreferences=getSharedPreferences("userInfo",MODE_PRIVATE);
        usernamenav.setText(sharedPreferences.getString("username",""));



    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

       MenuItem menuItem=menu.findItem(R.id.search_icon);
        materialSearchView.setMenuItem(menuItem);


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

         if(id==R.id.Shopping_Cart){
           Intent intent =new Intent(this,ShoppingCart.class);
           startActivity(intent);

           //   createandDisplayPdf("Ali");
        }


        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if(id==R.id.settings){
        Intent intent= new Intent(this,Settings.class);
        startActivity(intent);
}
        if(id==R.id.History){
            Intent intent=new Intent(this,History.class);
            startActivity(intent);
        }
        if(id==R.id.Logout){
            SharedPreferences sharedPreference=getSharedPreferences("userInfo",MODE_PRIVATE);
            sharedPreference.edit().clear().apply();
            Intent intent=new Intent(getApplicationContext(),Login.class);
            startActivity(intent);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    static class TableItem{
        String Product_Name;
        String Company_Name;
        String Price;
        String Amount;
        String Sale;
        String MedID;

        TableItem(String Product_Name,String Company_Name,String price,String Amount,String Sale,String MedID){
            this.Product_Name=Product_Name;
            this.Company_Name=Company_Name;
            this.Price=price;
            this.Amount=Amount;
            this.Sale=Sale;
            this.MedID=MedID;
        }

    }
    class TableListAdapter extends BaseAdapter {
        ArrayList<TableItem> tableItems;
        Context context;
        TableListAdapter(Context c,ArrayList<TableItem> tableItems1){
            context=c;
            tableItems=tableItems1;

       //     System.out.println("Asss");


        }
        @Override
        public int getCount() {

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
            final View row=inflater.inflate(R.layout.searchtabledata,parent,false);
            final TextView Name=(TextView)row.findViewById(R.id.product_Name);
            final TextView Company=(TextView)row.findViewById(R.id.Company_Name);
            final TextView Price=(TextView)row.findViewById(R.id.Price);
            final TextView Amount=(TextView) row.findViewById(R.id.Amount);
            final TextView Sale=(TextView) row.findViewById(R.id.Sale);
            Button addtocart=(Button) row.findViewById(R.id.addtocart);

            final TableItem tableItem=tableItems.get(position);


                Name.setText(tableItem.Product_Name);
                Company.setText(tableItem.Company_Name);
                Price.setText(tableItem.Price + " EGP");
                Amount.setText(tableItem.Amount);
                Sale.setText(tableItem.Sale +"%");



            addtocart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Intent i=new Intent(MainActivity.this,ShoppingCart.class);

                        /*i.putExtra("Product Name",Name.getText().toString());
                        i.putExtra("Company Name",Company.getText().toString());
                        i.putExtra("Price",Price.getText().toString());
                        i.putExtra("Amount",Amount.getText().toString());
                        i.putExtra("Sale",Sale.getText().toString());
                        Toast.makeText(MainActivity.this, "Added", Toast.LENGTH_SHORT).show();
*/

                        AlertDialog.Builder mbelder = new AlertDialog.Builder(MainActivity.this);
                        View view1 = getLayoutInflater().inflate(R.layout.addtocartdialog, null);
                        ImageView Close=(ImageView) view1.findViewById(R.id.Close);

                        final EditText Amount1=(EditText)view1.findViewById(R.id.Amount);
                        Button Update=(Button)view1.findViewById(R.id.addtocart);
                        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
                        final String id = sharedPreferences.getString("id", "");
                        mbelder.setView(view1);
                        final AlertDialog dialog = mbelder.create();

                        dialog.show();
                        Close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.cancel();
                            }
                        });
                        Update.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Double SalePrice=Integer.parseInt(Amount1.getText().toString())*(Integer.parseInt(tableItem.Price)-(Integer.parseInt(tableItem.Price)*(Double.parseDouble(tableItem.Sale)/100)));
                                ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                                boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
                                if(isConnected == true){
                                if(Amount1.getText().toString().isEmpty()){
                                    Toast.makeText(getApplicationContext(), "Enter Amount  ", Toast.LENGTH_SHORT).show();

                                }
                                else {
                                    addtoCart cart = new addtoCart(MainActivity.this);
                                    cart.execute(id, Amount1.getText().toString(), tableItem.MedID);
                                    dialog.cancel();
                                }
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Connect to Network  ", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                      }
                });

            return row;
        }

    }


    public class SearchClick extends AsyncTask<String,Void,ArrayList<TableItem>> {
        private WeakReference<MainActivity> activityWeakReference;
        SearchClick(MainActivity activity){
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
        protected ArrayList<TableItem> doInBackground(String... strings) {
            String LoginUrl="https://bestdiscounteg.com/Android/SearchClick.php";
            try {
                String Name=strings[0];

                URL url= new URL(LoginUrl);
                HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(outputStream,"utf-8"));
                String post_data=URLEncoder.encode("Name","utf-8")+"="+URLEncoder.encode(Name,"utf-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream=httpURLConnection.getInputStream();
                BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));

                ArrayList arrayList=new ArrayList<TableItem>();

                String line;

                while ((line=bufferedReader.readLine())!=null){

                    if(line.equals("NO Record")){
                        System.out.println(line);
                        break;
                    }
                    else {

                        arrayList.add(new TableItem(line, bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine(), bufferedReader.readLine(),bufferedReader.readLine()));
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
        protected void onPostExecute(ArrayList<TableItem> strings) {
            MainActivity activity=activityWeakReference.get();

            //System.out.print(strings.get(0));
            if(activity==null||activity.isFinishing()){
                return;
            }
            System.out.println(strings.size());
            strings.remove((strings.size()-1));
            ArrayList<TableItem> arrayList=strings;
            TableListAdapter tableListAdapter=new TableListAdapter(MainActivity.this,arrayList);
            listView.setAdapter(tableListAdapter);

            if(strings==null){
                Toast.makeText(MainActivity.this,"No Enternet Connection ",Toast.LENGTH_SHORT).show();

            }
            else {
                if (strings.isEmpty()) {
                    listView.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.VISIBLE);
                }

            }
            super.onPostExecute(strings);
        }
    }

}

package com.example.amotie.pharmacyproject;

import android.content.Context;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

import java.util.ArrayList;


public class ShoppingCart extends AppCompatActivity {
android.support.v7.widget.Toolbar toolbar;

    TextView sub_total;
ArrayList<shopingListItem> shopingListItems=new ArrayList<>();
    SwipeMenuListView listView;
    ShoppingListAdapter shoppingListAdapter;
    Context context;
    SwipeMenuCreator creator;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_shopping_cart);

        setSupportActionBar(toolbar);
shopingListItems.add(new shopingListItem("AAA","AAA",100,20));
shopingListItems.add(new shopingListItem("BBB","BBB",200,10));
shopingListItems.add(new shopingListItem("CCC","CCC",400,40));

      listView =(SwipeMenuListView) findViewById(R.id.ShoppingList);
        sub_total=(TextView)findViewById(R.id.Total_Price);
        shoppingListAdapter    =new ShoppingListAdapter(this,shopingListItems);
       context =this;

       listView.setAdapter(shoppingListAdapter);


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
                Toast toast=Toast.makeText(getApplicationContext(),shopingListItem.Product_Name,Toast.LENGTH_SHORT);
                toast.show();
                shoppingListAdapter.shoppingListItems.remove(shopingListItem);
shoppingListAdapter=new ShoppingListAdapter(context,shopingListItems);
listView.setAdapter(shoppingListAdapter);


                break;

        }
        return false;
    }
});

        }




public void Cheackout(View view){
    Toast toast=Toast.makeText(getApplicationContext(),"Done",Toast.LENGTH_SHORT);
    toast.show();
}
    public  String Total(ArrayList<shopingListItem>shopingListItems){
        int z=0;
        for(int i=0;i<shopingListItems.size();i++){
          shopingListItem listItem  =shopingListItems.get(i);

        z=z+(listItem.Amount*listItem.Price);
        }
         String f=Integer.toString(z);
        System.out.println(f);
        return f;
        }
        class shopingListItem{
        String Product_Name;
        String Company_Name;
        int Price;
        int Amount;

        shopingListItem(String Product_Name,String Company_Name,int price,int Amount){
            this.Product_Name=Product_Name;
            this.Company_Name=Company_Name;
            this.Price=price;
            this.Amount=Amount;
        }

        }
    class ShoppingListAdapter extends BaseAdapter{
ArrayList<shopingListItem> shoppingListItems;
Context context;
ShoppingListAdapter(Context c,ArrayList<shopingListItem> shopingListItems1){
    context=c;
    shoppingListItems=shopingListItems1;

    System.out.println("Asss");


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
            final EditText Amount=(EditText) row.findViewById(R.id.Amount);
            Button Ok=(Button) row.findViewById(R.id.Ok);
            final shopingListItem shopingListItem=shoppingListItems.get(position);
            Name.setText(shopingListItem.Product_Name);
            Company.setText(shopingListItem.Company_Name);
            Price.setText(Integer.toString(shopingListItem.Price)+" EGP");
            Amount.setText(Integer.toString(shopingListItem.Amount));
            String z=Total(shoppingListItems);
            sub_total.setText(z+" EGP");
Ok.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        shopingListItem.Amount=Integer.parseInt(Amount.getText().toString());
       String z= Total(shoppingListItems);
        sub_total.setText(z+" EGP");

    }
});

            return row;
        }

    }




}

package com.example.amotie.pharmacyproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
MaterialSearchView materialSearchView;
String[]list;
ImageSwitcher imageSwitcher,imageSwitcher2;
     int x[]={R.drawable.medc,R.drawable.medc,R.drawable.medc};
    int i=0;
    private Handler mHandler;
    private Runnable mUpdateResults;
    private Timer timerAnimate;
    private TimerTask timerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        Animation in= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.in);
Animation out=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.out);
     /*   imageSwitcher.setInAnimation(in);
        imageSwitcher.setOutAnimation(out);
        imageSwitcher2.setInAnimation(in);
        imageSwitcher2.setOutAnimation(out);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView=new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));

                return imageView;
            }
        });
        imageSwitcher2.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView=new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));

                return imageView;
            }
        });
        imageSwitcher.setImageResource(R.drawable.medc);
       imageSwitcher2.setImageResource(R.drawable.medc);*/
mHandler=new Handler();

mUpdateResults=new Runnable() {
    @Override
    public void run() {
        if(i!=x.length) {
            Animate(i);
            i++;
        }
        else{
            i=0;
            Animate(i);
            i++;

        }
    }
};

int delay=0;
int peroid=3000;

timerAnimate=new Timer();


timerTask=new TimerTask() {
    @Override
    public void run() {
        mHandler.post(mUpdateResults);
    }
};


timerAnimate.scheduleAtFixedRate(timerTask,delay,peroid);









        list=new String[]{"claritin","augmentin","panadol","flagyl","centre","cansanasn","cdaads","csadasd"};
materialSearchView=(MaterialSearchView)findViewById(R.id.mysearch);
materialSearchView.closeSearch();


materialSearchView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
materialSearchView.setTextColor(getResources().getColor(R.color.colorAccent));
materialSearchView.setHintTextColor(getResources().getColor(R.color.colorAccent));
materialSearchView.setSuggestions(list);
materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
    @Override
    public boolean onQueryTextSubmit(String query) {
        for(int i=0;i<list.length;i++){
            if(query.equals(list[i])){
                Intent intent=new Intent(getApplicationContext(),ItemContent.class);
                intent.putExtra("medcine",query);
                startActivity(intent);
            }

        }

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
    public  void Animate(int i){
  /*      if(i==0){
            imageSwitcher.setImageResource(x[i]);
        }
        else if(i==1){
            imageSwitcher2.setImageResource(x[i]);
        }
        else if(i==2){
            imageSwitcher.setImageResource(x[i]);
        }

*/
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
            Intent intent=new Intent(this,ShoppingCart.class);
            startActivity(intent);
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
        if(id==R.id.Track){
            Intent intent=new Intent(this,Track_Order.class);
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
}

package com.example.amotie.pharmacyproject;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.ViewSwitcher;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
MaterialSearchView materialSearchView;
String[]list;
ImageSwitcher imageSwitcher;
     int x[]={R.drawable.one,R.drawable.three,R.drawable.two};
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

        imageSwitcher=(ImageSwitcher)findViewById(R.id.imageswetcher);
        Animation in= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.in);
Animation out=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.out);
        imageSwitcher.setInAnimation(in);
        imageSwitcher.setOutAnimation(out);

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView=new ImageView(getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageView.setLayoutParams(new ImageSwitcher.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));

                return imageView;
            }
        });
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
int peroid=2000;
timerAnimate=new Timer();
timerTask=new TimerTask() {
    @Override
    public void run() {
        mHandler.post(mUpdateResults);
    }
};
timerAnimate.scheduleAtFixedRate(timerTask,delay,peroid);







        list=new String[]{"Asaasdasd","AFFF","A&A","Bawdsasda","CASASAS"};
materialSearchView=(MaterialSearchView)findViewById(R.id.mysearch);
materialSearchView.closeSearch();
materialSearchView.setSuggestions(list);

materialSearchView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
materialSearchView.setTextColor(getResources().getColor(R.color.colorAccent));
materialSearchView.setHintTextColor(getResources().getColor(R.color.colorAccent));

materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
});


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public  void Animate(int i){
        imageSwitcher.setImageResource(x[i]);
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
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

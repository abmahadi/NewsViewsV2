package com.example.newsviews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import com.example.newsviews.Adapter.NewsAdapter;
import com.example.newsviews.Model.HeadLine;
import com.example.newsviews.Utils.NetworkService;
import com.google.android.material.navigation.NavigationView;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private SwipeRefreshLayout swipeRefreshLayoutMain;
    private RecyclerView newsRV;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(toolbar);

        init();
        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        swipeRefreshLayoutMain.setOnRefreshListener(this);
        getHeadLines();
    }

    private void getHeadLines() {

        swipeRefreshLayoutMain.setRefreshing(false);

        NetworkService networkService = new NetworkService();

        networkService.GetTopHeadlines("us","d03ebaab03fa45a492700bc9cd046217")
                .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<HeadLine>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(HeadLine headLine) {

                Log.v("", "");
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(MainActivity.this);
                }
                newsAdapter = new NewsAdapter(MainActivity.this,builder);

                newsRV.setAdapter(newsAdapter);

                newsRV.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                newsAdapter.setItem(headLine);




            }

            @Override
            public void onError(Throwable e) {

                Toast.makeText(MainActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

                swipeRefreshLayoutMain.setRefreshing(false);

            }

            @Override
            public void onComplete() {

            }
        });






    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
        super.onBackPressed();
        }
    }

    private void init() {
        drawerLayout =findViewById(R.id.drawe_layout_main_activity);
        navigationView = findViewById(R.id.nav_view);
        toolbar =findViewById(R.id.toolbar);
        swipeRefreshLayoutMain = findViewById(R.id.swaipe_layout);
        newsRV = findViewById(R.id.mainActivity_RV);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId())
        {
            case R.id.nav_home :

                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();

                break;
            case R.id.nav_about :

                startActivity(new Intent(getApplicationContext(),AboutActivity.class));

                break;

            case R.id.nav_exit :

                //Exit Dialogue box

                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Exit App")
                        .setMessage("Are you sure you want to close this Application?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();

                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayoutMain.setRefreshing(true);
        getHeadLines();

    }
}

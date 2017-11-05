package com.example.thevillain.mathforbaby;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thevillain.mathforbaby.GetStarted.RegisterActivity;
import com.example.thevillain.mathforbaby.mainfunction.LearningActivity;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {

    TextView txtName;
    ImageView imgLearn, imgExam, imgAchie, imgShare, imgAvt;
    String avatar, fullname, username,highscore, account_type, KEYCODE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        init();

        imgLearn.setOnClickListener(this);

        getData();
    }

    private void getData() {
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        KEYCODE = b.getString("KEYCODE");
        if(KEYCODE.equals("normal")) {
            Picasso.with(getApplicationContext()).load(RegisterActivity.normalUserAvt).into(imgAvt);
        } else{
            avatar = b.getString("avtS");
            fullname = b.getString("fnameS");
            username = b.getString("unameS");
            txtName.setText(fullname);
            Picasso.with(getApplicationContext()).load(avatar).into(imgAvt);
        }
    }

    private void init() {
        imgAchie = (ImageView) findViewById(R.id.imgAchie);
        imgLearn = (ImageView) findViewById(R.id.imgLearn);
        imgExam = (ImageView) findViewById(R.id.imgExam);
        imgShare = (ImageView) findViewById(R.id.imgShare);
        imgAvt = (ImageView) findViewById(R.id.imgAvt);
        txtName = (TextView) findViewById(R.id.txtName);
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imgLearn:
                Intent intent = new Intent(MainActivity.this, LearningActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Hi, kids!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.imgExam:
                break;
            case R.id.imgAchie:
                break;
            case R.id.imgShare:
                break;
        }
    }
}

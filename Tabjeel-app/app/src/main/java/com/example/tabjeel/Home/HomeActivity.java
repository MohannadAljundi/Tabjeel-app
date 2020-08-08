package com.example.tabjeel.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tabjeel.AboutApp.AboutPage;
import com.example.tabjeel.Fragments.AboutUsFragment;
import com.example.tabjeel.Fragments.CalendarFragment;
import com.example.tabjeel.Fragments.HomeFragment;
import com.example.tabjeel.Fragments.ProfileFragment;
import com.example.tabjeel.R;
import com.example.tabjeel.RendererRecyclerView.pages.simple.LoadMoreFragment;
import com.example.tabjeel.RendererRecyclerView.pages.simple.ViewBinderFragment;
import com.example.tabjeel.RendererRecyclerView.pages.simple.ViewStateFragment;
import com.example.tabjeel.Setting.SettingsActivity;
import com.example.tabjeel.Utils.UserSessionManager;
import com.example.tabjeel.anim.LogOutAnimActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {


    UserSessionManager session ;
    String name , email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        session = new UserSessionManager(getApplicationContext());
        session.checkLogin();
        HashMap<String,String > UserUtils = session.getUserDetails();
        name  = UserUtils.get(UserSessionManager.KEY_NAME);
        email = UserUtils.get(UserSessionManager.KEY_EMAIL);

        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );


        // load the store fragment by default
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.action_home);


        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                new HomeFragment()).commit();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction.add(R.id.frame_container, homeFragment);
        fragmentTransaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.action_home:
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.action_profile:
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.action_contact_us:
//                    fragment = new ContactUsFragment();
//                    loadFragment(fragment);
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + "+962778985815"));
                    startActivity(intent);
                    return true;
                case R.id.action_about_us:
                    fragment = new AboutUsFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.action_calendar:
                    fragment = new CalendarFragment();
                    loadFragment(fragment);
                    return true;

            }

            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,fragment)
                    .commit();

            return false;
        }
    };

    public void onExit() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.menu_home, menu );
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_setting:{
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            }

            case R.id.action_about_us_2:{
                Intent intent = new Intent(this, AboutPage.class);
                startActivity(intent);
                return true;
            }

            case R.id.action_exit:{
                onExit();
                return true;
            }


            case R.id.action_home_2:{
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                return true;
            }

            case R.id.action_logout:{
                Intent go = new Intent(getApplicationContext(), LogOutAnimActivity.class);
                startActivity(go);
                return true;
            }

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
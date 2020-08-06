package com.example.tabjeel.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.tabjeel.Fragments.AboutUsFragment;
import com.example.tabjeel.Fragments.CalendarFragment;
import com.example.tabjeel.Fragments.HomeFragment;
import com.example.tabjeel.Fragments.ProfileFragment;
import com.example.tabjeel.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

//    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        toolbar = getSupportActionBar();

        // load the store fragment by default
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,
                new HomeFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {
                case R.id.action_home:
//                    toolbar.setTitle("الرئيسبة");
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.action_profile:
//                    toolbar.setTitle("الملف الشخصي");
                    fragment = new ProfileFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.action_contact_us:
//                    toolbar.setTitle("اتصل بنا");
//                    fragment = new ContactUsFragment();
//                    loadFragment(fragment);
                    return true;
                case R.id.action_about_us:
//                    toolbar.setTitle("من نحن");
                    fragment = new AboutUsFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.action_calendar:
//                    toolbar.setTitle("التقويم");
                    fragment = new CalendarFragment();
                    loadFragment(fragment);
                    return true;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,fragment)
                    .commit();

            return false;
        }
    };


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
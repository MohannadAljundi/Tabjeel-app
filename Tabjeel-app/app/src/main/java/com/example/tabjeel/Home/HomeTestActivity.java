package com.example.tabjeel.Home;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.tabjeel.R;
import com.example.tabjeel.Setting.ContactActivity;
import com.example.tabjeel.Setting.HelpActivity;
import com.example.tabjeel.Setting.SettingsActivity;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.ncapdevi.fragnav.FragNavController;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabClickListener;

import java.util.ArrayList;
import java.util.List;

public class HomeTestActivity extends AppCompatActivity {

    private BottomBar mBottomBar;
    private FragNavController fragNavController;

    //indices to fragments
    private final int TAB_FIRST = FragNavController.TAB1;
    private final int TAB_SECOND = FragNavController.TAB2;
    private final int TAB_THIRD = FragNavController.TAB3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_home);


        //FragNav
        //list of fragments
        List<Fragment> fragments = new ArrayList<>(3);

        //add fragments to list
//        fragments.add(FirstFragment.newInstance(0));
//        fragments.add(SecondFragment.newInstance(0));
//        fragments.add(ThirdFragment.newInstance(0));

        //link fragments to container
        fragNavController = new FragNavController(getSupportFragmentManager(),R.id.container,fragments);
        //End of FragNav

        //BottomBar menu
        mBottomBar = BottomBar.attach(this, savedInstanceState);
        mBottomBar.setItems(R.menu.home_bottom_bar_layout);
        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                //switch between tabs
                switch (menuItemId) {
                    case R.id.bottomBarItemOne:
                        fragNavController.switchTab(TAB_FIRST);
                        break;
                    case R.id.bottomBarItemSecond:
                        fragNavController.switchTab(TAB_SECOND);
                        break;
                    case R.id.bottomBarItemThird:
                        fragNavController.switchTab(TAB_THIRD);
                        break;
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                if (menuItemId == R.id.bottomBarItemOne) {
                    fragNavController.clearStack();
                }
            }
        });
        //End of BottomBar menu

        //Navigation drawer
        new DrawerBuilder().withActivity(this).build();

        //primary items
        PrimaryDrawerItem home = new PrimaryDrawerItem()
                .withIdentifier(1)
                .withName(R.string.drawer_item_home)
                .withIcon(R.drawable.ic_baseline_home_24);
        PrimaryDrawerItem primary_item1 = new PrimaryDrawerItem()
                .withIdentifier(2)
                .withName(R.string.drawer_item_option1)
                .withIcon(R.drawable.ic_baseline_local_offer_24);
        PrimaryDrawerItem primary_item2 = new PrimaryDrawerItem()
                .withIdentifier(3)
                .withName(R.string.drawer_item_option2)
                .withIcon(R.drawable.ic_baseline_person_pin_24);
        //secondary items
        SecondaryDrawerItem secondary_item1 = (SecondaryDrawerItem) new SecondaryDrawerItem()
                .withIdentifier(11)
                .withName(R.string.drawer_item_option1)
                .withIcon(R.drawable.ic_baseline_home_24);
        SecondaryDrawerItem secondary_item2 = (SecondaryDrawerItem) new SecondaryDrawerItem()
                .withIdentifier(12)
                .withName(R.string.drawer_item_option2)
                .withIcon(R.drawable.ic_baseline_local_offer_24);
        SecondaryDrawerItem secondary_item3 = (SecondaryDrawerItem) new SecondaryDrawerItem()
                .withIdentifier(13)
                .withName(R.string.drawer_item_option2)
                .withIcon(R.drawable.ic_baseline_person_pin_24);
        //settings, help, contact items
        SecondaryDrawerItem settings = (SecondaryDrawerItem) new SecondaryDrawerItem()
                .withIdentifier(97)
                .withName(R.string.drawer_item_settings)
                .withIcon(R.drawable.ic_baseline_settings_24);
        SecondaryDrawerItem help = (SecondaryDrawerItem) new SecondaryDrawerItem()
                .withIdentifier(98)
                .withName(R.string.drawer_item_help)
                .withIcon(R.drawable.ic_baseline_live_help_24);
        SecondaryDrawerItem contact = (SecondaryDrawerItem) new SecondaryDrawerItem()
                .withIdentifier(99)
                .withName(R.string.drawer_item_contact)
                .withIcon(R.drawable.ic_baseline_contact_mail_24);

        //Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);

        new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggleAnimated(true)
                .withTranslucentStatusBar(false)
                .withFullscreen(true)
                .withSavedInstance(savedInstanceState)
                .addDrawerItems(
                        home,
                        primary_item1,
                        primary_item2,
                        new SectionDrawerItem().withName("Categories"),
                        secondary_item1,
                        secondary_item2,
                        secondary_item2,
                        new DividerDrawerItem(),
                        settings,
                        help,
                        contact

                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null) {
                            Intent intent = null;
                            if (drawerItem.getIdentifier() == 1) {
                                intent = new Intent(HomeTestActivity.this, HomeTestActivity.class);
                            } else if (drawerItem.getIdentifier() == 2) {
                                //intent = new Intent(MainActivity.this, Class.class);
                            } else if (drawerItem.getIdentifier() == 3) {
                                //intent = new Intent(MainActivity.this, Class.class);
                            } else if (drawerItem.getIdentifier() == 11) {
                                //intent = new Intent(MainActivity.this, Class.class);
                            } else if (drawerItem.getIdentifier() == 12) {
                                //intent = new Intent(MainActivity.this, Class.class);
                            } else if (drawerItem.getIdentifier() == 13) {
                                //intent = new Intent(MainActivity.this, Class.class);
                            } else if (drawerItem.getIdentifier() == 97) {
                                intent = new Intent(HomeTestActivity.this, SettingsActivity.class);
                            } else if (drawerItem.getIdentifier() == 98) {
                                intent = new Intent(HomeTestActivity.this, HelpActivity.class);
                            } else if (drawerItem.getIdentifier() == 99) {
                                intent = new Intent(HomeTestActivity.this, ContactActivity.class);
                            }
                            if (intent != null) {
                                HomeTestActivity.this.startActivity(intent);
                            }
                        }

                        return false;
                    }
                })
                .build();
        //End of Navigation drawer
    }

    @Override
    public void onBackPressed() {
        if (fragNavController.getCurrentStack().size() > 1) {
            fragNavController.pop();
        } else {
            super.onBackPressed();
        }
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Necessary to restore the BottomBar's state, otherwise we would
        // lose the current tab on orientation change.
        mBottomBar.onSaveInstanceState(outState);
    }
}
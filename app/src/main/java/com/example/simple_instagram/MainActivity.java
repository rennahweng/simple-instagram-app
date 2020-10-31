package com.example.simple_instagram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.simple_instagram.fragments.ComposeFragment;
import com.example.simple_instagram.fragments.PostsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    /*
     * Set up fragment manager for all fragmant activties
     */
    final FragmentManager fragmentManager = getSupportFragmentManager();
    // define your fragments here
    final Fragment composeFragment = new ComposeFragment();
    final Fragment postsFragment = new PostsFragment();

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
         * Add instagram text logo to ActionBar
         */
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.instagram_text_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment;
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        // Toast.makeText(MainActivity.this, "Home!", Toast.LENGTH_SHORT).show();
                        fragment = new PostsFragment();
                        break;
                    case R.id.action_compose:
                        // Toast.makeText(MainActivity.this, "Compose!", Toast.LENGTH_SHORT).show();
                        fragment = new ComposeFragment();
                        break;
                    case R.id.action_profile:
                    default:
                        // TODO: change fragment
                        // Toast.makeText(MainActivity.this, "Profile!", Toast.LENGTH_SHORT).show();
                        fragment = new ComposeFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }
}
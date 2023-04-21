package com.example.daocomics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.daocomics.adapter.ComicAdapter;
import com.example.daocomics.adapter.ScreenSlidePageAdapter;
import com.example.daocomics.databinding.ActivityMainBinding;
import com.example.daocomics.databinding.ActivityRegisterBinding;
import com.example.daocomics.model.Comic;
import com.example.daocomics.model.User;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupFragment();



    }


    void setupFragment(){
        ScreenSlidePageAdapter fragAdapter = new ScreenSlidePageAdapter(this);
        binding.fragVPmain.setAdapter(fragAdapter);

        binding.navMenu.setOnItemSelectedListener(getBtnListener());
        binding.fragVPmain.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position)
                {
                    case 0:
                        binding.navMenu.getMenu().findItem(R.id.mnHome).setChecked(true);
                        break;
                    case 1:
                        binding.navMenu.getMenu().findItem(R.id.mnExplore).setChecked(true);
                        break;
                    case 2:
                        binding.navMenu.getMenu().findItem(R.id.mnFavourite).setChecked(true);
                        break;
                }
            }
        });
    }

    @NonNull
    private NavigationBarView.OnItemSelectedListener getBtnListener() {
        return new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.mnHome:
                        binding.fragVPmain.setCurrentItem(0);
                        return true;
                    case R.id.mnExplore:
                        binding.fragVPmain.setCurrentItem(1);
                        return true;
                    case R.id.mnFavourite:
                        binding.fragVPmain.setCurrentItem(2);
                        return true;
                }
                return false;
            }
        };
    }


}
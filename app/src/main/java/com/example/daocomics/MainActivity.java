package com.example.daocomics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.daocomics.adapter.ComicAdapter;
import com.example.daocomics.adapter.ScreenSlidePageAdapter;
import com.example.daocomics.databinding.ActivityMainBinding;
import com.example.daocomics.databinding.ActivityRegisterBinding;
import com.example.daocomics.model.Comic;
import com.example.daocomics.model.User;
import com.example.daocomics.ui.login_register.LoginActivity;
import com.example.daocomics.ui.setting.SettingActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    SharedPreferences.Editor editor;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;
    User user;
    SharedPreferences shget;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        shget = this.getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
        editor = shget.edit();
        drawerLayout= findViewById(R.id.drawer_layout);
        firestore = FirebaseFirestore.getInstance();

        setupMenu();
        setupFragment();
        setupSetting();
        //get user firebase login
        auth = FirebaseAuth.getInstance();
        firebaseUser = auth.getCurrentUser();
        getUserProgfile();

    }
    private void getUserProgfile() {

        TextView t = binding.drawerSetting.getHeaderView(0).findViewById(R.id.tvUsName_setting);
        if(!shget.getString(Utils.ACCOUNT_RMB_USER_NAME,"").isEmpty())
            t.setText(shget.getString(Utils.ACCOUNT_RMB_USER_NAME,""));
    }
    public User getUser(){
        return user;
    }
    private void setupSetting() {
        // View profile
        Button b = binding.drawerSetting.getHeaderView(0).findViewById(R.id.btn_ViewProfile);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SettingActivity.class);
                int x = 1;
                i.putExtra("fragment",x);
                startActivity(i);
            }
        });
        //Sign out
        binding.btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               auth.signOut();
               editor.remove(Utils.ACCOUNT_RMB_USER_NAME);
               editor.remove(Utils.ACCOUNT_RMB_USER_PHONENB);
               editor.commit();
               startActivity(new Intent(MainActivity.this,LoginActivity.class));

            }
        });
        // menu item
        binding.drawerSetting.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent i = new Intent(MainActivity.this, SettingActivity.class);
                int x;
                switch (item.getItemId()){
                    case R.id.changePass:
                        x = 2;
                        i.putExtra("fragment",x);
                        startActivity(i);
                        return true;
                    case R.id.feedback:
                        x = 3;
                        i.putExtra("fragment",x);
                        startActivity(i);
                        return true;
                    case R.id.aboutUs:
                        x = 4;
                        i.putExtra("fragment",x);
                        startActivity(i);
                        return true;
                }
                return false;
            }
        });
    }

    private void setupMenu() {
        binding.btnSettingDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (drawerLayout.isDrawerOpen(GravityCompat.END)){
                    drawerLayout.closeDrawer(GravityCompat.END);
                }
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });
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
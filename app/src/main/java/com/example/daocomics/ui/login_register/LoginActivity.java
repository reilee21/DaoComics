package com.example.daocomics.ui.login_register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.bumptech.glide.util.Util;
import com.example.daocomics.MainActivity;
import com.example.daocomics.R;
import com.example.daocomics.Utils;
import com.example.daocomics.adapter.ScreenSlidePageAdapter;
import com.example.daocomics.databinding.ActivityLoginBinding;
import com.example.daocomics.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;

import java.io.Serializable;


public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    SharedPreferences.Editor editor;
    private  final Gson gson = new Gson();
    SharedPreferences sharedPreferences;

    BottomNavigationView btnavView;

    ViewPager2 viewPager2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPreferences = getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        savedLogin();
        clicked();

    }

    private void savedLogin() {

        String usnSaved = sharedPreferences.getString(Utils.ACCOUNT_RMB_USN, "");
        String uspSaved = sharedPreferences.getString(Utils.ACCOUNT_RMB_PASS, "");
        if(usnSaved.isEmpty())
            return;
        binding.edLoginUsername.setText(usnSaved);
        binding.edLoginPassword.setText(uspSaved);
    }

    public void clicked() {

        binding.btnLogin.setOnClickListener(v->login());
        binding.btnRegis.setOnClickListener(v->register());
        binding.cbGhiNhoDN.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(binding.cbGhiNhoDN.isChecked())
                {
                    String savedLUSn = binding.edLoginUsername.getText().toString().trim();
                    String savedLUSp = binding.edLoginPassword.getText().toString().trim();
                    editor.putString(Utils.ACCOUNT_RMB_USN, savedLUSn);
                    editor.putString(Utils.ACCOUNT_RMB_PASS, savedLUSp);
                    editor.commit();
                }
                else{
                    editor.remove(Utils.ACCOUNT_RMB_USN);
                    editor.remove(Utils.ACCOUNT_RMB_PASS);
                    editor.commit();
                }


            }
        });
    }


    private void register() {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    private void login() {
        String userPref = sharedPreferences.getString(Utils.KEY_USER,null);
        User user = gson.fromJson(userPref,User.class);
        if (user == null){
            return;
        }

        boolean isValid = binding.edLoginUsername.getText().toString().trim().equals(user.getUsName())
                && binding.edLoginPassword.getText().toString().trim().equals(user.getPassW());
        if (isValid) {
            Intent intent = new Intent(this, MainActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(Utils.KEY_USER_PR0FILE,user);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
        }
    }


}
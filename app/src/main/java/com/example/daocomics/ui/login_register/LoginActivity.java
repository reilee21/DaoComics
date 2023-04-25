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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.regex.Pattern;


public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;

    SharedPreferences.Editor editor;
    private  final Gson gson = new Gson();
    SharedPreferences sharedPreferences;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        sharedPreferences = getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        savedLogin();
        clicked();

    }

    private void savedLogin() {

        String usnSaved = sharedPreferences.getString(Utils.ACCOUNT_RMB_US_LOGIN, "");
        String uspSaved = sharedPreferences.getString(Utils.ACCOUNT_RMB_PASS, "");
        if(usnSaved.isEmpty())
            return;
        binding.edLoginUsername.getEditText().setText(usnSaved);
        binding.edLoginPassword.getEditText().setText(uspSaved);
    }

    public void clicked() {

        binding.btnLogin.setOnClickListener(v->login());
        binding.btnRegis.setOnClickListener(v->register());
        binding.cbGhiNhoDN.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(binding.cbGhiNhoDN.isChecked())
                {
                    String savedLUSn = binding.edLoginUsername.getEditText().getText().toString().trim();
                    String savedLUSp = binding.edLoginPassword.getEditText().getText().toString().trim();
                    editor.putString(Utils.ACCOUNT_RMB_US_LOGIN, savedLUSn);
                    editor.putString(Utils.ACCOUNT_RMB_PASS, savedLUSp);
                    editor.commit();
                }
                else{
                    editor.remove(Utils.ACCOUNT_RMB_US_LOGIN);
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
        String usname = binding.edLoginUsername.getEditText().getText().toString().trim();
        String pass = binding.edLoginPassword.getEditText().getText().toString().trim();

        if(checkEmailType(usname)){
            firebaseAuth.signInWithEmailAndPassword(usname,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    getUsdataandNext(usname);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(LoginActivity.this,"Sai tài khoản hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                }
            });
        }

        firestore.collection("Users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    String s = documentSnapshot.getString("usName");
                    if (usname.equals(s)){
                        String e = documentSnapshot.getString("email");
                        firebaseAuth.signInWithEmailAndPassword(e,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                getUsdataandNext(e);

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this,"Sai tài khoản hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }
            }
        });
    }
    private void getUsdataandNext(String mail){
        firestore.collection("Users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                for (QueryDocumentSnapshot documentSnapshot : task.getResult())
                {
                    if(documentSnapshot.getString("email").equals(mail)) {
                        String name = documentSnapshot.getString("name"); String passW =  documentSnapshot.getString("passW"); String phoneNb =  documentSnapshot.getString("phoneNb"); String usName =  documentSnapshot.getString("usName"); String avatar =  documentSnapshot.getString("avatar");
                        editor.putString(Utils.ACCOUNT_RMB_USER_NAME, name);
                        editor.putString(Utils.ACCOUNT_RMB_USER_PHONENB, phoneNb);
                        editor.commit();
                        Intent i = new Intent(LoginActivity.this,MainActivity.class);
                        LoginActivity.this.finish();
                        startActivity(i);


                    }
                }
            }
        });

    }

    private boolean checkEmailType(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
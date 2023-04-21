package com.example.daocomics.ui.login_register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.example.daocomics.R;
import com.example.daocomics.Utils;
import com.example.daocomics.databinding.ActivityLoginBinding;
import com.example.daocomics.databinding.ActivityRegisterBinding;
import com.example.daocomics.model.User;
import com.google.gson.Gson;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;

    SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private final Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferences = getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        event();
        
    }

    private void event() {
        binding.btnCreateUS.setOnClickListener(v->regis());
        binding.btnBack.setOnClickListener(v->finish());
    }


    private void regis() {
        String name = binding.edRegisterName.getText().toString().trim();
        String usname = binding.edRegisterUserName.getText().toString().trim();
        String passw = binding.edRegisterUserPassWord.getText().toString().trim();
        String repassw = binding.edRegisterRePassWord.getText().toString().trim();
        String phone = binding.edRegisterUserPhoneNb.getText().toString().trim();
        String mail = binding.edRegisterEmail.getText().toString().trim();

        boolean isValid = checkUserName(usname) && checkPassword(passw,repassw);
        if(!isValid)
            return;
        User user = new User();
        user.setName(name);
        user.setUsName(usname);
        user.setPassW(passw);
        user.setPhoneNb(phone);
        user.setEmail(mail);

        String userStr = gson.toJson(user);
        editor.putString(Utils.KEY_USER, userStr);
        editor.commit();
        Toast.makeText(RegisterActivity.this,"Đăng ký tài khoản thành công",Toast.LENGTH_LONG).show();
        finish();
    }

    private boolean checkPassword(String passw, String repassw) {
        if(passw.isEmpty()){
            binding.edRegisterUserPassWord.setError("Vui lòng nhập mật khẩu");
            return false;
        }
        if (passw.length() <= 5){
            binding.edRegisterUserPassWord.setError("Mật khẩu phải lớn hơn 5 ký tự");
            return false;
        }
        if(!passw.equals(repassw)){
            binding.edRegisterRePassWord.setError("Xác nhận mật khẩu không khớp");
            return false;
        }
        return true;
    }

    private boolean checkUserName(String usname) {
        if (usname.isEmpty()){
            binding.edRegisterName.setError("Vui lòng nhập tên đăng nhập");
            return  false;
        }
        if (usname.length() <= 5){
            binding.edRegisterName.setError("Tên đăng nhập phỉa ít nhất 6 ký tự");
            return false;
        }
        return true;
    }
}
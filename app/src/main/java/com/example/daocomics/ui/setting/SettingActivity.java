package com.example.daocomics.ui.setting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.daocomics.R;

public class SettingActivity extends AppCompatActivity {
    ImageButton back;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        int x = getIntent().getIntExtra("fragment",0);

        chooseFragment(x);
        back = findViewById(R.id.btnBack_setting);
        back.setOnClickListener(v->finish());
    }


    private void chooseFragment(int x)
    {
        switch(x) {
            case 1:
                loadFragment(new ViewProfileFragment());
                return ;
            case 2:
                loadFragment(new ChangePasswordFragment());
                return ;
            case 3:
                loadFragment(new FeedbackFragment());
                return ;
            case 4:
                loadFragment(new AboutUsFragment());
                return ;
        }
    }
    void loadFragment(Fragment fmNew)
    {
        Log.d("dqwdqwdqwdwqd", "loadFragment: ");
        FragmentTransaction fmTran = getSupportFragmentManager().beginTransaction();
        fmTran.replace(R.id.setting_frag,fmNew);
        fmTran.addToBackStack(null);
        fmTran.commit();

    }
}
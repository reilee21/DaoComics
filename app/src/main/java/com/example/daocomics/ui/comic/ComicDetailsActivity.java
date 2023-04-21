package com.example.daocomics.ui.comic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.daocomics.R;
import com.example.daocomics.adapter.ScreenSP2;
import com.example.daocomics.adapter.ScreenSlidePageAdapter;
import com.example.daocomics.databinding.ActivityComicDetailsBinding;
import com.example.daocomics.databinding.ActivityMainBinding;
import com.example.daocomics.model.Chapter;
import com.example.daocomics.model.Comic;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Date;

public class ComicDetailsActivity extends AppCompatActivity {
    ActivityComicDetailsBinding binding;
    Comic comic;
    ArrayList<Chapter> listChapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_details);
        binding = ActivityComicDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //get data
        getComicOnclicked();
        //set background button color on clicked
        setBGCBtn();
        //event click
        onClick();
       
       
        createSampleListChap();
        setupFragment();
    }

    private void setBGCBtn() {
        binding.btnBack.setBackgroundColor(getResources().getColor(R.color.white));
        binding.btnFav.setBackgroundColor(getResources().getColor(R.color.white));
    }

    private void onClick() {
        binding.btnFav.setOnClickListener(v-> addtoFAV());
        binding.btnBack.setOnClickListener(v->finish());
    }

    private void addtoFAV() {
    }


    private void createSampleListChap() {
        listChapter = new ArrayList<>();
        for (int i = 0 ; i < 20 ;  i++) {
            String nameChap = "Chapter " + i;
            String date = 10 + i + "- 03 - 2023";
            listChapter.add(new Chapter(nameChap,date));
        }
    }

    private void getComicOnclicked() {
        Bundle b = getIntent().getExtras();
        comic = (Comic) b.getSerializable("comic");
        binding.tvComicName.setText(comic.getName());
        Glide.with(this).load(comic.getImageThumb()).into(binding.imgComic);
    }
    public String getComicDesciption() {
        return comic.getDesciption();
    }
    public ArrayList<Chapter> getListChapter() {
        return listChapter;
    }
    public Context getParentContext(){
        return this;
    }


    void setupFragment(){
        ScreenSP2 fragAdapter = new ScreenSP2(this);
        binding.fragDesChap.setAdapter(fragAdapter);

        binding.navMenuTab.setOnItemSelectedListener(getBtnListener());
        binding.fragDesChap.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position)
                {
                    case 0:
                        binding.navMenuTab.getMenu().findItem(R.id.tabMota).setChecked(true);
                        break;
                    case 1:
                        binding.navMenuTab.getMenu().findItem(R.id.tabChapList).setChecked(true);
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
                    case R.id.tabMota:
                        binding.fragDesChap.setCurrentItem(0);
                        return true;
                    case R.id.tabChapList:
                        binding.fragDesChap.setCurrentItem(1);
                        return true;
                }
                return false;
            }
        };
    }
}
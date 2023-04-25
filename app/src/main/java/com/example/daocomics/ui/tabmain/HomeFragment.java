package com.example.daocomics.ui.tabmain;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.daocomics.MainActivity;
import com.example.daocomics.R;
import com.example.daocomics.Utils;
import com.example.daocomics.adapter.ComicAdapter;
import com.example.daocomics.model.Comic;
import com.example.daocomics.model.User;
import com.example.daocomics.ui.comic.ComicDetailsActivity;
import com.google.gson.Gson;

import java.util.ArrayList;

public class HomeFragment extends Fragment{
    RecyclerView rcvHome;
    ComicAdapter comicAdapter;
    ArrayList<Comic> comicsList;
    TextView tvHello;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        rcvHome = v.findViewById(R.id.rcvHome);
        tvHello = v.findViewById(R.id.tvHello);
        getUser();
        //add sample comic list
        addDataSample();
        comicAdapter = new ComicAdapter(comicsList);
        GridLayoutManager grv = new GridLayoutManager(getActivity(),3);
        rcvHome.setAdapter(comicAdapter);
        rcvHome.setLayoutManager(grv);

        // Inflate the layout for this fragment
        return v;
    }

    private void getUser() {
        SharedPreferences shget = getActivity().getSharedPreferences(Utils.SHARE_PREFERENCES_APP, Context.MODE_PRIVATE);
        if(!shget.getString(Utils.ACCOUNT_RMB_USER_NAME,"").isEmpty())
            tvHello.setText("Xin chào " + shget.getString(Utils.ACCOUNT_RMB_USER_NAME,""));
    }


    private void addDataSample() {
        comicsList = new ArrayList<>();
        comicsList.add(new Comic("Solo leveling","Solo cung Jinwoo","https://www.asurascans.com/wp-content/uploads/2021/03/soloLevelingCover02.png","Action"));
        comicsList.add(new Comic("The Dark Mage’s Return to Enlistment","Solo cung Jinwoo","https://www.asurascans.com/wp-content/uploads/2023/03/resource_1Photoauto_scaleLevel3width_1000.jpg","Action"));
        comicsList.add(new Comic("Demonic Evolution","Solo cung Jinwoo","https://www.asurascans.com/wp-content/uploads/2023/02/tIEELUSJN.webp-t.w640-vert-copyCUnetauto_scaleLevel3width-1000.jpg","Action"));
        comicsList.add(new Comic("Existence","Solo cung Jinwoo","https://www.asurascans.com/wp-content/uploads/2022/08/existenceCover01.png","Action"));
        comicsList.add(new Comic("Never Die Extra","Solo cung Jinwoo","https://www.asurascans.com/wp-content/uploads/2022/09/dieExtraCover03.png","Action"));
        comicsList.add(new Comic("Ending Maker","Solo cung Jinwoo","https://www.asurascans.com/wp-content/uploads/2022/05/ending_maker_01.jpg","Action"));
        comicsList.add(new Comic("My Healing Game","Solo cung Jinwoo","https://www.asurascans.com/wp-content/uploads/2022/08/MyHealingGame04.png","Action"));

    }

}
package com.example.daocomics.ui.tabmain;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daocomics.R;
import com.example.daocomics.adapter.ComicAdapter;
import com.example.daocomics.adapter.FavComicAdapter;
import com.example.daocomics.model.Comic;

import java.util.ArrayList;


public class FavouriteFragment extends Fragment {
    RecyclerView rcvFavList;
    FavComicAdapter comicAdapter;
    ArrayList<Comic> comicsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favourite, container, false);
        rcvFavList = v.findViewById(R.id.rcvFavList);

        //add sample comic list
        addDataSample();

        comicAdapter = new FavComicAdapter(comicsList);
        rcvFavList.setAdapter(comicAdapter);
        LinearLayoutManager ln = new LinearLayoutManager(getActivity());
        rcvFavList.setLayoutManager(ln);
        // Inflate the layout for this fragment
        return v;
    }

    private void addDataSample() {
        comicsList = new ArrayList<>();
        comicsList.add(new Comic("Solo leveling","Solo cung Jinwoo","https://www.asurascans.com/wp-content/uploads/2021/03/soloLevelingCover02.png","Action"));
        comicsList.add(new Comic("Solo leveling","Solo cung Jinwoo","https://www.asurascans.com/wp-content/uploads/2021/03/soloLevelingCover02.png","Action"));
        comicsList.add(new Comic("Solo leveling","Solo cung Jinwoo","https://www.asurascans.com/wp-content/uploads/2021/03/soloLevelingCover02.png","Action"));
        comicsList.add(new Comic("Solo leveling","Solo cung Jinwoo","https://www.asurascans.com/wp-content/uploads/2021/03/soloLevelingCover02.png","Action"));
        comicsList.add(new Comic("Solo leveling","Solo cung Jinwoo","https://www.asurascans.com/wp-content/uploads/2021/03/soloLevelingCover02.png","Action"));
        comicsList.add(new Comic("Solo leveling","Solo cung Jinwoo","https://www.asurascans.com/wp-content/uploads/2021/03/soloLevelingCover02.png","Action"));
        comicsList.add(new Comic("Solo leveling","Solo cung Jinwoo","https://www.asurascans.com/wp-content/uploads/2021/03/soloLevelingCover02.png","Action"));

    }
}
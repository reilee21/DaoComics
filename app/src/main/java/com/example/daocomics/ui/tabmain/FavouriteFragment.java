package com.example.daocomics.ui.tabmain;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.daocomics.MainActivity;
import com.example.daocomics.R;
import com.example.daocomics.adapter.FavComicAdapter;
import com.example.daocomics.model.Comic;

import java.util.ArrayList;


public class FavouriteFragment extends Fragment {
    RecyclerView rcvFavList;
    FavComicAdapter comicAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favourite, container, false);
        rcvFavList = v.findViewById(R.id.rcvFavList);
        MainActivity temp = (MainActivity)getActivity();

        comicAdapter = temp.getFavComicAdapter();


        rcvFavList.setAdapter(comicAdapter);
        LinearLayoutManager ln = new LinearLayoutManager(getActivity());
        rcvFavList.setLayoutManager(ln);
        // Inflate the layout for this fragment
        return v;
    }
}
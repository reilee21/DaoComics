package com.example.daocomics.ui.comic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.daocomics.R;

public class DesciptonFragment extends Fragment {
    TextView tvDescrip;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_descipton, container, false);
        tvDescrip = v.findViewById(R.id.tvDescrip);
        ComicDetailsActivity temp = (ComicDetailsActivity) getActivity();
        String descrip = temp.getComicDesciption();
        tvDescrip.setText(descrip);
        return v;
    }
}
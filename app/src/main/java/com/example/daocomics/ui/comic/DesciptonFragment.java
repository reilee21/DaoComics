package com.example.daocomics.ui.comic;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.daocomics.R;
import com.example.daocomics.adapter.ReadAdapter;
import com.example.daocomics.model.Chapter;
import com.example.daocomics.model.ImgChap;
import com.example.daocomics.ui.comic_read.ReadComicActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class DesciptonFragment extends Fragment {
    Context ct;
    TextView tvDescrip;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_descipton, container, false);
        tvDescrip = v.findViewById(R.id.tvDescrip);
        ComicDetailsActivity temp = (ComicDetailsActivity) getActivity();
        ct = temp;
        String descrip = temp.getComicDesciption();
        String comicName = temp.getComicName();
        tvDescrip.setText(descrip);
        ImageButton b = v.findViewById(R.id.btnStartRead);
        b.setOnClickListener(r-> Read(comicName));

        return v;
    }

    private void Read(String comicName) {
        Intent i = new Intent(getActivity(), ReadComicActivity.class);
        Chapter chapter = new Chapter("Chapter 1",comicName);
        Bundle b = new Bundle();
        b.putSerializable("chapter",chapter);
        i.putExtras(b);
        startActivity(i);
    }

}
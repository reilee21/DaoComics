package com.example.daocomics.ui.comic;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.daocomics.R;
import com.example.daocomics.adapter.ChapterListAdapter;
import com.example.daocomics.adapter.ComicAdapter;
import com.example.daocomics.model.Chapter;
import com.example.daocomics.model.Comic;

import java.util.ArrayList;


public class ListChapterFragment extends Fragment {
    ListView lsvListChap;
    ChapterListAdapter chapterListAdapter;
    ArrayList<Chapter> chapterArrayList;
    Context ct;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_chapter, container, false);
        lsvListChap = v.findViewById(R.id.lsvListChap);
        ComicDetailsActivity comicDetailsActivity = (ComicDetailsActivity) getActivity();
        ct = comicDetailsActivity.getBaseContext();
        chapterArrayList = comicDetailsActivity.getListChapter();
        chapterListAdapter = new ChapterListAdapter(ct,0,chapterArrayList);

        lsvListChap.setAdapter(chapterListAdapter);
        // Inflate the layout for this fragment
        return v;
    }
}
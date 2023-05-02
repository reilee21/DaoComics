package com.example.daocomics.ui.comic_read;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.daocomics.R;
import com.example.daocomics.adapter.ReadAdapter;
import com.example.daocomics.databinding.ActivityComicDetailsBinding;
import com.example.daocomics.databinding.ActivityMainBinding;
import com.example.daocomics.databinding.ActivityReadComicBinding;
import com.example.daocomics.model.Chapter;
import com.example.daocomics.model.ImgChap;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ReadComicActivity extends AppCompatActivity {
    ActivityReadComicBinding binding;
    ReadAdapter adapter,readAdapter;
    Chapter chapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_comic);
        binding = ActivityReadComicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getChapter();
        setAdapter();
        getImgToRead();

        //event
        event();

    }

    private void setAdapter() {
        binding.rcvRead.setHasFixedSize(false);
        binding.rcvRead.setLayoutManager(new LinearLayoutManager(this));


        adapter = new ReadAdapter(this);
        binding.rcvRead.setAdapter(adapter);

        binding.rcvRead.setOnTouchListener(new TranslateAnimationUtil(this,binding.actionBar));

    }

    private void event() {
        binding.btnBack.setOnClickListener(v->finish());
    }


    private void setupActionbar() {
        binding.textView3.setText(chapter.getChapterName());
    }

    private void getChapter() {
        Bundle b = getIntent().getExtras();
        chapter = (Chapter)b.getSerializable("chapter");
        setupActionbar();

    }

    private void getImgToRead() {

        FirebaseFirestore.getInstance().collection("ImgChap").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> documentSnapshotList = queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot dc : documentSnapshotList)
                {
                    String chap = dc.get("chapterName").toString().trim();
                    String comicname = dc.get("comicName").toString().trim();
                    if(chap.equals(chapter.getChapterName()) && comicname.equals(chapter.getComicName())) {
                        ImgChap img = dc.toObject(ImgChap.class);
                        adapter.Add(img);
                    }

                }
            }
        });
    }
}
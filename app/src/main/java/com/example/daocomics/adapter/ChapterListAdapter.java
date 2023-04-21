package com.example.daocomics.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daocomics.R;
import com.example.daocomics.model.Chapter;

import java.util.ArrayList;
import java.util.List;

public class ChapterListAdapter extends ArrayAdapter<Chapter> {
    private Context ct;
    private ArrayList<Chapter> arr;


    public ChapterListAdapter(@NonNull Context context, int resource, @NonNull List<Chapter> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_item_chaplist, null);
        }
        if (arr.size()>0){
            TextView tvChapName,tvDate;
            tvChapName = convertView.findViewById(R.id.tvChapName);
            tvDate = convertView.findViewById(R.id.tvDate);

            Chapter chapter = arr.get(position);
            tvChapName.setText(chapter.getChapterName());
            tvDate.setText(chapter.getDate());
        }
        return convertView;
    }



}

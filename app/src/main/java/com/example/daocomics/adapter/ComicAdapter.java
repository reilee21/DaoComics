package com.example.daocomics.adapter;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.adapters.ViewBindingAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.bumptech.glide.Glide;
import com.example.daocomics.R;
import com.example.daocomics.model.Comic;
import com.example.daocomics.ui.comic.ComicDetailsActivity;

import java.util.ArrayList;

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ComicViewHolder> {
    ArrayList<Comic> arrayList;
    Context ct;

    public ComicAdapter(ArrayList<Comic> arrayList) {
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public ComicAdapter.ComicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ct = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(ct);
        View comicV = inflater.inflate(R.layout.layout_item_comic,parent,false);
        ComicViewHolder viewHolder = new ComicViewHolder(comicV);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ComicAdapter.ComicViewHolder holder, int position) {
        Comic comic = arrayList.get(position);
        holder.name.setText(comic.getName());
        Glide.with(this.ct).load(comic.getImageThumb()).into(holder.img);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Comic comic = arrayList.get(position);
                Intent i = new Intent(ct,ComicDetailsActivity.class);
                Bundle b = new Bundle();
                b.putSerializable("comic",comic);
                i.putExtras(b);
                ct.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void SortComic(ArrayList<Comic> comicSearch, String s) {
        s = s.toUpperCase();
        int k = 0;
        arrayList.clear();
        for(Comic c : comicSearch){
            String name = c.getName().toUpperCase();
            if(name.contains(s))
                arrayList.add(c);
        }
    }
    public class ComicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ImageView img;
        TextView name;
        ItemClickListener itemClickListener;

        public ComicViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            img = itemView.findViewById(R.id.imgMoTaTr);
            name = itemView.findViewById(R.id.tvChapName);

        }
        public void setItemClickListener(ItemClickListener itemClickListener)
        {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view,getAdapterPosition(),false);
            }
        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),true);
            return true;
        }
    }


}


package com.example.daocomics.ui.tabmain;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.daocomics.MainActivity;
import com.example.daocomics.R;
import com.example.daocomics.Utils;
import com.example.daocomics.adapter.ComicAdapter;
import com.example.daocomics.databinding.FragmentExploreBinding;
import com.example.daocomics.model.Comic;

import java.util.ArrayList;


public class ExploreFragment extends Fragment {
    EditText search;
    ImageView img;
    RecyclerView rcv;
    ComicAdapter comicAdapter;
    ArrayList<Comic> comicsList,temp;
    GridLayoutManager grv;


    Context ct;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         View v =  inflater.inflate(R.layout.fragment_explore, container, false);
         search = v.findViewById(R.id.edSearch);
         img = v.findViewById(R.id.imgExploreLuffy);
         rcv = v.findViewById(R.id.rcvSearchList);
        img.setImageBitmap(Utils.convertFromAssets(getActivity(), "explore.png"));

         comicsList = new ArrayList<>();
         addDataSample();
        temp = new ArrayList<>();
        onSearch();
        comicAdapter = new ComicAdapter(temp);
        grv = new GridLayoutManager(getActivity(),2);

        return v;
    }

    private void onSearch() {
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = search.getText().toString();
                if(s.length()>0) {
                    img.setImageBitmap(Utils.convertFromAssets(getActivity(), "white.png"));
                    if(temp.size()!=comicsList.size()) {
                        for (Comic c : comicsList) {
                            temp.add(c);
                        }
                    }
                    comicAdapter.SortComic(comicsList,s);
                }
                else {
                    temp.clear();
                    img.setImageBitmap(Utils.convertFromAssets(getActivity(), "explore.png"));
                }
                rcv.setAdapter(comicAdapter);
                rcv.setLayoutManager(grv);
            }
        });
    }
    private void addDataSample() {

        comicsList.add(new Comic("Solo leveling","Solo cung Jinwoo","https://www.asurascans.com/wp-content/uploads/2021/03/soloLevelingCover02.png","Action"));
        comicsList.add(new Comic("The Dark Mage’s Return to Enlistment","Solo cung Jinwoo","https://www.asurascans.com/wp-content/uploads/2023/03/resource_1Photoauto_scaleLevel3width_1000.jpg","Action"));
        comicsList.add(new Comic("Demonic Evolution","Solo cung Jinwoo","https://www.asurascans.com/wp-content/uploads/2023/02/tIEELUSJN.webp-t.w640-vert-copyCUnetauto_scaleLevel3width-1000.jpg","Action"));
        comicsList.add(new Comic("Existence","Solo cung Jinwoo","https://www.asurascans.com/wp-content/uploads/2022/08/existenceCover01.png","Action"));
        comicsList.add(new Comic("Never Die Extra","Solo cung Jinwoo","https://www.asurascans.com/wp-content/uploads/2022/09/dieExtraCover03.png","Action"));
        comicsList.add(new Comic("Ending Maker","Solo cung Jinwoo","https://www.asurascans.com/wp-content/uploads/2022/05/ending_maker_01.jpg","Action"));
        comicsList.add(new Comic("My Healing Game","Solo cung Jinwoo","https://www.asurascans.com/wp-content/uploads/2022/08/MyHealingGame04.png","Action"));

    }
}
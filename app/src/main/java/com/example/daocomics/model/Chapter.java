package com.example.daocomics.model;

import java.io.Serializable;

public class Chapter implements Serializable {
    String chapterName;

    public String getComicName() {
        return comicName;
    }

    public void setComicName(String comicName) {
        this.comicName = comicName;
    }

    String comicName;


    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }



    public Chapter() {
    }

    public Chapter(String chapterName,String comicName) {
        this.chapterName = chapterName;
        this.comicName = comicName;

    }
}

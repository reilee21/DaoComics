package com.example.daocomics.model;

public class Chapter {
    String chapterName;
    int idComic;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String date;

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }



    public Chapter() {
    }

    public Chapter(String chapterName,String date) {
        this.date = date;
        this.chapterName = chapterName;

    }
}

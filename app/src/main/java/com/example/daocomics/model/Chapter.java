package com.example.daocomics.model;

public class Chapter {
    String chapterName;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String date;

    public int getIdComic() {
        return idComic;
    }

    public void setIdComic(int idComic) {
        this.idComic = idComic;
    }

    int idComic;

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

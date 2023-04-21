package com.example.daocomics.model;

public class Genre {
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGenrename() {
        return genrename;
    }

    public void setGenrename(String genrename) {
        this.genrename = genrename;
    }

    String genrename;


    public Genre(int id, String genrename) {
        this.id = id;
        this.genrename = genrename;
    }

    public Genre() {
    }
}

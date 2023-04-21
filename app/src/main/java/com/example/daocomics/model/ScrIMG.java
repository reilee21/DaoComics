package com.example.daocomics.model;

public class ScrIMG {
    int id;
    String imgName;
    int idChap;
    String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public int getIdChap() {
        return idChap;
    }

    public void setIdChap(int idChap) {
        this.idChap = idChap;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ScrIMG(int id, String imgName, int idChap, String url) {
        this.id = id;
        this.imgName = imgName;
        this.idChap = idChap;
        this.url = url;
    }

    public ScrIMG() {
    }
}

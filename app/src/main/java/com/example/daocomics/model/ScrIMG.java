package com.example.daocomics.model;

public class ScrIMG {
    String imgName;
    int idChap;
    String url;


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

    public ScrIMG(String imgName, int idChap, String url) {
        this.imgName = imgName;
        this.idChap = idChap;
        this.url = url;
    }

    public ScrIMG() {
    }
}

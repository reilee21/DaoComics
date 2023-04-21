package com.example.daocomics.model;

import java.io.Serializable;

public class User implements Serializable {

    String Name;
    String PassW;

    public String getUsName() {
        return UsName;
    }

    public void setUsName(String usName) {
        UsName = usName;
    }

    String UsName;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassW() {
        return PassW;
    }

    public void setPassW(String passW) {
        PassW = passW;
    }

    public String getPhoneNb() {
        return PhoneNb;
    }

    public void setPhoneNb(String phoneNb) {
        PhoneNb = phoneNb;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    String PhoneNb;
    String Email;
    String Avatar;

    public User() {
    }

    public User(String usName, String name, String passW, String phoneNb, String email, String avatar) {
        UsName = usName;
        Name = name;
        PassW = passW;
        PhoneNb = phoneNb;
        Email = email;
        Avatar = avatar;
    }
}

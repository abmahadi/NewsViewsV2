package com.example.hometask;

public class UserInformation {

    private  int id;
    private int User;
    private String name;
    private String who;
    private int  image;


    public UserInformation() {
    }

    public UserInformation(int id, int user, String name, String who, int image) {
        this.id = id;
        User = user;
        this.name = name;
        this.who = who;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return User;
    }

    public void setUser(int user) {
        User = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}

package com.example.hometask;

public class UserInformation {

    private  int id;
    private String user;
    private String name;
    private String who;
    private int  image;


    public UserInformation() {
    }

    public UserInformation(int id, String user, String name, String who, int image) {
        this.id = id;
        this.user = user;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

package com.example.thevillain.mathforbaby.Objects;

/**
 * Created by Windows 10 Gamer on 05/11/2017.
 */

public class User {
    String id;
    String avatar;
    String fullname;
    String username;
    String password;
    String highscore;
    String account_type;

    public User(String id, String avatar, String fullname, String username, String password, String highscore, String account_type) {
        this.id = id;
        this.avatar = avatar;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.highscore = highscore;
        this.account_type = account_type;
    }

    public User(String avatar, String fullname, String username, String password, String highscore, String account_type) {
        this.avatar = avatar;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.highscore = highscore;
        this.account_type = account_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHighscore() {
        return highscore;
    }

    public void setHighscore(String highscore) {
        this.highscore = highscore;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }
}

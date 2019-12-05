package com.example.matchamenueadmin;

import java.util.List;
import java.util.Map;

public class User {

    String userID;
    String name;
    String password;
    String email;
    List<Map> menu;

    public User(){

    }

    public User(String userID, String name, String password, String email, List<Map> menu) {
        this.userID = userID;
        this.name = name;
        this.password = password;
        this.email = email;
        this.menu = menu;
    }

    public String getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public List<Map> getMenu() {
        return menu;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMenu(List<Map> menu) {
        this.menu = menu;
    }
}

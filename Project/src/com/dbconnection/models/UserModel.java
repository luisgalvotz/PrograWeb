/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dbconnection.models;

/**
 *
 * @author magoc
 */
public class UserModel {

    private String userName;
    private String password;
    private String urlImage;

    public UserModel() {
    }

    public UserModel(String userName, String urlImage) {
        this.userName = userName;
        this.urlImage = urlImage;
    }

    public UserModel(String userName, String password, String urlImage) {
        this.userName = userName;
        this.password = password;
        this.urlImage = urlImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

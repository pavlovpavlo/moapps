package com.pavlov.moappstest.pojo;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("userNick")
    private String userNick;
    @SerializedName("password")
    private String password;
    @SerializedName("data")
    private String data;
    @SerializedName("err")
    private String err;
    @SerializedName("code")
    private String code;

    public User(String userNick, String password) {
        this.userNick = userNick;
        this.password = password;
    }

    public String getUserNick() {
        return userNick;
    }

    public String getPassword() {
        return password;
    }

    public String getData() {
        return data;
    }

    public String isErr() {
        return err;
    }

    public String getCode() {
        return code;
    }
}

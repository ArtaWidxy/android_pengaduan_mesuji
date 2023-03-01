package com.widxy.mesuji.model.lihat;

import com.google.gson.annotations.SerializedName;
import com.widxy.mesuji.model.login.LoginData;

public class Lihat {
    @SerializedName("status")
    private boolean status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private LihatData lihatData;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LihatData getLihatData() {
        return lihatData;
    }

    public void setLihatData(LihatData lihatData) {
        this.lihatData = lihatData;
    }
}

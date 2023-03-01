package com.widxy.mesuji.model.pengaduan;

import com.google.gson.annotations.SerializedName;
import com.widxy.mesuji.model.register.RegisterData;

public class Pengaduan {
    @SerializedName("status")
    private boolean status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private PengaduanData pengaduanData;

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

    public PengaduanData getPengaduanData() {
        return pengaduanData;
    }

    public void setPengaduanData(PengaduanData pengaduanData) {
        this.pengaduanData = pengaduanData;
    }
}

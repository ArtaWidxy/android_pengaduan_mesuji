package com.widxy.mesuji.model.lihat;

import com.google.gson.annotations.SerializedName;

public class LihatData {
    @SerializedName("tanggapan")
    private String tanggapan;

    @SerializedName("description")
    private String description;

    public String getTanggapan() {
        return tanggapan;
    }

    public void setTanggapan(String tanggapan) {
        this.tanggapan = tanggapan;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

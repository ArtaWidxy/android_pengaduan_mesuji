package com.widxy.mesuji.model.pengaduan;

import com.google.gson.annotations.SerializedName;

public class PengaduanData {
    @SerializedName("user_nik")
    private String user_nik;

    @SerializedName("name")
    private String name;

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("description")
    private String description;

    public String getUser_nik() {
        return user_nik;
    }

    public void setUser_nik(String user_nik) {
        this.user_nik = user_nik;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

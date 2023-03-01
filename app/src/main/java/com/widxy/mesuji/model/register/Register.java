package com.widxy.mesuji.model.register;

import com.google.gson.annotations.SerializedName;
import com.widxy.mesuji.model.login.LoginData;

public class Register {
    @SerializedName("status")
    private boolean status;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private RegisterData registerData;

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

    public RegisterData getRegisterData() {
        return registerData;
    }

    public void setRegisterData(RegisterData registerData) {
        this.registerData = registerData;
    }
}

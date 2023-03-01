package com.widxy.mesuji.api;

import com.widxy.mesuji.model.lihat.Lihat;
import com.widxy.mesuji.model.login.Login;
import com.widxy.mesuji.model.pengaduan.Pengaduan;
import com.widxy.mesuji.model.register.Register;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login.php")
    Call<Login> loginResponse(
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register.php")
    Call<Register> registerResponse(
            @Field("nik") String nik,
            @Field("name") String name,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("kirim.php")
    Call<Pengaduan> pengaduanResponse(
            @Field("user_nik") String user_nik,
            @Field("name") String name,
            @Field("user_id") String user_id,
            @Field("description") String description
    );

    @FormUrlEncoded
    @POST("lihat.php")
    Call<Lihat> lihatResponse(
            @Field("pengaduan_id") String pengaduan_id
    );
}

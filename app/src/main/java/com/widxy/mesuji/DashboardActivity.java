package com.widxy.mesuji;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.widxy.mesuji.api.ApiClient;
import com.widxy.mesuji.api.ApiInterface;
import com.widxy.mesuji.model.lihat.Lihat;
import com.widxy.mesuji.model.lihat.LihatData;
import com.widxy.mesuji.model.login.LoginData;
import com.widxy.mesuji.model.pengaduan.Pengaduan;
import com.widxy.mesuji.model.register.Register;

import java.text.DateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{
    ApiInterface apiInterface;
    TextClock clock;
    TextView etName, etUserId, etEmail, etPhone, etRoles, etNik, etPengaduan, date;
    Button btnPengaduan, btnRiwayat;
    SessionManager sessionManager;
    String pengaduanid, name, nik, role, phone, email, userid, usernik, username, useridd, create, deskripsis, update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        sessionManager = new SessionManager(DashboardActivity.this);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(android.icu.text.DateFormat.FULL).format(calendar.getTime());
        TextView textViewDate = findViewById(R.id.text_vie_date);
        textViewDate.setText(currentDate);

        if(!sessionManager.isLoggedIn()){
            moveToLogin();
        }

        date = findViewById(R.id.text_vie_date);
        clock = findViewById(R.id.text_view_time);

        etName = findViewById(R.id.tvMainName);
        etNik = findViewById(R.id.tvNik);
        etUserId = findViewById(R.id.tvUserId);
        etEmail = findViewById(R.id.tvEmail);
        etPhone = findViewById(R.id.tvPhone);
        etRoles = findViewById(R.id.tvRoles);
        etPengaduan = findViewById(R.id.etPengaduan);

        name = sessionManager.getUserDetail().get(SessionManager.NAME);
        nik = sessionManager.getUserDetail().get(SessionManager.NIK);
        userid = sessionManager.getUserDetail().get(SessionManager.USER_ID);
        email = sessionManager.getUserDetail().get(SessionManager.EMAIL);
        phone = sessionManager.getUserDetail().get(SessionManager.PHONE);
        role = sessionManager.getUserDetail().get(SessionManager.ROLES);

        date.setText(currentDate);
        etName.setText(name);
        etNik.setText(nik);
        etUserId.setText(userid);
        etEmail.setText(email);
        etPhone.setText(phone);
        etRoles.setText(role);

        btnPengaduan = findViewById(R.id.btnpengaduan);
        btnPengaduan.setOnClickListener(this);
        btnRiwayat = findViewById(R.id.btnriwayat);
        btnRiwayat.setOnClickListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionLogout:
                sessionManager.logoutSession();
                moveToLogin();
        }
        return super.onOptionsItemSelected(item);
    }

    private void moveToLogin() {
        Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnriwayat:
                pengaduanid = etUserId.getText().toString();
                lihat(pengaduanid);
                break;

            case R.id.btnpengaduan:
                usernik = etNik.getText().toString();
                username = etName.getText().toString();
                useridd = etUserId.getText().toString();
                deskripsis = etPengaduan.getText().toString();
                pengaduan(usernik, username, useridd, deskripsis);
                break;
        }
    }

    private void lihat(String pengaduanid) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Lihat> call = apiInterface.lihatResponse(pengaduanid);
        call.enqueue(new Callback<Lihat>() {
            @Override
            public void onResponse(Call<Lihat> call, Response<Lihat> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){

                    // Ini untuk menyimpan sesi
                    sessionManager = new SessionManager(DashboardActivity.this);
                    LihatData lihatData = response.body().getLihatData();
                    sessionManager.createViewSession(lihatData);

                    //Ini untuk pindah
                    Toast.makeText(DashboardActivity.this, response.body().getLihatData().getDescription(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DashboardActivity.this, PengaduanActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(DashboardActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Lihat> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void pengaduan(String usernik, String username, String useridd, String deskripsis) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Pengaduan> call = apiInterface.pengaduanResponse(usernik, username, useridd, deskripsis);
        call.enqueue(new Callback<Pengaduan>() {
            @Override
            public void onResponse(Call<Pengaduan> call, Response<Pengaduan> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(DashboardActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DashboardActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pengaduan> call, Throwable t) {
                Toast.makeText(DashboardActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
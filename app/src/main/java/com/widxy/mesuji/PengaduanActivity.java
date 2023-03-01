package com.widxy.mesuji;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class PengaduanActivity extends AppCompatActivity {
    TextView pengaduan, tanggapan;

    String adu, tanggap;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaduan);
        sessionManager = new SessionManager(PengaduanActivity.this);

        pengaduan = findViewById(R.id.tvReport);
        tanggapan = findViewById(R.id.tvRespon);

        adu = sessionManager.getLihatDetail().get(SessionManager.DESCRIPTION);
        tanggap = sessionManager.getLihatDetail().get(SessionManager.TANGGAPAN);

        pengaduan.setText(adu);
        tanggapan.setText(tanggap);
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
        Intent intent = new Intent(PengaduanActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }
}
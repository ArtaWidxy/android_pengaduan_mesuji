package com.widxy.mesuji;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.widxy.mesuji.api.ApiClient;
import com.widxy.mesuji.api.ApiInterface;
import com.widxy.mesuji.model.register.Register;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    EditText etNik, etNama, etEmail, etPhone, etPassword;
    Button btnRegister;
    String nik, nama, email, phone, password;
    ApiInterface apiInterface;
    TextView tvLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        etNik = findViewById(R.id.etNIK);
        etNama = findViewById(R.id.etNama);
        etEmail = findViewById(R.id.etEmail);
        etPhone = findViewById(R.id.etPhone);
        etPassword = findViewById(R.id.etPassword);

        tvLogin = findViewById(R.id.tvLoginHere);
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister:
                nik = etNik.getText().toString();
                nama = etNama.getText().toString();
                email = etEmail.getText().toString();
                phone = etPhone.getText().toString();
                password = etPassword.getText().toString();
                register(nik, nama, email, phone, password);
                break;
        }
    }

    private void register(String nik, String nama, String email, String phone, String password) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Register> call = apiInterface.registerResponse(nik, nama, email, phone, password);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
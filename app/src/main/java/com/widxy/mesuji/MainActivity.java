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
import com.widxy.mesuji.model.login.Login;
import com.widxy.mesuji.model.login.LoginData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etEmail, etPassword;
    Button btnLogin;
    String Email, Password;
    TextView tvRegister;
    ApiInterface apiInterface;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvRegister = findViewById(R.id.tvCreateAccount);
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnLogin:
                Email = etEmail.getText().toString();
                Password = etPassword.getText().toString();
                login(Email, Password);
                break;
        }
    }

    private void login(String email, String password) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<Login> call = apiInterface.loginResponse(email,password);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){

                    // Ini untuk menyimpan sesi
                    sessionManager = new SessionManager(MainActivity.this);
                    LoginData loginData = response.body().getLoginData();
                    sessionManager.createLoginSession(loginData);

                    //Ini untuk pindah
                    Toast.makeText(MainActivity.this, response.body().getLoginData().getName(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
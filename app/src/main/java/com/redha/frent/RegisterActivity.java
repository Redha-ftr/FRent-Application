package com.redha.frent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import api.InterfaceAPI;
import api.serverAPI;
import model.Register;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
EditText editUname, editPass, editTelp, editNama, editCPass;
Button btnDaftar, btnLogin;
    String Username, Password, Nama, Telepon, CPass;
    InterfaceAPI apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editUname = (EditText) findViewById(R.id.editUname);
        editPass = (EditText) findViewById(R.id.editPass);
        editTelp = (EditText) findViewById(R.id.editTelp);
        editNama = (EditText) findViewById(R.id.editNama);
        btnDaftar = (Button) findViewById(R.id.btnDaftar);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        editCPass = (EditText) findViewById(R.id.editCPass);

        btnDaftar.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnDaftar:
                Username = editUname.getText().toString();
                Password = editPass.getText().toString();
                Nama = editNama.getText().toString();
                Telepon = editTelp.getText().toString();
                CPass = editCPass.getText().toString();

                if(Username.length()==0) {
                    editNama.setError("Masukkan nama pengguna!");
                }else if(Password.length()==0) {
                    editUname.setError("Masukkan username!");
                }else if(Nama.length()==0) {
                    editPass.setError("Masukkan password!");
                }else if(Telepon.length()==0) {
                    editPass.setError("Masukkan password!");
                }else if(Password.length() < 5){
                    editPass.setError("Password minimal terdiri dari 6 karakter");
                    return;
                }else if (!CPass.equals(Password)) {
                    editCPass.setError("Password tidak sesuai");
                    return;
                }
                register(Username, Password, Nama, Telepon);
                break;
            case R.id.btnLogin:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void register(String username, String password, String nama, String telepon) {

        apiInterface = serverAPI.getSelectAPI();
        Call<Register> call = apiInterface.registerResponse(nama, username, password, telepon);
        call.enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {
                if(response.body() != null && response.isSuccessful() && response.body().isStatus()){
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<Register> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
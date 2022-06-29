package com.redha.frent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.GedungAdapter;
import api.serverAPI;
import model.GedungModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    GedungAdapter adapter;
    SessionManager sessionManager;
    private List<GedungModel> gedungModelList = new ArrayList<>();
    LinearLayoutManager llm;
    TextView txtNama, txtUsername;
    Button btnLogout;
    String nama, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(MainActivity.this);
        if(!sessionManager.isLoggedIn()){
            moveToLogin();
        }

        txtNama = (TextView) findViewById(R.id.txtNama);
        txtUsername = (TextView) findViewById(R.id.txtUsername);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        nama = sessionManager.getUserDetail().get(SessionManager.NAMA);
        username = sessionManager.getUserDetail().get(SessionManager.USERNAME);
        txtNama.setText(nama);
        txtUsername.setText(username);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logoutSession();
                moveToLogin();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        adapter =  new GedungAdapter(this, gedungModelList);
        recyclerView.setAdapter(adapter);

        showData();
    }

    private void moveToLogin() {
        Intent iLogin = new Intent(MainActivity.this, LoginActivity.class);
        iLogin.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(iLogin);
        finish();
    }

 /*   @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //TextView getId = (TextView) view.findViewById(R.id.txtId);
        //final int id = Integer.parseInt(getId.getText().toString());

        Intent intent = new Intent(MainActivity.this, RincianGedungActivity.class);
        //intent.putExtra(row_id, id);
        startActivity(intent);
    }*/

    private void showData() {
        serverAPI.getSelectAPI().callGedungList().enqueue(new Callback<List<GedungModel>>() {
            @Override
            public void onResponse(Call<List<GedungModel>> call, Response<List<GedungModel>> response) {
                Log.i("DataAPI",response.body().toString());
                gedungModelList.addAll(response.body());
                adapter.notifyDataSetChanged();
            }


            @Override
            public void onFailure(Call<List<GedungModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage().toString(), Toast.LENGTH_LONG).show(); }
        });
    }
}
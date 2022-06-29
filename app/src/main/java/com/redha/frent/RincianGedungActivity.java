package com.redha.frent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class RincianGedungActivity extends AppCompatActivity {
Button btnPesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rincian_gedung);

        btnPesan = (Button) findViewById(R.id.btnPesan);

        Intent intent = new Intent(this, TransaksiActivity.class);
        startActivity(intent);
        finish();
    }
}
package com.redha.frent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TransaksiActivity extends AppCompatActivity {
TextView txtnama, txtnamagedung, txttanggal, txtjam, txtdurasi, txtTotHarga, txtno;
Button btnPesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        txtnama = (TextView) findViewById(R.id.txtnama);
        txtno = (TextView) findViewById(R.id.txtno);
        txtnamagedung = (TextView) findViewById(R.id.txtnamagedung);
        txttanggal = (TextView) findViewById(R.id.txtTanggal);
        txtjam = (TextView) findViewById(R.id.txtJam);
        txtdurasi = (TextView) findViewById(R.id.txtDurasi);
        txtTotHarga = (TextView) findViewById(R.id.txtTotHarga);

        Intent intent = getIntent();
        String nama = (String) intent.getExtras().getString("nama");
                //intent.getSerializableExtra("nama");
        String namagedung = (String) intent.getExtras().getString("namagedung"); //getSerializableExtra("namagedung");
        String tanggal = (String) intent.getExtras().getString("tanggal"); //getSerializableExtra("tanggal");
        String jam = (String) intent.getExtras().getString("jam"); //getSerializableExtra("jam");
        String durasi = (String) intent.getExtras().getString("durasi"); //getSerializableExtra("durasi");
        int totharga = (Integer) intent. getExtras().getInt("totharga"); //getSerializableExtra("totharga");
        String no = (String) intent.getExtras().getString("notelp");

        txtnama.setText(nama);
        txtno.setText(no);
        txtnamagedung.setText(namagedung);
        txttanggal.setText(tanggal);
        txtjam.setText(jam);
        txtdurasi.setText(durasi + " jam");
        txtTotHarga.setText("Rp"+totharga);

        btnPesan = (Button) findViewById(R.id.btnPesan);
        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Transaksi akan segera diproses", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(TransaksiActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
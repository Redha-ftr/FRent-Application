package com.redha.frent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import adapter.GedungAdapter;
import api.serverAPI;
import model.GedungModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RincianGedungActivity extends AppCompatActivity {
Button btnPesan;

    Spinner spJam;
    String [] jam = {"08.00 - 09.00","09.00 - 10.00","10.00 - 11.00","11.00 - 12.00",
            "13.00 - 14.00","14.00 - 15.00","15.00 - 16.00","16.00 - 17.00",
            "17.00 - 18.00","19.00 - 20.00","20.00 - 21.00","21.00 - 22.00",
            "22.00 - 23.00","23.00 - 00.00"};

    Calendar calender;
    DatePickerDialog.OnDateSetListener date;
    TextView datedeparture, txtHarga, txtnamagedung, txtalamat, txtnohp, txtket;
    EditText durasi;
    ImageView image;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rincian_gedung);

        datedeparture = findViewById(R.id.datedeparture);
        btnPesan = (Button) findViewById(R.id.btnPesan);
        spJam = (Spinner) findViewById(R.id.spjam);
        txtHarga = (TextView) findViewById(R.id.harga);
        txtnamagedung = (TextView) findViewById(R.id.nama_gedung);
        txtalamat = (TextView) findViewById(R.id.alamat);
        txtnohp = (TextView) findViewById(R.id.nmr_telp);
        txtket = (TextView) findViewById(R.id.ket);
        durasi = (EditText) findViewById(R.id.editdurasi);
        sessionManager = new SessionManager(this);
        image = (ImageView) findViewById(R.id.gambar);

        //get data intent
        getIncomingExtra();

        ArrayAdapter AA = new ArrayAdapter(this,R.layout.spinner_style,jam);
        AA.setDropDownViewResource(R.layout.spinner_style);
        spJam.setAdapter(AA);


        calender = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                calender.set(Calendar.YEAR, year);
                calender.set(Calendar.MONTH, monthOfYear);
                calender.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TextView tanggal = findViewById(R.id.datedeparture);
                String format = "dd/MMMM/yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
                tanggal.setText(sdf.format(calender.getTime()));
            }
        };

        datedeparture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RincianGedungActivity.this, date,
                        calender.get(Calendar.YEAR),
                        calender.get(Calendar.MONTH),
                        calender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int harga = Integer.parseInt(txtHarga.getText().toString());
                int totharga = Integer.parseInt(durasi.getText().toString()) * harga;

                Intent intent = new Intent(RincianGedungActivity.this, TransaksiActivity.class);
                intent.putExtra("namagedung",txtnamagedung.getText().toString());
                intent.putExtra("nama",sessionManager.getUserDetail().get(SessionManager.NAMA));
                intent.putExtra("notelp",sessionManager.getUserDetail().get(SessionManager.NOTELP));
                intent.putExtra("tanggal",datedeparture.getText().toString());
                intent.putExtra("jam",spJam.getSelectedItem().toString());
                intent.putExtra("durasi", durasi.getText().toString());
                intent.putExtra("totharga",totharga);
                startActivity(intent);
            }
        });

    }

    private void getIncomingExtra() {
        Intent idata = getIntent();
        if(getIntent().hasExtra("gambar")&&
                getIntent().hasExtra("namagedung")&&
                getIntent().hasExtra("alamat")&&
                getIntent().hasExtra("notelp")&&
                getIntent().hasExtra("keterangan")&&
                getIntent().hasExtra("harga")){

            String gambar = idata.getExtras().getString("gambar");
            String namagedung = idata.getExtras().getString("namagedung");
            String alamat = idata.getExtras().getString("alamat");
            String notelp = idata.getExtras().getString("notelp");
            String keterangan = idata.getExtras().getString("keterangan");
            String harga = idata.getExtras().getString("harga");

            Glide.with(this).asBitmap().load(gambar).into(image);
            txtnamagedung.setText(namagedung);
            txtalamat.setText(alamat);
            txtnohp.setText(notelp);
            txtket.setText(keterangan);
            txtHarga.setText(harga);

        }
    }

}


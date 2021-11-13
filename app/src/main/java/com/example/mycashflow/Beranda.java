package com.example.mycashflow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycashflow.javaclass.DatabaseHelper;

public class Beranda extends AppCompatActivity implements View.OnClickListener {

    private static final String MY_PREFS_NAME = "MyPrefName";
    ImageButton pemasukan, pengeluaran, cashFLow, pengaturan;
    TextView pengeluaranText, pemasukanText;
    DatabaseHelper dbHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
        pemasukan = findViewById(R.id.imageButtonPemasukan);
        pengeluaran = findViewById(R.id.imageButtonPengeluran);
        cashFLow = findViewById(R.id.imageButtonDetailCashFlow);
        pengaturan = findViewById(R.id.imageButtonSetting);
        pemasukanText = findViewById(R.id.textViewPemasukan);
        pengeluaranText = findViewById(R.id.textViewPengeluaran);

        pemasukan.setOnClickListener(this);
        pengeluaran.setOnClickListener(this);
        cashFLow.setOnClickListener(this);
        pengaturan.setOnClickListener(this);

        dbHelp = new DatabaseHelper(this);

        Integer totalPemasukkan = dbHelp.total("pemasukan");
        Integer totalPengeluaran = dbHelp.total("pengeluaran");

        pemasukanText.setText("Rp." + String.valueOf(totalPemasukkan));
        pengeluaranText.setText("Rp." + String.valueOf(totalPengeluaran));
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageButtonPemasukan:
                Toast.makeText(getApplicationContext(),"Pemaasukan",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Beranda.this, TambahPemasukan.class));
                break;

            case R.id.imageButtonPengeluran:
                Toast.makeText(getApplicationContext(),"Pengeluaran",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Beranda.this, TambahPengeluaran.class));
                break;

            case R.id.imageButtonDetailCashFlow:
                startActivity(new Intent(Beranda.this, DetailCashFlow.class));
                Toast.makeText(getApplicationContext(),"Detail Cash Flow",Toast.LENGTH_SHORT).show();
                break;

            case R.id.imageButtonSetting:
                startActivity(new Intent(Beranda.this, Pengaturan.class));
                Toast.makeText(getApplicationContext(),"Setting",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
package com.example.mycashflow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Pengaturan extends AppCompatActivity {

    private static final String MY_PREFS_NAME = "MyPrefsFile";
    EditText passwordLama, passwordBaru;
    Button simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengaturan);
        passwordBaru = findViewById(R.id.editTextPasswordBaru);
        passwordLama = findViewById(R.id.editTextPasswordLama);
        simpan = findViewById(R.id.buttonSimpanPengaturan);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final String prefPassword = prefs.getString("password", "user");

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (passwordLama.getText().toString().equalsIgnoreCase(prefPassword)) {
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("username", "user");
                    editor.putString("password", passwordBaru.getText().toString());
                    editor.apply();
                    startActivity(new Intent(Pengaturan.this, Beranda.class));
                } else {
                    Toast.makeText(getBaseContext(), "Password Tidak Sama", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
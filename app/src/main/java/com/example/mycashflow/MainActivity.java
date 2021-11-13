package com.example.mycashflow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String MY_PREFS_NAME = "MyPrefsFile";
    EditText username, password;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameSt = username.getText().toString();
                String passwordSt = password.getText().toString();

                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                String prefUsername = prefs.getString("username", "user"); //"No name defined" is the default value.
                String prefPassword = prefs.getString("password", "user"); //"No name defined" is the default value.

                if (usernameSt.equalsIgnoreCase(prefUsername) && passwordSt.equalsIgnoreCase(prefPassword)){
                    startActivity(new Intent(MainActivity.this,Beranda.class));
                }else{
                    Toast.makeText(getBaseContext(),"Username atau Password Salah",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
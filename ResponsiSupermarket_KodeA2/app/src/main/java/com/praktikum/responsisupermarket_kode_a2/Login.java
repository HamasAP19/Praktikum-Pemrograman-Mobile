package com.praktikum.responsisupermarket_kode_a2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.praktikum.responsisupermarket_kode_a2.Database.DataHelper;

public class Login extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    private TextView username, password;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        dbHelper = new DataHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        username = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.masuk);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cursor = db.rawQuery("SELECT * FROM users WHERE username = '" +
                        username.getText().toString() + "' AND password = '" +
                        password.getText().toString() + "'", null);
                if (cursor.getCount() > 0) {
                    cursor.moveToPosition(0);
                    if (cursor.getString(4).equals("Admin")) {
                        Intent intent = new Intent(Login.this, Admin.class);
                        intent.putExtra("nama_user", cursor.getString(1));
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(Login.this, Karyawan.class);
                        intent.putExtra("nama_user", cursor.getString(1));
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(Login.this, "Username dan Password Salah, Coba Lagi!!", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}
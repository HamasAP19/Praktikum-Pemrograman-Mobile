package com.praktikum.responsisupermarket_kode_a2.AdminController;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.praktikum.responsisupermarket_kode_a2.Database.DataHelper;
import com.praktikum.responsisupermarket_kode_a2.R;

public class ViewRincianAkunKaryawan extends AppCompatActivity {
    private TextView outputNama, outputUsername, outputPassword;
    protected Cursor cursor;
    private Button btnKembali;
    private DataHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_rincian_akun_karyawan);

        dbHelper = new DataHelper(this);
        outputNama = (TextView) findViewById(R.id.outputNamaKaryawan);
        outputUsername = (TextView) findViewById(R.id.outputUsername);
        outputPassword = (TextView) findViewById(R.id.outputPassword);
        btnKembali = (Button) findViewById(R.id.btnKembali);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM users WHERE id_user = " + getIntent().getStringExtra("idAkun")
                , null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            outputNama.setText(cursor.getString(1).toString());
            outputUsername.setText(cursor.getString(2).toString());
            outputPassword.setText(cursor.getString(3).toString());
        }
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
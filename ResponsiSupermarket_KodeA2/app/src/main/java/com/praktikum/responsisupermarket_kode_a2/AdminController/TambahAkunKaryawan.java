package com.praktikum.responsisupermarket_kode_a2.AdminController;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.praktikum.responsisupermarket_kode_a2.Database.DataHelper;
import com.praktikum.responsisupermarket_kode_a2.Fragment.Frag_ViewListUsers;
import com.praktikum.responsisupermarket_kode_a2.R;

public class TambahAkunKaryawan extends AppCompatActivity {
    private EditText inputNama, inputUsername, inputPassword;
    private Button btnInputData;
    private DataHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_tambah_akun_karyawan);

        dbHelper = new DataHelper(this);
        inputNama = (EditText) findViewById(R.id.inputNamaKaryawan);
        inputUsername = (EditText) findViewById(R.id.inputUsername);
        inputPassword = (EditText) findViewById(R.id.inputPassword);

        btnInputData = (Button) findViewById(R.id.btnTambahKaryawan);
        btnInputData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("INSERT INTO users (nama_user, username, password, level) VALUES ('" +
                        inputNama.getText().toString() + "', '" +
                        inputUsername.getText().toString() + "', '" +
                        inputPassword.getText().toString() + "', 'Karyawan')");
                Toast.makeText(getApplicationContext(), "Berhasil Tambah Akun Karyawan", Toast.LENGTH_SHORT).show();
                Frag_ViewListUsers.FVLU.readData();
                finish();
            }
        });
    }
}
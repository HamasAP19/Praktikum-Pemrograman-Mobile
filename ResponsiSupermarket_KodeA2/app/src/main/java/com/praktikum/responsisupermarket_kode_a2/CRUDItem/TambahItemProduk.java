package com.praktikum.responsisupermarket_kode_a2.CRUDItem;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.praktikum.responsisupermarket_kode_a2.Database.DataHelper;
import com.praktikum.responsisupermarket_kode_a2.Fragment.Frag_Dashboard;
import com.praktikum.responsisupermarket_kode_a2.Fragment.Frag_Statistik;
import com.praktikum.responsisupermarket_kode_a2.R;

public class TambahItemProduk extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private EditText inputKodeProduk;
    private EditText inputNamaProduk;
    private EditText inputJumStok;
    private Button btnTambah;
    private String inputKategoriProduk;

    private Spinner kategoriProduk;
    private ArrayAdapter<String> arrAdapter;
    private String[] kategori = {"Pilih Kategori Produk", "Minuman", "Makanan", "Peralatan Mandi", "Peralatan Rumah Tangga", "Barang Elektronik"};
    private DataHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_tambah_item_produk);
        dbHelper = new DataHelper(this);
        inputKodeProduk = (EditText) findViewById(R.id.inputKodeProduk);
        inputNamaProduk = (EditText) findViewById(R.id.inputNamaProduk);
        inputJumStok = (EditText) findViewById(R.id.inputJumStok);
        btnTambah = (Button) findViewById(R.id.btnTambah);

        kategoriProduk = (Spinner) findViewById(R.id.inputKategoriProduk);
        arrAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, kategori);
        arrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        kategoriProduk.setAdapter(arrAdapter);
        kategoriProduk.setOnItemSelectedListener(TambahItemProduk.this);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("INSERT INTO products (id_products, nama_produk, kategori, jumlah) VALUES ('" +
                        inputKodeProduk.getText().toString() + "', '" +
                        inputNamaProduk.getText().toString() + "', '" +
                        inputKategoriProduk + "', " +
                        inputJumStok.getText().toString() + ")");
                Toast.makeText(getApplicationContext(), "Berhasil Tambah Data Item", Toast.LENGTH_SHORT).show();
                Frag_Dashboard.FD.readData();
                Frag_Statistik.FS.readData();
                finish();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        inputKategoriProduk = (String) arg0.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}

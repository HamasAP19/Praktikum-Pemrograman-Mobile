package com.praktikum.responsisupermarket_kode_a2.CRUDItem;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
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

public class EditItemProduk extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText updateKodeProduk;
    private EditText updateNamaProduk;
    private EditText updateStokProduk;
    private String selectKategoriProduk;
    private Button btnUpdateProduk;
    private String kodeProduk;
    private Spinner updateKategoriProduk;
    private ArrayAdapter<String> arrAdapter;
    private String[] kategori = {"Pilih Kategori Produk", "Minuman", "Makanan", "Peralatan Mandi", "Peralatan Rumah Tangga", "Barang Elektronik"};

    private Cursor cursor;
    private DataHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_edit_item_produk);

        updateKodeProduk = (EditText) findViewById(R.id.updateKodeProduk);
        updateNamaProduk = (EditText) findViewById(R.id.updateNamaProduk);
        updateStokProduk = (EditText) findViewById(R.id.updateStokProduk);
        updateKategoriProduk = (Spinner) findViewById(R.id.updateKategoriProduk);

        dbHelper = new DataHelper(this);

        updateKategoriProduk = (Spinner) findViewById(R.id.updateKategoriProduk);
        arrAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, kategori);
        arrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        updateKategoriProduk.setAdapter(arrAdapter);
        updateKategoriProduk.setOnItemSelectedListener(EditItemProduk.this);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM products WHERE id_products = '" + getIntent().getStringExtra("kodeProduk") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            kodeProduk = cursor.getString(0).toString();
            updateKodeProduk.setText(cursor.getString(0).toString());
            updateNamaProduk.setText(cursor.getString(1).toString());
            updateKategoriProduk.setSelection(getSelectedKategori(cursor.getString(2)));
            updateStokProduk.setText(cursor.getString(3));
        }

        btnUpdateProduk = (Button) findViewById(R.id.btnUpdateProduk);
        btnUpdateProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("UPDATE products SET id_products = '" +
                        updateKodeProduk.getText().toString() + "', nama_produk = '" +
                        updateNamaProduk.getText().toString() + "', kategori = '" +
                        selectKategoriProduk + "', jumlah = " +
                        updateStokProduk.getText().toString() + " WHERE id_products = '" + kodeProduk + "'");
                Toast.makeText(getApplicationContext(), "Berhasil Update Data Item", Toast.LENGTH_SHORT).show();
                Frag_Dashboard.FD.readData();
                Frag_Statistik.FS.readData();
                finish();
            }
        });
    }

    private int getSelectedKategori(String data) {
        int n = 0;
        for (int i = 0; i < 6; i++) {
            if (kategori[i].equals(data)) {
                n = i;
            }
        }
        return n;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectKategoriProduk = (String) adapterView.getItemAtPosition(i);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
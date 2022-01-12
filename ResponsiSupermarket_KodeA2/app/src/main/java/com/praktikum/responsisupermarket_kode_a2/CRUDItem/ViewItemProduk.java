package com.praktikum.responsisupermarket_kode_a2.CRUDItem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.praktikum.responsisupermarket_kode_a2.Database.DataHelper;
import com.praktikum.responsisupermarket_kode_a2.Fragment.Frag_Dashboard;
import com.praktikum.responsisupermarket_kode_a2.Fragment.Frag_Statistik;
import com.praktikum.responsisupermarket_kode_a2.R;

public class ViewItemProduk extends AppCompatActivity {
    private TextView outKodeProduk;
    private TextView outNamaProduk;
    private TextView outKategoriProduk;
    private TextView outStokProduk;
    private Button btnHapusproduk;
    private Button btnEditproduk;
    private DataHelper dbHelper;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_item_produk);

        dbHelper = new DataHelper(this);
        outKodeProduk = (TextView) findViewById(R.id.outputKodeProduk);
        outNamaProduk = (TextView) findViewById(R.id.outputNamaProduk);
        outKategoriProduk = (TextView) findViewById(R.id.outputKategoriProduk);
        outStokProduk = (TextView) findViewById(R.id.outputStokProduk);

        outKodeProduk.setText(getIntent().getStringExtra("kodeProduk"));
        outNamaProduk.setText(getIntent().getStringExtra("namaProduk"));
        outKategoriProduk.setText(getIntent().getStringExtra("kategoriProduk"));
        outStokProduk.setText(getIntent().getStringExtra("stokProduk") + " Item");

        btnEditproduk = (Button) findViewById(R.id.btnEditProduk);
        btnEditproduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewItemProduk.this, EditItemProduk.class);
                intent.putExtra("kodeProduk", getIntent().getStringExtra("kodeProduk"));
                startActivity(intent);
                finish();
            }
        });

        btnHapusproduk = (Button) findViewById(R.id.btnHapusProduk);
        btnHapusproduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("DELETE FROM products WHERE id_products = '" + getIntent().getStringExtra("kodeProduk") + "'");
                Frag_Dashboard.FD.readData();
                Frag_Statistik.FS.readData();
                finish();
            }
        });
    }
}
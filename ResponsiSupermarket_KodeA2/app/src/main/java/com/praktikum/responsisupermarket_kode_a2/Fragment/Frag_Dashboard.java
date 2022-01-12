package com.praktikum.responsisupermarket_kode_a2.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.praktikum.responsisupermarket_kode_a2.Adapter.ItemAdapter;
import com.praktikum.responsisupermarket_kode_a2.CRUDItem.TambahItemProduk;
import com.praktikum.responsisupermarket_kode_a2.ClassData.Products;
import com.praktikum.responsisupermarket_kode_a2.Database.DataHelper;
import com.praktikum.responsisupermarket_kode_a2.R;

import java.util.ArrayList;

public class Frag_Dashboard extends Fragment {
    private RecyclerView recyclerViewMinuman;
    private RecyclerView recyclerViewMakanan;
    private RecyclerView recyclerViewPeralatanMandi;
    private RecyclerView recyclerViewPRT;
    private RecyclerView recyclerViewBarangElektronik;
    private ExtendedFloatingActionButton fab;
    public static Frag_Dashboard FD;
    private Cursor cursor;
    private DataHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_dashboard, container, false);
        recyclerViewMinuman = view.findViewById(R.id.recyleMinuman);
        recyclerViewMakanan = view.findViewById(R.id.recycleMakanan);
        recyclerViewPeralatanMandi = view.findViewById(R.id.recycleperalatanMandi);
        recyclerViewPRT = view.findViewById(R.id.recyclePeralatanRumahTangga);
        recyclerViewBarangElektronik = view.findViewById(R.id.recycleBarangElektronik);
        fab = view.findViewById(R.id.fabTambahItem);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TambahItemProduk.class);
                startActivity(intent);
            }
        });
        FD = Frag_Dashboard.this;
        dbHelper = new DataHelper(getContext());
        readData();
        return view;
    }
    public void readData() {
        setDataRecycle(recyclerViewMinuman, "Minuman");
        setDataRecycle(recyclerViewMakanan, "Makanan");
        setDataRecycle(recyclerViewPeralatanMandi, "Peralatan Mandi");
        setDataRecycle(recyclerViewPRT, "Peralatan Rumah Tangga");
        setDataRecycle(recyclerViewBarangElektronik, "Barang Elektronik");

    }
    private void setDataRecycle(RecyclerView recyclerView, String kunci) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ArrayList<Products> item = new ArrayList<>();
        cursor = db.rawQuery("SELECT * FROM products", null);
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            if (cursor.getString(2).equals(kunci)) {
                item.add(new Products(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)));
            }
        }
        recyclerView.setAdapter(new ItemAdapter(getContext(), item));
        recyclerView.setSelected(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
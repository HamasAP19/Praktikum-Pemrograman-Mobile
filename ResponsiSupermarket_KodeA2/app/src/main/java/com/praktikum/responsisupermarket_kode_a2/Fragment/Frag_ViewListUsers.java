package com.praktikum.responsisupermarket_kode_a2.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.praktikum.responsisupermarket_kode_a2.AdminController.TambahAkunKaryawan;
import com.praktikum.responsisupermarket_kode_a2.AdminController.ViewRincianAkunKaryawan;
import com.praktikum.responsisupermarket_kode_a2.Database.DataHelper;
import com.praktikum.responsisupermarket_kode_a2.R;

public class Frag_ViewListUsers extends Fragment {
    private DataHelper dbHelper;
    private String[] kodeAkun;
    private ListView listView;
    protected Cursor cursor;
    public static Frag_ViewListUsers FVLU;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_view_list_users, container, false);
        ExtendedFloatingActionButton btnTambahKaryawan = view.findViewById(R.id.fabTambahKaryawan);
        listView = view.findViewById(R.id.outputDataUsers);
        btnTambahKaryawan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TambahAkunKaryawan.class);
                startActivity(intent);
            }
        });
        FVLU = this;
        dbHelper = new DataHelper(getContext());
        readData();
        return view;
    }

    public void readData() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM users WHERE level = 'Karyawan'", null);
        String[] daftar = new String[cursor.getCount()];
        kodeAkun = new String[cursor.getCount()];

        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            kodeAkun[cc] = cursor.getString(0);
            daftar[cc] = cursor.getString(2);
        }
        listView.setAdapter(new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, daftar));
        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                final String selectKodeAkun = kodeAkun[position];

                final CharSequence[] dialogItem = {"Lihat Rincian Akun", "Hapus Akun"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Pilihan");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0 :
                                Intent i = new Intent(getContext(), ViewRincianAkunKaryawan.class);
                                i.putExtra("idAkun", selectKodeAkun);
                                startActivity(i);
                                break;
                            case 1 :
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                db.execSQL("DELETE FROM users WHERE id_user = " + selectKodeAkun);
                                readData();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ((ArrayAdapter)listView.getAdapter()).notifyDataSetInvalidated();
    }
}
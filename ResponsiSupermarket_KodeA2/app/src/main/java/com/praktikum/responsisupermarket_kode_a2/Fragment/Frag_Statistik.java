package com.praktikum.responsisupermarket_kode_a2.Fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.praktikum.responsisupermarket_kode_a2.Admin;
import com.praktikum.responsisupermarket_kode_a2.Database.DataHelper;
import com.praktikum.responsisupermarket_kode_a2.Login;
import com.praktikum.responsisupermarket_kode_a2.R;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Frag_Statistik extends Fragment {
    private TextView outTanggal;
    private TextView outMinuman;
    private TextView outMakanan;
    private TextView outPeralatanMandi;
    private TextView outPeralatanRT;
    private TextView outBarangElektronik;
    private TextView namaAdmin;
    private DataHelper dbHelper;
    private Cursor cursor;
    public static Frag_Statistik FS;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_statistik, container, false);

        outTanggal = (TextView) view.findViewById(R.id.outTanggal);
        outMinuman = (TextView) view.findViewById(R.id.outMinuman);
        outMakanan = (TextView) view.findViewById(R.id.outMakanan);
        outPeralatanMandi = (TextView) view.findViewById(R.id.outPeralatanMandi);
        outPeralatanRT = (TextView) view.findViewById(R.id.outPeralatanRumahTangga);
        outBarangElektronik = (TextView) view.findViewById(R.id.outBarangElektronik);

        namaAdmin = (TextView) view.findViewById(R.id.outputNamaAdmin);
        Admin admin = (Admin) getActivity();
        namaAdmin.setText("Selamat Datang, " + admin.getNamaUser());

        FS = Frag_Statistik.this;
        dbHelper = new DataHelper(getContext());
        readData();
        return view;
    }
    public void readData() {
        getTanggal(outTanggal);
        getJumItem(outMinuman, "Minuman");
        getJumItem(outMakanan, "Makanan");
        getJumItem(outPeralatanMandi, "Peralatan Mandi");
        getJumItem(outPeralatanRT, "Peralatan Rumah Tangga");
        getJumItem(outBarangElektronik, "Barang Elektronik");
    }
    private void getTanggal(TextView tanggal) {
        int hari, bulan, tahun;
        String[] namaBulan = {"Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
        GregorianCalendar date = new GregorianCalendar();
        hari = date.get(Calendar.DAY_OF_MONTH);
        bulan = date.get(Calendar.MONTH);
        tahun = date.get(Calendar.YEAR);
        tanggal.setText("Per Tanggal " + hari + " " + namaBulan[bulan] + " " + tahun);
    }

    private void getJumItem(TextView output, String kunci) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT SUM(jumlah) AS stok FROM products WHERE kategori = '" + kunci + "'", null);
        cursor.moveToFirst();

        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            if (cursor.getInt(0) > 0) {
                output.setText(cursor.getString(0) + " Item");
            } else {
                output.setText("0 Item");
            }
        }
    }
}